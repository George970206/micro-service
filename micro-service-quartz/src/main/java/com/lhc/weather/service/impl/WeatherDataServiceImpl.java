package com.lhc.weather.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lhc.weather.service.WeatherDataService;
import com.lhc.weather.vo.WeatherResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 天气数据服务.
 * 
 * @since 1.0.0 2017年10月18日
 * @author <a href="https://waylau.com">Way Lau</a>
 */
@Service
public class WeatherDataServiceImpl implements WeatherDataService {

	private final static Logger logger = LoggerFactory.getLogger(WeatherDataServiceImpl.class);  
	
	private final RestTemplate restTemplate;
	
	private final StringRedisTemplate stringRedisTemplate;
	
	private final String WEATHER_API = "http://wthrcdn.etouch.cn/weather_mini";

	private final static int SUCCESS = 200;

	/**
	 * 	缓存超时时间
 	 */
	private final Long TIME_OUT = 1800L;

	public WeatherDataServiceImpl(RestTemplate restTemplate, StringRedisTemplate stringRedisTemplate) {
		this.restTemplate = restTemplate;
		this.stringRedisTemplate = stringRedisTemplate;
	}

	@Override
	public WeatherResponse getDataByCityId(String cityId) {
		String uri = WEATHER_API + "?citykey=" + cityId;
		return this.doGetWeatherData(uri);
	}

	@Override
	public WeatherResponse getDataByCityName(String cityName) {
		String uri = WEATHER_API + "?city=" + cityName;
		return this.doGetWeatherData(uri);
	}

	@Override
	public void syncDataByCityId(String cityId) {
		String uri = WEATHER_API + "?citykey=" + cityId;
		this.saveWeatherData(uri);
	}
	
	private WeatherResponse doGetWeatherData(String uri) {
		ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
		String strBody = null;

		// 先查缓存，没有再查服务
		if (!stringRedisTemplate.hasKey(uri)) {
			logger.info("不存在 key " + uri);
			
			ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);

			if (response.getStatusCodeValue() == SUCCESS) {
				strBody = response.getBody();
			}

			ops.set(uri, strBody, TIME_OUT, TimeUnit.SECONDS);
		} else {
			logger.info("存在 key " + uri + ", value=" + ops.get(uri));
			
			strBody = ops.get(uri);
		}

		ObjectMapper mapper = new ObjectMapper();
		WeatherResponse weather = null;

		try {
			weather = mapper.readValue(strBody, WeatherResponse.class);
		} catch (IOException e) {
			logger.error("JSON反序列化异常！",e);
		}

		return weather;
	}
	
	private void saveWeatherData(String uri) {
		ValueOperations<String, String> ops = this.stringRedisTemplate.opsForValue();
		String strBody = null;
 
		ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
		
		if (response.getStatusCodeValue() == SUCCESS) {
			strBody = response.getBody();
		}

		ops.set(uri, strBody, TIME_OUT, TimeUnit.SECONDS);
	 
	}

}
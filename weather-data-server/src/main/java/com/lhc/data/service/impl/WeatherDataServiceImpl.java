package com.lhc.data.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lhc.data.service.WeatherDataService;
import com.lhc.data.vo.WeatherResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @author <a href="https://waylau.com">Way Lau</a>
 * @since 1.0.0 2017年10月18日
 */
@Service
public class WeatherDataServiceImpl implements WeatherDataService {

    private final static Logger logger = LoggerFactory.getLogger(WeatherDataServiceImpl.class);

    private final StringRedisTemplate stringRedisTemplate;

    private final String WEATHER_API = "http://wthrcdn.etouch.cn/weather_mini";

    @Autowired
    private RestTemplate restTemplate;

    public WeatherDataServiceImpl(StringRedisTemplate stringRedisTemplate) {
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

    private WeatherResponse doGetWeatherData(String uri) {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        String strBody;
        // 先查缓存，查不到就抛异常
        if (!stringRedisTemplate.hasKey(uri)) {
            logger.info("不存在 key " + uri);
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
            strBody = responseEntity.getBody();
            if (strBody != null) {
                ops.set(uri, strBody, 1800L, TimeUnit.SECONDS);
            } else {
                throw new RuntimeException("没有对应的天气信息");
            }
        } else {
            logger.info("存在 key " + uri + ", value=" + ops.get(uri));
            strBody = ops.get(uri);
        }
        ObjectMapper mapper = new ObjectMapper();
        WeatherResponse weather = null;
        try {
            weather = mapper.readValue(strBody, WeatherResponse.class);
        } catch (IOException e) {
            logger.error("JSON反序列化异常！", e);
        }
        return weather;
    }

}
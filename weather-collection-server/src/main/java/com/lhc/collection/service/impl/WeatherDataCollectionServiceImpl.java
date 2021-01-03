package com.lhc.collection.service.impl;

import com.lhc.collection.service.WeatherDataCollectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

/**
 * 通过Rest客户端去调用第三方的天气数据接口
 * @author lhc
 */
@Service
public class WeatherDataCollectionServiceImpl implements WeatherDataCollectionService {

    private final static Logger logger = LoggerFactory.getLogger(WeatherDataCollectionServiceImpl.class);

    private final RestTemplate restTemplate;

    private final StringRedisTemplate stringRedisTemplate;

    private final String WEATHER_API = "http://wthrcdn.etouch.cn/weather_mini";

    private final static int SUCCESS = 200;

    /**
     * 	缓存超时时间
     */
    private final Long TIME_OUT = 1800L;


    public WeatherDataCollectionServiceImpl(RestTemplate restTemplate, StringRedisTemplate stringRedisTemplate) {
        this.restTemplate = restTemplate;
        this.stringRedisTemplate = stringRedisTemplate;
    }


    @Override
    public void syncDataByCityId(String cityId) {
        logger.info("开始同步天气，cityId={}", cityId);
        String uri = WEATHER_API + "?citykey=" + cityId;
        this.saveWeatherData(uri);
        logger.info("同步天气结束");
    }

    private void saveWeatherData(String uri) {
        ValueOperations<String, String> ops = this.stringRedisTemplate.opsForValue();
        String strBody = null;

        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);

        if (response.getStatusCodeValue() == SUCCESS) {
            strBody = response.getBody();
        }
        //strBody = new String(strBody.getBytes(StandardCharsets.UTF_8), Charset.forName("GBK"));
        ops.set(uri, strBody, TIME_OUT, TimeUnit.SECONDS);

    }
}

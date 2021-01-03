package com.lhc.collection.job;


import com.lhc.collection.service.CityClient;
import com.lhc.collection.service.WeatherDataCollectionService;
import com.lhc.collection.vo.City;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

/**
 * @author lhc
 */
public class WeatherDataSyncJob extends QuartzJobBean {

    private static final Logger log = LoggerFactory.getLogger(WeatherDataSyncJob.class);

    @Autowired
    private WeatherDataCollectionService weatherDataServiceImpl;

    @Autowired
    private CityClient cityClient;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("开始天气数据同步任务");

        //城市数据API微服务来提供数据
        List<City> cities;
        try {
            //调用城市数据API
            cities = cityClient.listCity();
            City city = new City();
            city.setCityId("101280601");
            cities.add(city);
        } catch (Exception e) {
            log.error("获取城市信息异常！",e);
            throw new RuntimeException("获取城市信息异常！",e);
        }
        cities.forEach(e -> {
            String cityId = e.getCityId();
            log.info("天气数据同步任务进行中，cityId:" + cityId);
            // 根据 xml中的城市信息刷新 redis中的天气信息
            weatherDataServiceImpl.syncDataByCityId(cityId);
        });
    }


}

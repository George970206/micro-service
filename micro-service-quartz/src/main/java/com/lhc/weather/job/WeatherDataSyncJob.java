package com.lhc.weather.job;

import com.lhc.weather.service.CityDataService;
import com.lhc.weather.service.WeatherDataService;
import com.lhc.weather.vo.City;
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
    private CityDataService cityDataServiceImpl;

    @Autowired
    private WeatherDataService weatherDataServiceImpl;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("开始天气数据同步任务");

        List<City> cities = null;
        try {
            cities = cityDataServiceImpl.listCity();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cities != null) {
            cities.forEach(e -> {
                String cityId = e.getCityId();
                log.info("天气数据同步任务进行中，cityId:" + cityId);
                // 根据xml中的城市信息刷新redis中的天气信息
                weatherDataServiceImpl.syncDataByCityId(cityId);
            });
        }
    }


}

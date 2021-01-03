package com.lhc.report.service;

import com.lhc.report.vo.WeatherResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author lhc
 */
@FeignClient("weather-data-eureka")
public interface WeatherDataClient {

    /**
     * 根据城市id获取天气信息
     * @param cityId 城市id
     * @return 天气信息
     */
    @GetMapping("/weather/cityId/{cityId}")
    WeatherResponse getReportByCityId(@PathVariable("cityId") String cityId);
}

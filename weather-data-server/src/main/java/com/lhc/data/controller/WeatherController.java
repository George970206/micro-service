package com.lhc.data.controller;


import com.lhc.data.service.WeatherDataService;
import com.lhc.data.vo.WeatherResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lhc
 */
@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherDataService weatherDataService;

    public WeatherController(WeatherDataService weatherDataService) {
        this.weatherDataService = weatherDataService;
    }

    @GetMapping("/cityId/{cityId}")
    public WeatherResponse getReportByCityId(@PathVariable String cityId){
        return weatherDataService.getDataByCityId(cityId);
    }

    @GetMapping("/cityName/{cityName}")
    public WeatherResponse getReportByCityName(@PathVariable String cityName){
        return weatherDataService.getDataByCityName(cityName);
    }
}

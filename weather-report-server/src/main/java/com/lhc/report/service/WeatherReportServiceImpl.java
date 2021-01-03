package com.lhc.report.service;

import com.lhc.report.vo.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 天气预报服务.
 * 
 * @since 1.0.0 2017年10月25日
 * @author <a href="https://waylau.com">Way Lau</a> 
 */
@Service
public class WeatherReportServiceImpl implements WeatherReportService {

	@Autowired
	private WeatherDataClient weatherDataClient;

	@Override
	public Weather getDataByCityId(String cityId) {
		// 由天气数据API微服务来提供数据
		return weatherDataClient.getReportByCityId(cityId).getData();
	}

}
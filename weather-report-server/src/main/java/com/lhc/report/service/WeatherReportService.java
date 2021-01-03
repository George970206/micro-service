package com.lhc.report.service;


import com.lhc.report.vo.Weather;

/**
 * 天气预报服务.
 * 
 * @since 1.0.0 2017年10月25日
 * @author <a href="https://waylau.com">Way Lau</a>
 */
public interface WeatherReportService {

	/**
	 * 根据城市ID查询天气信息
	 * 
	 * @param cityId
	 * @return
	 */
	Weather getDataByCityId(String cityId);

}

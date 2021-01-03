package com.lhc.weather.service;


import com.lhc.weather.vo.WeatherResponse;

/**
 * 天气数据服务.
 * 
 * @since 1.0.0 2017年10月18日
 * @author <a href="https://waylau.com">Way Lau</a>
 */
public interface WeatherDataService {

	/**
	 * 根据城市ID查询天气数据
	 * 
	 * @param cityId 城市id
	 * @return response
	 */
	WeatherResponse getDataByCityId(String cityId);

	/**
	 * 根据城市名称查询天气数据
	 * 
	 * @param cityName 城市名称
	 * @return response
	 */
	WeatherResponse getDataByCityName(String cityName);
	
	/**
	 * 根据城市ID同步天气数据
	 * 
	 * @param cityId 城市id
	 */
	void syncDataByCityId(String cityId);

}

package com.lhc.weather.service;

import com.lhc.weather.vo.City;

import java.util.List;

/**
 * @author lhc
 */
public interface CityDataService {

    /**
     * 获取城市列表
     * @return list
     * @throws Exception xml
     */
    List<City> listCity() throws Exception;

}

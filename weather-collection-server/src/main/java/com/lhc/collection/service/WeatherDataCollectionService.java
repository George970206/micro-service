package com.lhc.collection.service;

/**
 * @author lhc
 */
public interface WeatherDataCollectionService {

    /**
     * 根据城市ID同步天气数据
     * @param cityId 城市id
     */
    void syncDataByCityId(String cityId);

}

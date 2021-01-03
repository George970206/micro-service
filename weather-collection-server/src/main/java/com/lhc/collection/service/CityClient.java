package com.lhc.collection.service;

import com.lhc.collection.vo.City;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author lhc
 */
@FeignClient(name = "weather-city-eureka")
public interface CityClient {

    /**
     *  城市数据 api的查询接口
     * @return 所有城市信息
     * @throws Exception 读取 xml文件的IO异常
     */
    @GetMapping("/cities")
    List<City> listCity() throws Exception;

}

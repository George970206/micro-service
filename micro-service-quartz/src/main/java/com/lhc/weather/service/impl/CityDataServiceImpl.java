package com.lhc.weather.service.impl;

import com.lhc.weather.service.CityDataService;
import com.lhc.weather.util.XmlBuilder;
import com.lhc.weather.vo.City;
import com.lhc.weather.vo.CityList;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author lhc
 */
@Service
public class CityDataServiceImpl implements CityDataService {


    @Override
    public List<City> listCity() throws Exception {
        // 读取xml文件
        ClassPathResource resource = new ClassPathResource("cityList.xml");
        BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8));
        final StringBuilder builder = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null){
            builder.append(line);
        }
        reader.close();
        // xml转Java对象
        CityList cityList = (CityList) XmlBuilder.xmlStrToObject(CityList.class, builder.toString());

        return cityList.getCityList();
    }
}

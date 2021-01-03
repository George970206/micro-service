package com.lhc.city.service;

import com.lhc.city.util.XmlBuilder;
import com.lhc.city.vo.City;
import com.lhc.city.vo.CityList;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;


/**
 * 城市数据服务.
 * 
 * @since 1.0.0 2017年10月23日
 * @author <a href="https://waylau.com">Way Lau</a>
 */
@Service
public class CityDataServiceImpl implements CityDataService {

	@Override
	public List<City> listCity() throws Exception {
		// 读取XML文件
		Resource resource = new ClassPathResource("citylist.xml");
		BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8));
		StringBuilder buffer = new StringBuilder();
		String line = "";

		while ((line = br.readLine()) != null) {
			buffer.append(line);
		}

		br.close();

		// XML转为Java对象
		CityList cityList = (CityList) XmlBuilder.xmlStrToObject(CityList.class, buffer.toString());

		return cityList.getCityList();
	}

}

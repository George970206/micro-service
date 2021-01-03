package com.lhc.city.controller;

import com.lhc.city.service.CityDataService;
import com.lhc.city.vo.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;



/**
 * 城市API.
 * 
 * @since 1.0.0 2017年10月30日
 * @author <a href="https://waylau.com">Way Lau</a>
 */
@RestController
@RequestMapping("/cities")
public class CityController {

	@Autowired
	private CityDataService cityDataService;

	@GetMapping
	public List<City> listCity() throws Exception {
		return cityDataService.listCity();
	}

}

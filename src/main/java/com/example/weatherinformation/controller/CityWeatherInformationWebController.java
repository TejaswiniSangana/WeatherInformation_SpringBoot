package com.example.weatherinformation.controller;

import com.example.weatherinformation.business.domain.CityWeatherInformation;
import com.example.weatherinformation.business.service.CityWeatherInformationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/city-weather-information")
public class CityWeatherInformationWebController {
    private final CityWeatherInformationService cityWeatherInformationService;

    public CityWeatherInformationWebController(CityWeatherInformationService cityWeatherInformationService) {
        this.cityWeatherInformationService = cityWeatherInformationService;
    }

    @GetMapping
    public String getCityWeatherInfo(Model model){
        List<CityWeatherInformation> cityWeatherInformationList=this.cityWeatherInformationService.getCityWeatherInformation();
        model.addAttribute("citiesWeatherInformation", cityWeatherInformationList);
        return "city_weather_information";
    }
}

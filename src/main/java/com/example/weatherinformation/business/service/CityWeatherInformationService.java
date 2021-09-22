package com.example.weatherinformation.business.service;

import com.example.weatherinformation.business.domain.CityWeatherInformation;
import com.example.weatherinformation.data.entity.City;
import com.example.weatherinformation.data.entity.Weather;
import com.example.weatherinformation.data.repository.CityRepository;
import com.example.weatherinformation.data.repository.WeatherRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CityWeatherInformationService {
    private final CityRepository cityRepository;
    private final WeatherRepository weatherRepository;

    public CityWeatherInformationService(CityRepository cityRepository, WeatherRepository weatherRepository) {
        this.cityRepository = cityRepository;
        this.weatherRepository = weatherRepository;
    }

    public List<CityWeatherInformation> getCityWeatherInformation(){
        Iterable<City> cities = this.cityRepository.findAll();
        Map<Long, CityWeatherInformation> cityWeatherInformationMap= new HashMap<>();
        cities.forEach(city->{
            CityWeatherInformation cityInformation= new CityWeatherInformation();
            cityInformation.setCityId(city.getCityId());
            cityInformation.setCityName(city.getCityName());
            cityInformation.setStateCode(city.getStateCode());
            cityInformation.setCountryCode(city.getCountryCode());
            cityInformation.setCountryName(city.getCountryName());
            cityInformation.setLatitude(city.getLatitude());
            cityInformation.setLongitude(city.getLongitude());
            cityInformation.setWeatherCode(city.getWeatherCode());
            cityInformation.setObservationDate(city.getObservationDate());
            Weather weather= this.weatherRepository.findById(city.getWeatherCode()).get();
            cityInformation.setDescription(weather.getDescription());
            cityWeatherInformationMap.put(city.getCityId(), cityInformation);
        });
        List<CityWeatherInformation> weatherInformationList = new ArrayList<>();
        for(Long id:cityWeatherInformationMap.keySet()){
            weatherInformationList.add(cityWeatherInformationMap.get(id));
        }
        return weatherInformationList;
    }
}

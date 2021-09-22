package com.example.weatherinformation.data.repository;

import com.example.weatherinformation.data.entity.Weather;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends CrudRepository<Weather,Integer> {
}

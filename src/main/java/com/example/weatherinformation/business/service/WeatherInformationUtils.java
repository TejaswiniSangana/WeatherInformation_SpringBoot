package com.example.weatherinformation.business.service;

import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class WeatherInformationUtils {
    private WeatherInformationUtils(){}
    public static Map<String,Object> getWeatherData(Response response){
        JSONObject jsonObject= new JSONObject(response.asString());
        JSONArray weatherData=(JSONArray) jsonObject.get("data");
        JSONObject data= (JSONObject) weatherData.get(0);
        Map<String,Object> cityWeatherInfo = new HashMap<>();
        cityWeatherInfo.put("CityName", data.get("city_name"));
        cityWeatherInfo.put("CountryCode",data.get("country_code"));
        cityWeatherInfo.put("Latitude",data.get("lat"));
        cityWeatherInfo.put("Longitude", data.get("lon"));
        JSONObject weatherInfo=data.getJSONObject("weather");
        cityWeatherInfo.put("WeatherCode",weatherInfo.get("code"));
        return cityWeatherInfo;
    }
}

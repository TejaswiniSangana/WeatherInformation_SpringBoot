package com.example.weatherinformation.controllerTest;

import com.example.weatherinformation.business.domain.CityWeatherInformation;
import com.example.weatherinformation.business.service.CityWeatherInformationService;
import com.example.weatherinformation.business.service.WeatherInformationUtils;
import com.example.weatherinformation.constant.Constants;
import com.example.weatherinformation.controller.CityWeatherInformationWebController;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(CityWeatherInformationWebController.class)
public class CityWeatherInformationControllerTest {
    @MockBean
    private CityWeatherInformationService cityWeatherInformationService;

    SoftAssertions softAssertions;

    @Test
    public void testWeatherInformationService(){
        List<CityWeatherInformation> cityWeatherInformationList = this.cityWeatherInformationService.getCityWeatherInformation();
        cityWeatherInformationList.forEach(cityWeatherInformation -> {
            Response response= RestAssured.given()
                    .when()
                    .get("current?city_id={city_id}&key={key}",cityWeatherInformation.getCityId(), Constants.KEY);
            Map<String,Object> dataFromWeatherAPI =WeatherInformationUtils.getWeatherData(response);
            softAssertions=new SoftAssertions();
            softAssertions.assertThat(dataFromWeatherAPI.get("CityName").equals(cityWeatherInformation.getCityName()));
            softAssertions.assertThat(dataFromWeatherAPI.get("CountryCode").equals(cityWeatherInformation.getCountryCode()));
            softAssertions.assertThat(dataFromWeatherAPI.get("Latitude").equals(cityWeatherInformation.getLatitude()));
            softAssertions.assertThat(dataFromWeatherAPI.get("Longitude").equals(cityWeatherInformation.getLongitude()));
            softAssertions.assertThat(dataFromWeatherAPI.get("WeatherCode").equals(cityWeatherInformation.getWeatherCode()));
            softAssertions.assertAll();
        });
    }

}

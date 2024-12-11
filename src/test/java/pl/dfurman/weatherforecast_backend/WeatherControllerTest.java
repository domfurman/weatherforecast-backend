package pl.dfurman.weatherforecast_backend;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pl.dfurman.weatherforecast_backend.weather.Weather;
import pl.dfurman.weatherforecast_backend.weather.WeatherController;
import pl.dfurman.weatherforecast_backend.weather.WeatherService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WeatherController.class)
public class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherService weatherService;

    @Test
    public void testGetWeatherWithValidCoordinates() throws Exception {
        Weather mockWeatherData = new Weather();
        Mockito.when(weatherService.getForecastFor7Days(50.0, 19.0)).thenReturn(mockWeatherData);
        mockMvc.perform(get("/weather?latitude=50.0&longitude=19.0"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetWeatherWithInvalidCoordinates() throws Exception {
        mockMvc.perform(get("/weather?latitude=-91.0&longitude=19.0"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Invalid coordinates"));
    }
}


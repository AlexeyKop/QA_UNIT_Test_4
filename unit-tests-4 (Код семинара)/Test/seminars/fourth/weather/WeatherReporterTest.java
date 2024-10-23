package seminars.fourth.weather;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WeatherReporterTest {
    // проверяем что отчет содержит ожидаемую информацию о температуре
    @Test
    public void testWeatherReporter(){
        // создаем мок для WeatherService
        WeatherService mockWeatherService = mock(WeatherService.class);

        // определяем поведение мока. Когда метод getCurrentTemperature() будет вызван,
        // он вернет 30
        when(mockWeatherService.getCurrentTemperature()).thenReturn(30);

        // Создаем объект класса WeatherReporter, передаем ему в конструктор наш мок
        WeatherReporter weatherReporter = new WeatherReporter(mockWeatherService);

        // вызываем метод generateReport()
        String report = weatherReporter.generateReport();

        // проверяем, что отчет  содержит ожидаемую информацию о температуре
        assertEquals("Текущая температура: 30 градусов.", report);
    }


}
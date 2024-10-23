package seminars.fourth.database;


import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DataProcessorTest {
    @Test
    public void testProcessData(){
        // создаем мок для Database
        Database database = mock(Database.class);

        // настраиваем его поведение
        when(database.query(anyString())).thenReturn(Arrays.asList("First", "Second", "Third"));

        // создаем объект класса, который мы хотим протестировать, передавая мок в качестве зависимости
        DataProcessor dataProcessor = new DataProcessor(database);

        // Вызываем метод, который хотим протестировать
        List<String> resultSize = dataProcessor.processData("SELECT * FROM table");

        // Проверяем результат
        assertEquals(3, resultSize.size());
    }

}
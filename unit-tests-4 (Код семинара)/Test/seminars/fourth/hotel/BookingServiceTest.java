package seminars.fourth.hotel;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.anyInt;

class BookingServiceTest {
    @Test
    public void testBookingService(){
        // создание мока
        HotelService mockHotelService = mock(HotelService.class);

        // Определение поведения мока
        when(mockHotelService.isRoomAvailable(anyInt())).thenAnswer(i -> (Integer) i.getArgument(0) % 2 == 0);

        // Создание экземпляра тестируемого класса с подставленным моком
        BookingService bookingService = new BookingService(mockHotelService);

        // проверка метода bookRoom() с номером комнаты, который является доступным (четным)
        assertTrue(bookingService.bookRoom(2));

        // проверка метода bookRoom() с номером комнаты, который является недоступным (нечетным)
        assertFalse(bookingService.bookRoom(3));

        // Проверка, что метод isRoomAvailable() вызывается 2 раза
        verify(mockHotelService, times(2)).isRoomAvailable(anyInt());
    }

}
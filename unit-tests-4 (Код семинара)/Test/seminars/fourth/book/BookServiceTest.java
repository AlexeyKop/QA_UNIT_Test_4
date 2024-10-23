package seminars.fourth.book;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {

    private BookRepository bookRepository; // Объявляем интерфейс для мока репозитория
    private BookService bookService; // Объявляем объект класса, который будем тестировать

    // Этот метод выполнится перед каждым тестом
    @BeforeEach
    void setUp() {
        // Создаем мок-объект BookRepository с помощью Mockito
        bookRepository = Mockito.mock(BookRepository.class);
        // Создаем экземпляр BookService, передавая мок BookRepository в конструктор
        bookService = new BookService(bookRepository);
    }

    // Тестируем метод findBookById()
    @Test
    void testFindBookById() {
        // Подготавливаем данные для теста
        Book mockBook = new Book("1", "Test Book", "Test Author");
        // Настраиваем поведение мока: если вызывается метод findById с аргументом "1", он должен вернуть mockBook
        when(bookRepository.findById("1")).thenReturn(mockBook);

        // Вызываем метод, который мы тестируем
        Book result = bookService.findBookById("1");

        // Проверяем, что результат не равен null
        assertNotNull(result);
        // Проверяем, что результат содержит правильные данные
        assertEquals("1", result.getId());
        assertEquals("Test Book", result.getTitle());
        assertEquals("Test Author", result.getAuthor());

        // Проверяем, что метод findById был вызван только один раз
        verify(bookRepository, times(1)).findById("1");
    }

    // Тестируем метод findAllBooks()
    @Test
    void testFindAllBooks() {
        // Подготавливаем список книг для теста
        List<Book> mockBooks = Arrays.asList(
                new Book("1", "Book1", "Author1"),
                new Book("2", "Book2", "Author2")
        );
        // Настраиваем поведение мока: findAll должен вернуть подготовленный список книг
        when(bookRepository.findAll()).thenReturn(mockBooks);

        // Вызываем метод, который мы тестируем
        List<Book> result = bookService.findAllBooks();

        // Проверяем, что результат не пуст
        assertFalse(result.isEmpty());
        // Проверяем, что количество книг в результате соответствует ожиданиям
        assertEquals(2, result.size());
        // Проверяем, что первая книга имеет правильные данные
        assertEquals("1", result.get(0).getId());
        assertEquals("Book1", result.get(0).getTitle());

        // Проверяем, что метод findAll был вызван один раз
        verify(bookRepository, times(1)).findAll();
    }
}

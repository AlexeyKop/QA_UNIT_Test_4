

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import seminars.fourth.book.BookRepository;
import seminars.fourth.book.BookService;
import seminars.fourth.book.Book;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {

    private BookRepository bookRepository;
    private BookService bookService;


    @BeforeEach
    void setUp() {

        bookRepository = Mockito.mock(BookRepository.class);

        bookService = new BookService(bookRepository);
    }


    @Test
    void testFindBookById() {

        Book mockBook = new Book("1", "Test Book", "Test Author");

        when(bookRepository.findById("1")).thenReturn(mockBook);


        Book result = bookService.findBookById("1");


        assertNotNull(result);

        assertEquals("1", result.getId());
        assertEquals("Test Book", result.getTitle());
        assertEquals("Test Author", result.getAuthor());


        verify(bookRepository, times(1)).findById("1");
    }


    @Test
    void testFindAllBooks() {

        List<Book> mockBooks = Arrays.asList(
                new Book("1", "Book1", "Author1"),
                new Book("2", "Book2", "Author2")
        );

        when(bookRepository.findAll()).thenReturn(mockBooks);


        List<Book> result = bookService.findAllBooks();


        assertFalse(result.isEmpty());

        assertEquals(2, result.size());

        assertEquals("1", result.get(0).getId());
        assertEquals("Book1", result.get(0).getTitle());


        verify(bookRepository, times(1)).findAll();
    }
}

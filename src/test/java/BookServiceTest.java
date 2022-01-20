import com.example.Studentlibrary.StudentlibraryApplication;
import com.example.Studentlibrary.entity.BookEntity;
import com.example.Studentlibrary.exception.BookException;
import com.example.Studentlibrary.repository.BookRepo;
import com.example.Studentlibrary.service.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes = StudentlibraryApplication.class)
public class BookServiceTest {

    @Autowired
    BookService bookService;

    @Autowired
    BookRepo bookRepo;


    public void cleanAll() {
        bookRepo.deleteAll();
    }

    @Test
    public void newBookTest() {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setAuthor("author1");
        bookEntity.setTitle("title1");
        BookEntity savedBookEntity = bookService.bookNew(bookEntity);
        Assertions.assertNotNull(savedBookEntity);
        Assertions.assertEquals(savedBookEntity.getAuthor(), bookEntity.getAuthor());
        Assertions.assertEquals(savedBookEntity.getTitle(), bookEntity.getTitle());
        cleanAll();
    }

    @Test
    public void newBookTestException() {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setAuthor("author1");
        bookEntity.setTitle("title1");
        bookService.bookNew(bookEntity);
        BookEntity failedBookEntity = new BookEntity();
        failedBookEntity.setTitle("title1");
        Assertions.assertThrows(BookException.class, () -> bookService.bookNew(bookEntity));
        cleanAll();
    }

}

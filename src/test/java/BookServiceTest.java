import com.example.studentlibrary.StudentlibraryApplication;
import com.example.studentlibrary.dto.Book;
import com.example.studentlibrary.dto.Student;
import com.example.studentlibrary.entity.BookEntity;
import com.example.studentlibrary.entity.StudentEntity;
import com.example.studentlibrary.exception.BookNotFoundException;
import com.example.studentlibrary.repository.BookRepo;
import com.example.studentlibrary.repository.StudentRepo;
import com.example.studentlibrary.service.BookService;
import com.example.studentlibrary.service.StudentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes = StudentlibraryApplication.class)
public class BookServiceTest {

    @Autowired
    BookService bookService;
    @Autowired
    StudentService studentService;

    @Autowired
    BookRepo bookRepo;
    @Autowired
    StudentRepo studentRepo;


    public void cleanAll() {
        bookRepo.deleteAll();
        studentRepo.deleteAll();
    }

    @Test
    public void newBookTest() {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setAuthor("author1");
        bookEntity.setTitle("title1");
        Book savedBook = bookService.createBook(bookEntity);
        Assertions.assertNotNull(savedBook);
        Assertions.assertEquals(savedBook.getAuthorName(), bookEntity.getAuthor());
        Assertions.assertEquals(savedBook.getTitle(), bookEntity.getTitle());
        cleanAll();
    }

    @Test
    public void newBookTestException() {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setAuthor("author1");
        bookEntity.setTitle("title1");
        bookService.createBook(bookEntity);
        BookEntity failedBookEntity = new BookEntity();
        failedBookEntity.setTitle("title1");
        Assertions.assertThrows(BookNotFoundException.class, () -> bookService.createBook(bookEntity));
        cleanAll();
    }
    @Test
    public void bookSearchByIdTest(){
        BookEntity bookEntity = new BookEntity();
        bookEntity.setAuthor("author1");
        bookEntity.setTitle("title1");
        Book savedBook = bookService.createBook(bookEntity);
        Book bookSearchById = bookService.getBookById(savedBook.getId());
        Assertions.assertEquals(bookSearchById.getAuthorName(), bookEntity.getAuthor());
        Assertions.assertEquals(bookSearchById.getTitle(), bookEntity.getTitle());
    }
//    @Test
//    public void deletingBookByIbTest(){
//        BookEntity bookEntity = new BookEntity();
//        bookEntity.setAuthor("author1");
//        bookEntity.setTitle("title1");
//        Book savedBook = bookService.createBook(bookEntity);
//        bookService.deleteBook(savedBook.getId());
//        Assertions.assertEquals("NO_CONTENT",bookService.deleteBook(savedBook.getId()));
//    }

}

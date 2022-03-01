import com.example.studentlibrary.StudentlibraryApplication;
import com.example.studentlibrary.dto.Student;
import com.example.studentlibrary.entity.StudentEntity;
import com.example.studentlibrary.exception.StudentNotFoundExeption;
import com.example.studentlibrary.repository.BookRepo;
import com.example.studentlibrary.repository.StudentRepo;
import com.example.studentlibrary.service.BookService;
import com.example.studentlibrary.service.StudentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = StudentlibraryApplication.class)
public class StudentServiseTest {

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
    public void newStudentTest(){
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setNameStudent("Vasia Pupkin1");
        studentEntity.setFaculty("Prog");
        Student saveStudent = studentService.createStudent(studentEntity);
        Assertions.assertNotNull(saveStudent);
        Assertions.assertEquals(saveStudent.getStudentName(), studentEntity.getNameStudent());
        Assertions.assertEquals(saveStudent.getFaculty(), studentEntity.getFaculty());
    }
//    @Test
//    public void newStudentTestException(){
//        StudentEntity studentEntity = new StudentEntity();
//        studentEntity.setNameStudent("Vasia Pupkin");
//        studentEntity.setFaculty("Prog");
//        studentService.createStudent(studentEntity);
//        StudentEntity failedStudentEntity = new StudentEntity();
//        failedStudentEntity.setNameStudent("Vasia Pupkin");
//        Assertions.assertThrows(StudentNotFoundExeption.class, ()->studentService.createStudent(studentEntity));
//        cleanAll();
//    }

    @Test
    public void studentSearchByIdTest(){
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setNameStudent("Vasia Pupkin1");
        studentEntity.setFaculty("Prog");
        Student saveStudent = studentService.createStudent(studentEntity);
        Student studentSearchById = studentService.getStudentById(saveStudent.getId());
        Assertions.assertEquals(studentSearchById.getStudentName(),studentEntity.getNameStudent());
        Assertions.assertEquals(studentSearchById.getFaculty(),studentEntity.getFaculty());
    }
    @Test
    public void daletingStudentByIdTest(){
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setNameStudent("Vasia Pupkin1");
        studentEntity.setFaculty("Prog");
        Student saveStudent = studentService.createStudent(studentEntity);
        studentService.deleteStudent(saveStudent.getId());
        Assertions.assertThrows(StudentNotFoundExeption.class,()->studentService.getStudentById(saveStudent.getId()));
    }
    @Test
    public void updateStudentTest(){
        StudentEntity studentEntityOne = new StudentEntity();
        studentEntityOne.setNameStudent("Vasia Pupkin1");
        studentEntityOne.setFaculty("Prog");
        studentService.createStudent(studentEntityOne);
        StudentEntity studentEntityTwo = new StudentEntity();
        studentEntityTwo.setNameStudent("Vasia Pupkin");
        studentEntityTwo.setFaculty("Prog1");
        studentService.createStudent(studentEntityTwo);
        Student studentUpdate = studentService.updateStudent(studentEntityOne.getId(), studentEntityTwo);
        Assertions.assertEquals(studentUpdate.getId(), studentEntityOne.getId());
        Assertions.assertEquals(studentUpdate.getStudentName(),studentEntityTwo.getNameStudent());
        Assertions.assertEquals(studentUpdate.getFaculty(),studentEntityTwo.getFaculty());

    }

//    @Test
//    public void addBookStudentTest (){
//        BookEntity bookEntity = new BookEntity();
//        bookEntity.setAuthor("author");
//        bookEntity.setTitle("title");
//        Book saveBook = bookService.createBook(bookEntity);
//        StudentEntity studentEntity = new StudentEntity();
//        studentEntity.setNameStudent("Pavlic Morozov");
//        studentEntity.setFaculty("Prom");
//        Student saveStudent = studentService.createStudent(studentEntity);
//        Student student = studentService.addBookStudent(saveBook.getId(),saveStudent.getId());
//        System.out.println(student.getBooks());
//    }


}

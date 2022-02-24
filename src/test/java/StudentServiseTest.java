import com.example.studentlibrary.StudentlibraryApplication;
import com.example.studentlibrary.dto.Student;
import com.example.studentlibrary.entity.StudentEntity;
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



}

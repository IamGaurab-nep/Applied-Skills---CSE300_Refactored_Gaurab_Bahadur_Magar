import java.io.IOException;
import java.util.List;

interface StudentRepository {
    void save(Student student) throws IOException;
    List<Student> findAll();
}

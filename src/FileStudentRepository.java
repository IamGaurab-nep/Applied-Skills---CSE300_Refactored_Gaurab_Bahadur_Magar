import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class FileStudentRepository implements StudentRepository {
    private final String filePath;

    public FileStudentRepository(String filePath) {
        this.filePath = filePath;
    }

    public void save(Student student) throws IOException {
        try (FileWriter fw = new FileWriter(filePath, true)) {
            fw.write(student.toFileString() + "\n");
        }
    }

    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists())
            return students; // no students yet

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int lineNo = 0;
            while ((line = br.readLine()) != null) {
                lineNo++;
                String[] p = line.split("\\|");
                if (p.length != 5) {
                    System.out.println("Warning: skipped malformed line " + lineNo);
                    continue;
                }
                try {
                    students.add(new Student(p[0], p[1],
                        Integer.parseInt(p[2]),
                        Integer.parseInt(p[3]),
                        Integer.parseInt(p[4])));
                } catch (IllegalArgumentException e) {
                    System.out.println("Warning: skipped invalid line " + lineNo);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("File error: " + e.getMessage());
        }
        return students;
    }
}

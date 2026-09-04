import java.util.Objects;
class Student {
    private final String name;
    private final String id;
    private final int math, science, english;
    public Student(String name, String id, int math,
                   int science, int english) {
        if (name == null || name.isBlank() || id == null || id.isBlank())
            throw new IllegalArgumentException("Name and ID are required");
        if (name.contains("|") || id.contains("|"))
            throw new IllegalArgumentException("Name/ID cannot contain |");
        if (!validScore(math) || !validScore(science) || !validScore(english))
            throw new IllegalArgumentException("Scores must be 0-100");
        this.name = name;
        this.id = id;
        this.math = math;
        this.science = science;
        this.english = english;
    }
    private boolean validScore(int score) {
        return score >= 0 && score <= 100;
    }
    public double getAverage() {
        return (math + science + english) / 3.0;
    }
    public String getGrade() {
        double a = getAverage();
        if (a >= 90) return "A";
        if (a >= 80) return "B";
        if (a >= 70) return "C";
        return "F";
    }
    public String toFileString() {
        return name + "|" + id + "|" + math + "|" + science + "|" + english;
    }
    public String getName() { return name; }
    public String getId() { return id; }
    @Override
    public String toString() {
        return name + " (" + id + ")";
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student s = (Student) o;
        return id.equals(s.id);
    }
    @Override
    public int hashCode() { return Objects.hash(id); }
}

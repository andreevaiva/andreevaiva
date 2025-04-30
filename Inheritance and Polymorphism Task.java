public class Main {
    public static void main(String[] args) {
        UniversityCourses[] courses = {new MathCourse("Cambridge", 100, 1, false),
        new EnglishCourse("Cambridge", 75, 3, false),
        new MathCourse("SAT Prep Math", 300, 5, true),
        new EnglishCourse("SAT Prep English", 300, 7, true)};

        for(UniversityCourses course: courses){
            course.displayDetails();
            System.out.println("\nRequirements for Course: "+course.requirements());
        }
    }
}

class UniversityCourses{
    String nameOfSchool;
    int numberOfStudents;
    int numberOfTeachers;
    boolean isItPublic;

    public UniversityCourses(String nameOfSchool, int numberOfStudents, int numberOfTeachers, boolean isItPublic) {
        this.nameOfSchool = nameOfSchool;
        this.numberOfStudents = numberOfStudents;
        this.numberOfTeachers = numberOfTeachers;
        this.isItPublic = isItPublic;
    }

    public String getName() {
        return nameOfSchool;
    }
    public int getNumberOfStudents() {
        return numberOfStudents;
    }
    public int getNumberOfTeachers() {
        return numberOfTeachers;
    }

    public boolean isItPublic() {
        return isItPublic;
    }

    public String requirements(){
        return "no requirements";
    }

    public int getLength(){
        return 0;
    }

    public void displayDetails(){
        System.out.print("\nname: "+nameOfSchool+"\nnumber of students: "+numberOfStudents+"\nnumber of teachers: "+numberOfTeachers+"\nis it public: "+isItPublic);
    }
}

class MathCourse extends UniversityCourses{
    int length = 240;
      public MathCourse(String nameOfSchool, int numberOfStudents, int numberOfTeachers, boolean isItPublic) {
        super(nameOfSchool, numberOfStudents, numberOfTeachers, isItPublic);
    }

    public String requirements(){
          return "Math matura, SAT 780 in Maths, GMAT";
    }
    public int getLength(){
          return length;
    }
    public void displayDetails(){
        System.out.print("\nName: "+nameOfSchool+"\nNumber of students: "+numberOfStudents+"\nNumber of teachers: "+numberOfTeachers+"\nIs it public: "+isItPublic+"\nLength: "+length);
    }
}

class EnglishCourse extends UniversityCourses{
    int length = 360;
    public EnglishCourse(String nameOfSchool, int numberOfStudents, int numberOfTeachers, boolean isItPublic) {
        super(nameOfSchool, numberOfStudents, numberOfTeachers, isItPublic);
    }

    public String requirements(){
        return "English matura, SAT 750 in English, Cambridge C1 certificate";
    }
    public int getLength(){
        return length;
    }
    public void displayDetails(){
        System.out.print("\nName: "+nameOfSchool+"\nNumber of students: "+numberOfStudents+"\nNumber of teachers: "+numberOfTeachers+"\nIs it public: "+isItPublic+"\nLength: "+length);
    }
}

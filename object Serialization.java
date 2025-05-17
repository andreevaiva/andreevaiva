import java.io.*;
public class Main {
    public static void main(String[] args) {
        Employee employee = new Employee("Iva", 17, 2000);
        employee.writeToFile("employee.txt");
        employee.readFromFile("employee.txt");
    }
}

class Employee implements Serializable {
    String name;
    int age;
    int salary;

    public Employee(String name, int age, int salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public void writeToFile(String filename) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))){
            oos.writeObject(this);
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void readFromFile(String filename) {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))){
            try{
                while(true){
                    Employee obj = (Employee) ois.readObject();
                    System.out.println("Name: "+obj.name+"; Age: "+obj.age+"; Salary: "+obj.salary+";");
                }
            }catch(EOFException e){}
        }catch(IOException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

}

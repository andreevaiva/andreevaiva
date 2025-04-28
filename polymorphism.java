import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Animal[] animals = {new Chicken(1, "Emma", 3, "female"),
        new Chicken(2, "Bob", 4, "male"),
        new Cow(3, "Jack", 5, "male"),
        new Cow(4, "Jill", 6, "female"),
        new Donkey(5, "Mare", 7, "female"),
        new Donkey(6, "Miles", 8, "male")};

        for (Animal animal : animals) {
            System.out.println(animal.makeSound());
            System.out.println(animal.getName()+" produces "+animal.getProduct()+"\n");
        }

    }
}

class Animal{
    int id;
    String name;
    int age;
    String gender;

    public Animal(int id, String name, int age, String gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public String makeSound(){
        return "";
    }

    public String getProduct(){
        return "";
    }
}

class Chicken extends Animal{
    public Chicken(int id, String name, int age, String gender) {
        super(id, name, age, gender);
    }

    public String makeSound(){
        return "the chicken says: cluck cluck";
    }

    public String getProduct(){
        if(gender.equals("female")){
         return "eggs";
        }
        return "nothing";
    }
}

class Cow extends Animal{
    public Cow(int id, String name, int age, String gender) {
        super(id, name, age, gender);
    }

    public String makeSound(){
        return "the cow says: moo";
    }

    public String getProduct(){
        if(gender.equals("female")){
            return "milk";
        }
        return "nothing";
    }
}

class Donkey extends Animal{
    public Donkey(int id, String name, int age, String gender) {
        super(id, name, age, gender);
    }

    public String makeSound(){
        return "the donkey says: hee-haw";
    }

    public String getProduct(){
        if(gender.equals("female")){
            return "milk";
        }
        return "nothing";
    }
}

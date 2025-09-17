import java.util.*;
public class Main {
    public static void main(String[] args) {
        Duck duck = new Duck(1, "female", "yellow");
        duck.quack();

        Fish fish = new Fish(2, "male", 1, true);
        fish.swim();

        Zebra zebra = new Zebra(10, "female", false);
        zebra.run();
    }
}

abstract class Animal{
    int age;
    String gender;

    public Animal(int age, String gender){
        this.age=age;
        this.gender=gender;
    }

    abstract boolean isMammal();

    abstract boolean mate();
}

class Duck extends Animal{
    String beakColor;
    public Duck(int age, String gender, String beakColor){
        super(age, gender);
        this.beakColor=beakColor;
    }

    @Override
    boolean isMammal() {
        return false;
    }

    @Override
    boolean mate() {
        if(isMammal()) {
            return true;
        }
        return false;
    }

    void swim(){
        System.out.println("I am ready to Swim!");
    }

    void quack(){
        System.out.println("Quack!");
    }
}

class Fish extends Animal{
    int sizeInFt;
    boolean canEat;
    public Fish(int age, String gender, int sizeInFt, boolean canEat){
        super(age, gender);
        this.sizeInFt=sizeInFt;
        this.canEat=canEat;
    }

    @Override
    boolean isMammal() {
        return false;
    }

    @Override
    boolean mate() {
        if(isMammal()){
            return true;
        }
        return false;
    }

    void swim(){
        System.out.println("I am ready to Swim!");
    }
}

class Zebra extends Animal{
    boolean isWild;
    public Zebra(int age, String gender, boolean isWild){
        super(age, gender);
        this.isWild=isWild;
    }

    @Override
    boolean isMammal() {
        return true;
    }

    @Override
    boolean mate() {
        if(isMammal()){
            return true;
        }
        return false;
    }

    void run(){
        System.out.println("I am running really fast!");
    }
}
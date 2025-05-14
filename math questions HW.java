import java.io.*;
import java.util.Scanner;
import java.util.Random;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        write(name+"-answers.txt");
        System.out.println();
        read(name+"-answers.txt");
    }
    public static void write(String filename) {
        Random rand = new Random();
        Scanner scanner = new Scanner(System.in);
        try(DataOutputStream oS = new DataOutputStream(new FileOutputStream(filename))){
            oS.writeUTF("Results:");
            for(int i = 0; i<5; i++){
                int a=rand.nextInt(100);
                int b=rand.nextInt(100);
                System.out.print(a+"+"+b+"=");
                int answer = scanner.nextInt();
                boolean correct = answer == (a+b);

                oS.writeUTF(a+"+"+b+"="+answer+"; The answer is: "+correct);
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    public static void read(String filename) {
        try(DataInputStream iS = new DataInputStream(new FileInputStream(filename))){
            try{
                while(true){
                    String output = iS.readUTF();
                    System.out.println(output);
                }
            }catch (EOFException e){}
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}

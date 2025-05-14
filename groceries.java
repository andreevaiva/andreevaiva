import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter name of file: ");
        String fileName = scanner.nextLine();

        ArrayList groceries = new ArrayList();

        System.out.print("Enter grocerie: ");
        String input=scanner.nextLine();
        while(!input.equals("done")){
            if(input!=null){
                groceries.add(input);
            }
            System.out.print("Enter next grocerie: ");
            input=scanner.nextLine();
        }

        write(fileName, groceries);
        read(fileName);

    }

    public static void write(String fileName, ArrayList<String> groceries){
        try(DataOutputStream oS = new DataOutputStream(new FileOutputStream(fileName))){
            for(int i=1; i<=groceries.size(); i++){
                oS.writeUTF(i+". "+groceries.get(i-1)+"\n");
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void read(String fileName){
        try (DataInputStream iS = new DataInputStream(new FileInputStream(fileName)) ){
            try{
                int i=1;
                while(true){
                    String grocerie = iS.readUTF();
                    System.out.print(grocerie);
                    i++;
                }
            }catch (EOFException e){}
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}

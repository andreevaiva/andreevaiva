import java.util.Scanner;

public class UserAdministrator {
    public static void main(String[] args) {
        User iva=new Administrator();
        Administrator rosi=new Administrator();
        User tero=new User();
    }
}

class User{
    String userId;
    String password;
    String loginStatus;

    public User(){
        Scanner sc=new Scanner(System.in);
        do {
            userId = sc.nextLine();
        }while(!verifyLogin(userId));

        do{
            password=sc.nextLine();
        }while(!verifyLogin(password));

        do{
            loginStatus=sc.nextLine();
        }while(!verifyLogin(loginStatus));

        sc.close();
    }

    boolean verifyLogin(String input){
        if(input==null){
            return false;
        }
        return true;
    }
}

class Administrator extends User{
    String adminName;
    String email;

    public Administrator(){
        Scanner sc = new Scanner(System.in);
        adminName=sc.nextLine();
        email=sc.nextLine();
        sc.close();
    }

    boolean updateCatalog(){
        Scanner sc=new Scanner(System.in);
        String input;
        do {
            input = sc.nextLine();
        }while(input==null);
        String newName=input.split(" ")[0];
        String newEmail=input.split(" ")[1];
        sc.close();
        if(verifyLogin(newName)){
            if(verifyLogin(newEmail)){
                adminName=newName;
                email=newEmail;
                return true;
            }else{
                System.out.println("Invalid email");
            }
        }else{
            System.out.println("Invalid name");
        }
        return true;
    }
}
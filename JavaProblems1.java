import java.util.*;
public class JavaProblems1 {
   public static void main(String[] args) {
       Product nums = new Product();


   }
}


class Product{
   int[] arr;


   public Product(){
       Scanner scanner = new Scanner(System.in);
       int n= scanner.nextInt();
       int[] arr=new int[n];
       int max= scanner.nextInt();
       int secMax=-1;
       for(int i=1;i<n;i++){
           arr[i]=scanner.nextInt();
           if(arr[i]>max){
               secMax=max;
               max=arr[i];
           }
           if(arr[i]!=max && arr[i]>secMax){
               secMax=arr[i];
           }
       }
       scanner.close();
       System.out.println(secMax);


   }
}

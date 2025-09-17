import java.util.Scanner;

public class Zad26 {
    public static void main(String[] args) {
        Numbers nums=new Numbers();
        System.out.println("Max Element = "+nums.maxA());
        System.out.println("Max Digit = "+nums.maxDigit(nums.maxA()));
        System.out.println("next Max Digit = "+(nums.maxDigit(nums.maxA())+1));
    }
}

class Numbers{
    Scanner sc=new Scanner(System.in);
    int n=sc.nextInt();
    int[] numbers=new int[n];

    public Numbers(){
        for(int i=0;i<n;i++){
            numbers[i]=sc.nextInt();
        }
    }

    int maxA(){
        int max=numbers[0];
        for(int i=1;i<n;i++){
            if(max<numbers[i]){
                max=numbers[i];
            }
        }
        return max+1;
    }

    int maxDigit(int num){
        String nums=Integer.toString(num);
        int[] digits = new int[nums.length()];
        for(int i=0;i<nums.length();i++){
            digits[i]=Integer.parseInt(String.valueOf(nums.charAt(i)));
        }
        for(int i=0;i<digits.length;i++) {
            for(int j=i+1;j<digits.length;j++){
                int temp = 0;
                if(digits[i]<digits[j]){
                    temp=digits[i];
                    digits[i]=digits[j];
                    digits[j]=temp;
                }
            }
        }
        int answer=0;
        for(int i=0;i<digits.length;i++){
            answer+=digits[i]*(Math.pow(10,i));
        }
        return answer;
    }
}
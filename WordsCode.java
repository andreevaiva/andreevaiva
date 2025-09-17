import java.util.*;
import java.lang.Math;
import java.util.Arrays;

public class WordsCode {
    public static void main(String[] args) {
        Words words=new Words();
        System.out.println(words.findDistance());
    }
}

class Words{
    String [] words=new String[5];
    String word1;
    String word2;

    public Words(){
        Scanner sc=new Scanner(System.in);
        String input;
        for(int i=0;i<5;i++){
            do{
                input=sc.nextLine();
            }while(input==null);
            words[i]=input;
        }
        do {
            word1 = sc.nextLine();
        }while(word1==null);
        do {
            word2 = sc.nextLine();
        }while(word2==null);
        sc.close();
    }

    int findDistance(){
        int[] indexes1 = new int[5];
        int i1=0;
        int[] indexes2=new int[5];
        int i2=0;

        for(int i=0;i<5;i++){
            if(words[i].equals(word1)){
                indexes1[i1]=i;
                i1++;
            }
            if(words[i].equals(word2)){
                indexes2[i2]=i;
                i2++;
            }
        }

        int minimal=5;
        for(int i=0;i<i1;i++){
            for(int j=0;j<i2;j++){
                if(Math.abs(indexes1[i]-indexes2[j])<minimal){
                    minimal=Math.abs(indexes2[j]-indexes1[i]);
                }
            }
        }
        return minimal;
    }
}


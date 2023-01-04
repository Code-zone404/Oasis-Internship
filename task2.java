import java.util.Random;
import java.util.Scanner;
public class task2{
	public static void main(String [] args){
		Random random = new Random();
		int num=random.nextInt(100);
		// int num=random.nextInt(1,100);	//number is randomly selected here
		int guessed=-1;
		Scanner sc=new Scanner(System.in);
		int chances=0;
		while(guessed!=num){
			System.out.print("Guess number: ");
			guessed=sc.nextInt();
			if(guessed==num){
				System.out.println("Guessed RIGHT!!\nIn "+chances+" chances");
			}
			else if(num-guessed>=-5 && num-guessed<=5){
				System.out.println("Very close!!!");
				chances++;
			}
			else if(guessed>num){
				System.out.println("Guess lower");
				chances++;
			}
			else{
				System.out.println("Guess higher");
				chances++;
			}
		}
		sc.close();
	}
}
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

class atm{
	String user;
	int pin,balance=0;
	public atm(String user, int pin){
		this.user=user;
		this.pin=pin;
		System.out.println("Account created successfully("+this.user+")");
		try{
			FileWriter file=new FileWriter("bankdetails.txt");
			file.write(this.user+" data:\n");
			file.close();
		}
		catch(Exception e){
			System.out.println("Error occured");
		}
	}
	private void update_history(String data){
		try{
			FileWriter file=new FileWriter("bankdetails.txt",true);
			file.write(data+"\r");
			file.close();
		}
		catch(Exception e){
			System.out.println("Error occured");
		}
	}
	public String show_history(){
		String message="";
		try{
			File view=new File("bankdetails.txt");
			Scanner sc = new Scanner(view);
			while(sc.hasNextLine()){
				message+=sc.nextLine()+"\n";
			}
		}
		catch(Exception e){
			System.out.println("Error occured");
		}
		return message;
	}
	public String show_details(){
		return "The details of "+this.get_user()+" are as follows:\nBalance: "+this.get_balance();
	}
	private void add_bal(int amount){
		this.balance+=amount;
	}
	private void remove_bal(int amount){
		this.balance-=amount;
	}
	private String get_user(){
		return this.user;
	}
	private int get_balance(){
		return this.balance;
	}

	public String deposit(int amount){
		this.balance+=amount;
		String message=amount+" deposited successfully";
		update_history(amount+" credited");
		return message;
	}
	public String withdraw(int amount){
		if(this.balance>=amount){
			this.balance-=amount;
			String message=amount+" withdrawed successfully";
			update_history(amount+" debited");
			return message;
		}
		else{
			String reply="Unable to withdraw\nInsufficient Balance";
			return reply;
		}
	}
	public String transfer(atm user2){
		System.out.println("Confirm yourself("+this.user+").\nEnter your pin: ");
		Scanner sc = new Scanner(System.in);
		int temp=sc.nextInt();
		String message="";
		if(temp==this.pin){
			System.out.println("Enter amount to transfer to "+user2.user);
			temp=sc.nextInt();
			if(temp<=this.balance){
				user2.add_bal(temp);
				this.remove_bal(temp);
				update_history(temp+" transferred successfully to "+user2.user);
				message="Transfer process successfull";
				return message;
			}
			else{
				message="Transfer failed\nInsufficient balance";
				return message;
			}
		}
		else{
			message="Incorrect pin!";
			return message;
		}
	}
}

class task3{
	public static void main(String [] args){
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter your name: ");
		String name=sc.nextLine();
		System.out.print("Choose your pin: ");
		int pin=sc.nextInt();
		while(Integer.toString(pin).length()<4 || Integer.toString(pin).length()>4){
			System.out.println("Length of pin must me 4 digits only.\nEnter another pin: ");
			pin=sc.nextInt();
		}
		atm b= new atm("Ritesh",6789);
		atm a= new atm(name,pin);
		int temp=0,option=-1;
		String message="";
		while(option!=5){
			System.out.println("1. Transaction history");
			System.out.println("2. Withdraw");
			System.out.println("3. Deposit");
			System.out.println("4. Transfer");
			System.out.println("5. Quit");
			option=sc.nextInt();
			switch(option){
				case 1:
					message=a.show_history();
					message+="\nBalance: "+a.balance;
					break;
				case 2:
					System.out.println("Enter amount to withdraw: ");
					temp=sc.nextInt();
					message=a.withdraw(temp);
					break;
				case 3:
					System.out.println("Enter amount to deposit: ");
					temp=sc.nextInt();
					message=a.deposit(temp);
					break;
				case 4:
					message=a.transfer(b);
					break;
				case 5:
					break;
			}
			System.out.print("\033[H\033[2J");
			System.out.println("-->"+message);
		}
	}
}

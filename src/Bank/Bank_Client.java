package Bank;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Bank_Client {

	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		Scanner scn=new Scanner(System.in);
        Bank bank=new Bank();
        System.out.println("Welcome!!");
        System.out.println("To Open Account Press 1 ");
        System.out.println("For Cash Deposit Press 2");
        System.out.println("For Cash Withdrawal Press 3");
        System.out.println("To Check Balance Press 4");
        int choice=scn.nextInt();
        
        if(choice<1||choice>4) {
        	throw new Exception("Please Enter valid choice!");
        }
        if(choice==1) {
        	bank.New_Account();
        }else if(choice==2) {
        	bank.deposit();
        }else if(choice==3) {
        	bank.withdraw();
        }else if(choice==4) {
        	bank.balance();
        }
        
                
        
	}
	
}

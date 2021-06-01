package Bank;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;
public class Bank {
static Scanner scn=new Scanner(System.in);
public static void main(String[]args) throws Exception{
//	Bank B=new Bank();
//	B.display_accounts();
//	
//	B.New_Account();
//	B.New_Account();
//	B.New_Account();
		
	}
private HashMap<String,User>hm_name=new HashMap<>();
private HashMap<Long,User>hm_account=new HashMap<>();
private ArrayList<User>ar=new ArrayList<>();
private ArrayList<Statement>Statements=new ArrayList<>();


	private class User{
		private String name;
		private long account_no;
		private int amount=0;
	}
	 public Bank() throws Exception{
		User user1=new User();
		user1.name="user1";
		hm_name.put(user1.name, user1);
		generate_account_no(user1);
		user1.amount=20000;
		ar.add(user1);
		User user2=new User();
		user2.name="user2";
		hm_name.put(user2.name, user2);
		generate_account_no(user2);
		user1.amount=200;
		ar.add(user2);
		User user3=new User();
		user3.name="user3";
		hm_name.put(user3.name, user3);
		generate_account_no(user3);
		user3.amount=2500;
		ar.add(user3);
		User user4=new User();
		user4.name="user4";
		hm_name.put(user4.name, user4);
		generate_account_no(user4);
		user4.amount=5000;
		ar.add(user4);
	}
	
	
	
	public void Open_Account()throws Exception {
		System.out.println("Enter Name");
		String str=scn.next();
		if(hm_name.containsKey(str)) {
		  throw new Exception("Name Already Exists!");
		}else {
			User user=new User();
			user.name=str;
			generate_account_no(user);
			System.out.println(user.account_no);
			hm_name.put(user.name, user);
			ar.add(user);
			System.out.println("Your account has been created!!");
		}
	}
	private void generate_account_no(User user) throws Exception{
		
		Random rand = new Random();
		long drand = (long)(rand.nextDouble()*10000000000L);
		if(hm_account.containsKey(drand)) {
			 generate_account_no(user);
		}else {
			user.account_no=drand;
			hm_account.put(user.account_no,user);
			
		}
	}
	
	
	public void deposit()throws Exception {
		User user=getUser();
		
		System.out.println("Enter the deposit amount");
		int deposit_amount=scn.nextInt();
		deposit(user,deposit_amount);
		System.out.println("INR"+deposit_amount+" Added to Your Account");
		
	}
	private void deposit(User user,int deposit_amount)throws Exception {
		user.amount=user.amount+deposit_amount;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();
        String date=dtf.format(now)+"";
		String txn_type="cr";
		Statement st=new Statement();
		st.Date=date;
		st.txn_type=txn_type;
		st.mode=get_txn_mode();
		st.account_number=user.account_no;
		st.balance=user.amount;
		st.txn_amount=deposit_amount;
		Statements.add(st);
	}
	
	
	public void withdraw() throws Exception{
		User user=getUser();
		System.out.println("Enter the withdrawal amount");
		int withdraw_amount=scn.nextInt();
		if(withdraw_amount>user.amount) {
			throw new Exception("Insufficient fund");
		}else {
		withdraw(user,withdraw_amount);
		System.out.println("INR"+withdraw_amount+" Debited From Your Account");
		
		
		
		}
	}
	private void withdraw(User user,int withdraw_amount)throws Exception {
		user.amount=user.amount-withdraw_amount;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();
        String date=dtf.format(now)+"";
		String txn_type="db";
		Statement st=new Statement();
		st.Date=date;
		st.txn_type=txn_type;
		st.mode=get_txn_mode();
		st.account_number=user.account_no;
		st.balance=user.amount;
		st.txn_amount=withdraw_amount;
		Statements.add(st);
	}
	
	
	public void balance() throws Exception{
		User user=getUser();
		System.out.println("Your current account balance is : "+user.amount);
	}
	

	private User getUser()throws Exception {
	   System.out.println("Please Enter your account number");
	   Long num=scn.nextLong();
	   if(hm_account.containsKey(num)) {
		   return hm_account.get(num);
	   }else {
		   throw new Exception("Invalid");
	   }
	}
	
	private class Statement{
		String Date;
		String txn_type;
		long account_number;
		int txn_amount;
		int balance;
		String mode;
	}
	
	private String get_txn_mode()throws Exception {
		
		System.out.println("Choose Mode: ");
    	System.out.println("For Online Press 0 ");
    	System.out.println("For Cash Press 5 ");
    	System.out.println("For ATM Press 6 ");
    	int num=scn.nextInt();
    	if(num==0) {
    		return "Online";
    	}else if(num==5) {
    		return "Cash";
    	}else if(num==6) {
    		return "ATM";
    	}else {
    		throw new Exception("Invalid Mode");
    	}
	}
	public void display_statements() {
		for(int i=0;i<Statements.size();i++) {
			Statement st=Statements.get(i);
			System.out.println(st.Date+" "+st.txn_type+" "+st.account_number+" "+st.txn_amount+" "+st.balance+" "+st.mode);
		}
	}
	
	public void display_accounts() {
		for(int i=0;i<ar.size();i++) {
			User user=ar.get(i);
			System.out.println(user.account_no+" "+user.name+" "+user.amount);
					}
	}
	

}

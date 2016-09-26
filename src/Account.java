import java.util.ArrayList;
import java.util.HashMap;
//import java.util.Iterator;
import java.util.Scanner;
abstract public class Account {
private
	static double total = 0;
	String id;
	double balance;
	static HashMap<Date, AccountRecord> recordMap = new HashMap<Date, AccountRecord>();
protected
	Account(final Date date, final String id){
		this.id = id;
		balance = 0;
		date.show();
		System.out.println("\t#"+id+" created");
	}
	void record(final Date date, double amount, final String desc){
		amount = Math.floor(amount*100+0.5)/100;
		balance += amount;
		total += amount;
		date.show();
		System.out.println("\t#"+id+"\t"+amount+"\t"+balance+"\t"+desc);
	}
	final void error(final String msg){
		System.out.println("Error(#"+id+"): "+msg);
	}

public
	final String getId(){
		return id;
	}
	final double getBalance(){
		return balance;
	}
	static double getTotal(){
		return total;
	}
	abstract void deposit(final Date date, double amount, final String desc);
	abstract void withdraw(final Date date, double amount, final String desc);
	abstract void settle(final Date date) throws Exception;
	abstract void show();
	//{
		//System.out.print(id+"\tBalance: "+balance);
	//}

//	final static boolean whichIsBigger(final Date date1, final Date date2){
//		return date1.totalDays<=date2.totalDays;
//	}
//	static void query(final Date begin, final Date end){
//		if(whichIsBigger(begin, end)){
//			Iterator<HashMap<Date, AccountRecord>> it1 = (Iterator<HashMap<Date, AccountRecord>>) recordMap.get(begin);
//			Iterator<HashMap<Date, AccountRecord>> it2 = (Iterator<HashMap<Date, AccountRecord>>) recordMap.get(end);
//			for(Iterator<HashMap<Date, AccountRecord>> it = it1; it!=it2; it.hasNext())
//				System.out.println(it);
//		}
//	}
	
//public static void main(String[] args) {
//	Date date = new Date(2008, 11, 1);
//	SavingAccount sa1 = new SavingAccount(date, "S3755217", 0.015);
//	SavingAccount sa2 = new SavingAccount(date, "02342342", 0.015);
//	CreditAccount ca = new CreditAccount(date, "C5392394", 10000, 0.0005, 50);
//	sa1.deposit(new Date(2008, 11, 5), 5000, "salary");
//	ca.withdraw(new Date(2008, 11, 15), 2000, "buy a cell");
//	sa2.deposit(new Date(2008, 11, 25), 10000, "sell stock 0323");
//	ca.settle(new Date(2008, 12, 1));
//	ca.deposit(new Date(2008, 12, 1), 2016, "repay the credit");
//	sa1.deposit(new Date(2008, 12, 5), 5500, "salary");
//	sa1.settle(new Date(2009, 1, 1));
//	sa2.settle(new Date(2009, 1, 1));
//	ca.settle(new Date(2009, 1, 1));
//	System.out.println();
//	sa1.show();
//	System.out.println();
//	sa2.show();
//	System.out.println();
//	ca.show();
//	System.out.println();
//	System.out.println("Total: "+getTotal());
//}
	
	public static void main(String args[]) throws Exception{
		Date date = new Date(2008, 11, 1);
		//int x = 0;
		ArrayList<Account> accounts = new ArrayList<Account>();
//		SavingAccount sa1 = new SavingAccount(date, "S3755217", 0.015);
//		SavingAccount sa2 = new SavingAccount(date, "02342342", 0.015);
//		CreditAccount ca = new CreditAccount(date, "C5392394", 10000, 0.0005, 50);
//		Account accounts[] = {sa1, sa2, ca};
//		final int n = 3;
		System.out.println("(d)deposit (w)withdraw (s)show (c)change day (n)next month (e)exit");
		String cmd;
		Scanner sc = new Scanner(System.in);
		do{
			date.show();
			System.out.print("\tTotal: "+Account.getTotal()+"\tcommand>");
			int index, day;
			double amount, credit, rate, fee;
			String id, desc, type;
			cmd = sc.next();
			Account account;
			switch (cmd) {
				case "a":
					type = sc.next();
					char type1 = type.charAt(0);
					id = sc.next();
					if (type1=='s'){
						rate = sc.nextDouble();
						account = new SavingAccount(date, id, rate);
					}else {
						credit = sc.nextDouble();
						rate = sc.nextDouble();
						fee = sc.nextDouble();
						account = new CreditAccount(date, id, credit, rate, fee);
					}
					accounts.add(account);
					//x++;
					break;
				case "d":
					index = sc.nextInt();
					amount = sc.nextDouble();
					desc = sc.nextLine();
					accounts.get(index).deposit(date, amount, desc);
					break;
				case "w":
					index = sc.nextInt();
					amount = sc.nextDouble();
					desc = sc.nextLine();
					accounts.get(index).withdraw(date, amount, desc);
					break;
				case "s":
					for (int i=0; i<accounts.size(); i++){
						System.out.print("["+i+"] ");
						accounts.get(i).show();
						System.out.println();
					}
					break;
				case "c":
					day = sc.nextInt();
					if (day<date.getDay())
						System.out.println("You cannot specify a previous day");
					else if (day>date.getMaxDay())
						System.out.println("Invalid day");
					else
						date = new Date(date.getYear(), date.getMonth(), day);
					break;
				case "n":
					if (date.getMonth()==12)
						date = new Date(date.getYear()+1, 1, 1);
					else
						date = new Date(date.getYear(), date.getMonth()+1, 1);
					for (int i=0; i<accounts.size(); i++)
						accounts.get(i).settle(date);
					break;
//				case "q":
//					Date date1 = Date.read();
//					Date date2 = Date.read();
//					query(date1, date2);
//					break;
			}
		}while(!cmd.equals("e"));
		sc.close();
	}	
}
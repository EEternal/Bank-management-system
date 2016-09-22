public class AccountRecord {
private
	Date date;
	final Account account;
	double amount, balance;
	String desc;

public
	AccountRecord(final Date date, final Account account, double amount, double balance, String desc){
		this.date = date;
		this.account = account;
		this.amount = amount;
		this.balance = balance;
		this.desc = desc;
	}
	final void show(){
		date.show();
		System.out.println("\t#"+account.getId()+"\t"+amount+"\t"+balance+"\t"+desc);
	} 
}

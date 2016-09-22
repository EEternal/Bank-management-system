public class SavingAccount extends Account{
private
	Accumulator acc;
	double rate;
public
	SavingAccount(Date date, final String id, double rate){
		super(date, id);
		//new Account(date, id);
		this.rate = rate;
		acc = new Accumulator(date, 0);
	};
	final double getRate(){
		return rate;
	}
	void deposit(Date date, double amount, final String desc){
		record(date, amount, desc);
		acc.change(date, getBalance());
	}
	void withdraw(Date date, double amount, final String desc){
		if (amount>getBalance())
			System.out.println("Error:not enough money");
		else{
			record(date, -amount, desc);
			acc.change(date, getBalance());
		}
	}
	void settle(Date date){
		if (date.getMonth()==1){
			double interest = acc.getSum(date)*rate/date.distance(new Date(date.getYear()-1, 1, 1));
			if (interest!=0)
				record(date, interest, "interest");
			acc.reset(date, getBalance());
		}
	}
	@Override
	void show() {
		// TODO Auto-generated method stub
		System.out.print(id+"\tBalance: "+balance);
	} 

//public static void main(String args[]){
//	Date date = new Date(2008, 11, 1);
//	SavingAccount accounts[] = new SavingAccount[2];
//	accounts[0] = new SavingAccount(date, "03755217", 0.015);
//	accounts[1] = new SavingAccount(date, "02342342", 0.015);
//	//账户总数
//	accounts[0].deposit(new Date(2008, 11, 5), 5000, "salary");
//	accounts[1].deposit(new Date(2008, 11, 25), 10000, "sell stock 0323");
//	accounts[0].deposit(new Date(2008, 12, 5), 5500, "salary");
//	accounts[1].withdraw(new Date(2008,  12, 20), 4000, "buy a laptop");
//	System.out.println();
//	for(int i=0; i<2; i++){
//		accounts[i].settle(new Date(2009, 1, 1));
//		accounts[i].show();
//		System.out.println();
//	}
//	System.out.println("Total:"+SavingAccount.getTotal());
//	}
}








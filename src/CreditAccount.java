public class CreditAccount extends Account{
private
	Accumulator acc;
	double credit;
	double rate;
	double fee;
	final double getDebt(){
		double balance = getBalance();
		return (balance<0? balance : 0);
	}

public
	CreditAccount(final Date date, final String id, double credit, double rate, double fee){
		super(date, id);	
		//new Account(date, id1);
		this.credit = credit;
		this.rate = rate;
		this.fee = fee;
		acc = new Accumulator(date, 0);
	}
	final double getCredit(){
		return credit;
	}
	final double getRate(){
		return rate;
	}
	final double getFee(){
		return fee;
	}
	final double getAvailableCredit(){
		if(getBalance()<0)
			return credit+getBalance();
		else
			return credit;
	}
	void deposit(final Date date, double amount, final String desc){
		record(date, amount, desc);
		acc.change(date, getDebt());
	}
	void withdraw(final Date date, double amonut, final String desc){
		if (amonut-getBalance()>credit)
			error("not enough credit");
		else{
			record(date, -amonut, desc);
			acc.change(date, getDebt());
		}
	}
	void settle(final Date date){
		double interest = acc.getSum(date)*rate;
		if (interest!=0)
			record(date, interest, "interest");
		if (date.getMonth()==1)
			record(date, -fee, "annual fee");
		acc.reset(date, getDebt());
	}
	final void show(){
		//super.show();
		System.out.print(id+"\tBalance: "+balance);
		System.out.println("\tAvailable credit:"+getAvailableCredit());
	}

}

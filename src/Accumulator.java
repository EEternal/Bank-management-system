public class Accumulator{
private
	Date lastDate;
	double value;
	double sum;
	
public
	Accumulator(final Date date, double value){
		lastDate = date;
		this.value = value;
		sum = 0;
	}
	final double getSum(final Date date){
		return sum+value*date.distance(lastDate);
	}
	void change(final Date date, double value){
		sum = getSum(date);
		lastDate = date;
		this.value = value;
	}
	void reset(final Date date, double value){
		lastDate = date;
		this.value = value;
		sum = 0;
	}
}

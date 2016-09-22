//import javax.management.RuntimeErrorException;

import java.util.Scanner;
public class Date {
private
	int year;
	int month;
	int day;
	int totalDays;
	final int DAYS_BEFORE_MONTH[]={0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334, 365};
public
	Date(int year, int month, int day) throws Exception{
		this.year = year;
		this.month = month;
		this.day = day;
		if (day<=0 || day>getMaxDay()){
			throw new Exception("Invalid date: ");
//			System.out.println("Invalid date: ");
//			show();
//			System.out.println();
//			System.exit(1);
		}
		int years = year-1;
		totalDays = years*365+years/4-years/100+years/400+DAYS_BEFORE_MONTH[month-1]+day;
		if (isLeapYear() && month>2)
			totalDays++;
	}

//	static Date read() throws Exception{
//		int year, month, day;
//		String c1, c2;
//		Scanner sc = new Scanner(System.in);
//		year = sc.nextInt();
//		c1 = sc.next();
//		month = sc.nextInt();
//		c2 = sc.next();
//		day = sc.nextInt();
//		sc.close();
//		if (c1!="-" || c2!="-") {
//			throw new Exception("Bad time format");
//		}
//		return new Date(year, month, day);
//	}
	
	final int getYear(){
		return year;
	}
	final int getMonth(){
		return month;
	}
	final int getDay(){
		return day;
	}
	final int getMaxDay(){
		if (isLeapYear() && month==2)
			return 29;
		else
			return DAYS_BEFORE_MONTH[month]-DAYS_BEFORE_MONTH[month-1];
	}
	final boolean isLeapYear(){
		return year%4==0 && year%100!=0 || year%400==0;
	}
	final void show(){
		System.out.println(getYear()+"-"+getMonth()+"-"+getDay());
	}
	final int distance(final Date date){
		return totalDays-date.totalDays;
	}
}

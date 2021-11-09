package in.budgettracker.rs.model;

import java.time.Month;
import java.util.Set;

public class MonthlyStatement {
	
	private long userId;
	private Month month;
	private int year;
	private double totalCredit;
	private double totalDebit;
	private double monthlyBalance;
	private Set<TransactionModel> txns;
	
	public MonthlyStatement() {}

	public MonthlyStatement(long userId, Month month, int year, double totalCredit, double totalDebit,
			double monthlyBalance, Set<TransactionModel> txns) {
		super();
		this.userId = userId;
		this.month = month;
		this.year = year;
		this.totalCredit = totalCredit;
		this.totalDebit = totalDebit;
		this.monthlyBalance = monthlyBalance;
		this.txns = txns;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Month getMonth() {
		return month;
	}

	public void setMonth(Month month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getTotalCredit() {
		return totalCredit;
	}

	public void setTotalCredit(double totalCredit) {
		this.totalCredit = totalCredit;
	}

	public double getTotalDebit() {
		return totalDebit;
	}

	public void setTotalDebit(double totalDebit) {
		this.totalDebit = totalDebit;
	}

	public double getMonthlyBalance() {
		return monthlyBalance;
	}

	public void setMonthlyBalance(double monthlyBalance) {
		this.monthlyBalance = monthlyBalance;
	}

	public Set<TransactionModel> getTxns() {
		return txns;
	}

	public void setTxns(Set<TransactionModel> txns) {
		this.txns = txns;
	}

	
	
}
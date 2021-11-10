package in.budgettracker.rs.model.service;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.budgettracker.rs.model.MonthlyStatement;
import in.budgettracker.rs.model.TransactionModel;
import in.budgettracker.rs.model.TransactionType;

@Service
public class StatementServiceImpl implements StatementService{

	@Autowired
	private TxnManagementProxyService txnService;
	
	@Override
	public MonthlyStatement getMonthlyStatement(Long userId, Month month, int year) {
		LocalDateTime start = LocalDateTime.of(year, month, 1, 0, 0);
		LocalDateTime end = start.with(TemporalAdjusters.lastDayOfMonth());
		List<TransactionModel> txns = txnService.getAllTxnsInDates(userId, start, end);
		
		double totalCredit=0;
		double totalDebit=0;
		
		if(txns!=null && !txns.isEmpty()) {
			totalCredit = totalAmountOf(txns,TransactionType.CREDIT);
			totalDebit = totalAmountOf(txns,TransactionType.DEBIT);
		}
		
		double monthlyBalance=totalCredit-totalDebit;
		
		return new MonthlyStatement(userId, month, year, totalCredit, totalDebit, monthlyBalance, new TreeSet<>(txns));
	}

	private double totalAmountOf(List<TransactionModel> txns,TransactionType type) {
		return txns.stream().
				filter(t->t.getTxnType()==type).
				map(t -> t.getAmount()).
				reduce((a1,a2)->a1+a2).orElse(0.0);
	}

}

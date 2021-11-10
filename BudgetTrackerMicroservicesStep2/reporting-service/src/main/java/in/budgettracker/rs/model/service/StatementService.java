package in.budgettracker.rs.model.service;

import java.time.Month;

import in.budgettracker.rs.model.MonthlyStatement;

public interface StatementService {
	MonthlyStatement getMonthlyStatement(Long userId,Month month,int year);
}

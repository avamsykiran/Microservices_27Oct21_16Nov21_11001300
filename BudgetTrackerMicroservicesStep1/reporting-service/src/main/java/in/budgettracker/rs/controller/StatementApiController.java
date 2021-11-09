package in.budgettracker.rs.controller;

import java.time.Month;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.budgettracker.rs.model.MonthlyStatement;
import in.budgettracker.rs.model.service.StatementService;

@RestController
@RequestMapping("/reports")
public class StatementApiController {

	@Autowired
	private StatementService stmtService;
	
	@GetMapping("/{userId}/{month}/{year}")
	public ResponseEntity<MonthlyStatement> getMonthlyStatement(
			@PathVariable("userId") long userId,
			@PathVariable("month") Month month,
			@PathVariable("year") int year
			){
		return new ResponseEntity<MonthlyStatement>(stmtService.getMonthlyStatement(userId, month, year), HttpStatus.OK);
	}
}

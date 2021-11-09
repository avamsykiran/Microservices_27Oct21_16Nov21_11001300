package in.budgettracker.rs.model.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import in.budgettracker.rs.model.TransactionModel;

@FeignClient(name="txn-management-service",url = "http://localhost:9200/users")
public interface TxnManagementProxyService {

	@GetMapping("/{userId}/txns/{start}/{end}")
	List<TransactionModel> getAllTxnsInDates(
			@PathVariable("userId") Long userId,
			@PathVariable("start") @DateTimeFormat(iso=ISO.DATE_TIME) LocalDateTime start,
			@PathVariable("end") @DateTimeFormat(iso=ISO.DATE_TIME) LocalDateTime end);
	
}

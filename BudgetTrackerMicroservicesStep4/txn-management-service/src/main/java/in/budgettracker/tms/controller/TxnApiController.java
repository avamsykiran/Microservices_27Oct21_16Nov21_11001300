package in.budgettracker.tms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.budgettracker.tms.entity.TransactionEntity;
import in.budgettracker.tms.exception.TxnManagementException;
import in.budgettracker.tms.service.TransactionService;

@RestController
@RequestMapping("/txns")
public class TxnApiController {

	@Autowired
	private TransactionService txnService;
	
	@GetMapping("/{txnId}")
	public ResponseEntity<TransactionEntity> getById(@PathVariable("txnId") Long txnId) throws TxnManagementException{
		TransactionEntity txn = txnService.getById(txnId);
		return txn==null? new ResponseEntity<>(HttpStatus.NOT_FOUND):new ResponseEntity<>(txn,HttpStatus.OK);
	}
	
	@DeleteMapping("/{txnId}")
	public ResponseEntity<Void> deleteById(@PathVariable("txnId") Long txnId) throws TxnManagementException{
		txnService.deleteById(txnId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping
	public ResponseEntity<TransactionEntity> create(@RequestBody TransactionEntity txn) throws TxnManagementException{
		txn = txnService.create(txn);
		return new ResponseEntity<>(txn,HttpStatus.OK);
	}
}

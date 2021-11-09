package in.budgettracker.rs.model;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class TransactionModel implements Comparable<TransactionModel> {

	private Long txnId;
	private String header;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime dateAndTime;
	private Double amount;
	private TransactionType txnType;
	
	public TransactionModel() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int compareTo(TransactionModel arg0) {
		return this.txnId.compareTo(arg0.txnId);
	}

	public Long getTxnId() {
		return txnId;
	}

	public void setTxnId(Long txnId) {
		this.txnId = txnId;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public LocalDateTime getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(LocalDateTime dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public TransactionType getTxnType() {
		return txnType;
	}

	public void setTxnType(TransactionType txnType) {
		this.txnType = txnType;
	}

	
}

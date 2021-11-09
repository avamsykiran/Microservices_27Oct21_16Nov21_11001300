package in.budgettracker.tms.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name="tmsTxns")
public class TransactionEntity implements Comparable<TransactionEntity> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long txnId;
	private String header;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime dateAndTime;
	private Double amount;
	@Enumerated(EnumType.STRING)
	private TransactionType txnType;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private UserEntity holder;
	
	public TransactionEntity() {
		// TODO Auto-generated constructor stub
	}

	public TransactionEntity(Long txnId, String header, LocalDateTime dateAndTime, Double amount,
			TransactionType txnType, UserEntity holder) {
		super();
		this.txnId = txnId;
		this.header = header;
		this.dateAndTime = dateAndTime;
		this.amount = amount;
		this.txnType = txnType;
		this.holder = holder;
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

	public UserEntity getHolder() {
		return holder;
	}

	public void setHolder(UserEntity holder) {
		this.holder = holder;
	}

	@Override
	public int compareTo(TransactionEntity arg0) {
		return this.txnId.compareTo(arg0.txnId);
	}

	
}

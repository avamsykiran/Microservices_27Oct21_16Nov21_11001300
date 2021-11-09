package in.budgettracker.tms.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tmsUsers")
public class UserEntity {

	@Id
	private Long userId;
	
	@Column(name="cur_bal")
	private Double currentBal;
	
	@OneToMany(mappedBy = "holder",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Set<TransactionEntity> txns;
	
	public UserEntity() {
		// TODO Auto-generated constructor stub
	}

	public UserEntity(Long userId, Double currentBal, Set<TransactionEntity> txns) {
		super();
		this.userId = userId;
		this.currentBal = currentBal;
		this.txns = txns;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Double getCurrentBal() {
		return currentBal;
	}

	public void setCurrentBal(Double currentBal) {
		this.currentBal = currentBal;
	}

	public Set<TransactionEntity> getTxns() {
		return txns;
	}

	public void setTxns(Set<TransactionEntity> txns) {
		this.txns = txns;
	}

}

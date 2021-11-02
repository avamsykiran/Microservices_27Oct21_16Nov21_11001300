package in.budgettracker.tms.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tmsDayBook")
public class DayBookEntity implements Comparable<DayBookEntity>{

	@Id
	private String monthId;
	
	private Double openingBal;
	private Double closingBal;
	
	@OneToMany(mappedBy = "dayBook",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Set<TransactionEntity> txns;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private UserEntity holder;
	
	public DayBookEntity() {
		// TODO Auto-generated constructor stub
	}

	public DayBookEntity(String monthId, Double openingBal, Double closingBal, Set<TransactionEntity> txns,
			UserEntity holder) {
		super();
		this.monthId = monthId;
		this.openingBal = openingBal;
		this.closingBal = closingBal;
		this.txns = txns;
		this.holder = holder;
	}

	public String getMonthId() {
		return monthId;
	}

	public void setMonthId(String monthId) {
		this.monthId = monthId;
	}

	public Double getOpeningBal() {
		return openingBal;
	}

	public void setOpeningBal(Double openingBal) {
		this.openingBal = openingBal;
	}

	public Double getClosingBal() {
		return closingBal;
	}

	public void setClosingBal(Double closingBal) {
		this.closingBal = closingBal;
	}

	public Set<TransactionEntity> getTxns() {
		return txns;
	}

	public void setTxns(Set<TransactionEntity> txns) {
		this.txns = txns;
	}

	public UserEntity getHolder() {
		return holder;
	}

	public void setHolder(UserEntity holder) {
		this.holder = holder;
	}

	@Override
	public int compareTo(DayBookEntity o) {
		return monthId.compareTo(o.monthId);
	}
}

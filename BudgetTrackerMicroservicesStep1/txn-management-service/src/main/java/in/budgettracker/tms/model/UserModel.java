package in.budgettracker.tms.model;

public class UserModel {

	private Long userId;
	private String fullName;
	private String emailId;
	private Double currentBal;
	
	public UserModel() {
		// TODO Auto-generated constructor stub
	}

	public UserModel(Long userId, String fullName, String emailId,Double currentBal) {
		super();
		this.userId = userId;
		this.fullName = fullName;
		this.emailId = emailId;
		this.currentBal=currentBal;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Double getCurrentBal() {
		return currentBal;
	}

	public void setCurrentBal(Double currentBal) {
		this.currentBal = currentBal;
	}
	
	
	
}

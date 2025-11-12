package application;

public class EmploymentApplication {
    private int id;
    private String fullName;
    private String email;
    private String position;
    private String phone;
    private java.sql.Date startDate;
    private String relocate;
    private String comments;
    //Lab7 attributes added
    private String currentAddress;
    private String highestEducation; //Switch to class after - better and include a table for qualifications with rank - getHighest();
    private Gender gender;   //using Gender enum
    private double desiredSal;
    private boolean isAuthorized; 
    private boolean relatedEmployees; 
    private String relativeExplain; //make it so that instantiating doesnt require this attribute 
    private String signaturePic; //samSigned.jpg

    public EmploymentApplication(int id, String fullName, String currentAddress, String phone, String email, String highestEducation, Gender gender, 
    			java.sql.Date startDate, String position, double desiredSal, boolean isAuthorized, boolean relatedEmployees, String relativeExplain, 
                      String relocate, String comments, String signaturePic) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.position = position;
        this.phone = phone;
        this.startDate = startDate;
        this.relocate = relocate;
        this.comments = comments;
        
        this.currentAddress = currentAddress;
        this.highestEducation = highestEducation;
        this.gender = gender;
        this.desiredSal = desiredSal;
        this.isAuthorized = isAuthorized; 
        this.relatedEmployees = relatedEmployees;
        if(relatedEmployees)
        	this.relativeExplain = relativeExplain;
        else
        	this.relativeExplain = "";
        
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public java.sql.Date getStartDate() {
		return startDate;
	}

	public void setStartDate(java.sql.Date startDate) {
		this.startDate = startDate;
	}

	public String getRelocate() {
		return relocate;
	}

	public void setRelocate(String relocate) {
		this.relocate = relocate;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getCurrentAddress() {
		return currentAddress;
	}

	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}

	public String getHighestEducation() {
		return highestEducation;
	}

	public void setHighestEducation(String highestEducation) {
		this.highestEducation = highestEducation;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public double getDesiredSal() {
		return desiredSal;
	}

	public void setDesiredSal(double desiredSal) {
		this.desiredSal = desiredSal;
	}

	public boolean isAuthorized() {
		return isAuthorized;
	}

	public void setAuthorized(boolean isAuthorized) {
		this.isAuthorized = isAuthorized;
	}

	public boolean isRelatedEmployees() {
		return relatedEmployees;
	}

	public void setRelatedEmployees(boolean relatedEmployees) {
		this.relatedEmployees = relatedEmployees;
	}

	public String getRelativeExplain() {
		return relativeExplain;
	}

	public void setRelativeExplain(String relativeExplain) {
		this.relativeExplain = relativeExplain;
	}

	public String getSignaturePic() {
		return signaturePic;
	}

	public void setSignaturePic(String signaturePic) {
		this.signaturePic = signaturePic;
	}
    
   

//    // Getters
//    public int getId() { return id; }
//    public String getFirstName() { return firstName; }
//    public String getLastName() { return lastName; }
//    public String getEmail() { return email; }
//    public String getPosition() { return position; }
//    public String getPhone() { return phone; }
//    public java.sql.Date getStartDate() { return startDate; }
//    public String getRelocate() { return relocate; }
//    public String getComments() { return comments; }
}
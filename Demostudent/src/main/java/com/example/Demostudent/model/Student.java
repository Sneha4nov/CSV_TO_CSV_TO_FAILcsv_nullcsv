package com.example.Demostudent.model;

public class Student {

	private String firstName;
	private String middleName;
	private String lastName;
	private String college;
	private String branch;
	private String maths;
	private String science;
	private String hindi;
	private String dbms;
	private String english;

	//@NotNull(message = "mathsMarks cannot be null")
	private int mathsMarks;

	//@NotNull(message = "scienceMarks cannot be null")
	private int scienceMarks;

	//@NotNull(message = "hindiMarks cannot be null")
	private int hindiMarks;

	//@NotNull(message = "dbmsMarks cannot be null")
	private int dbmsMarks;

	//@NotNull(message = "englishMarks cannot be null")
	private int englishMarks;

	private float totalMarks;
	private float percantage;
	private String grade;

	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getMaths() {
		return maths;
	}

	public void setMaths(String maths) {
		this.maths = maths;
	}

	public String getScience() {
		return science;
	}

	public void setScience(String science) {
		this.science = science;
	}

	public String getHindi() {
		return hindi;
	}

	public void setHindi(String hindi) {
		this.hindi = hindi;
	}

	public String getDbms() {
		return dbms;
	}

	public void setDbms(String dbms) {
		this.dbms = dbms;
	}

	public String getEnglish() {
		return english;
	}

	public void setEnglish(String english) {
		this.english = english;
	}

	public int getMathsMarks() {
		return mathsMarks;
	}

	public void setMathsMarks(int mathsMarks) {
		this.mathsMarks = mathsMarks;
	}

	public int getScienceMarks() {
		return scienceMarks;
	}

	public void setScienceMarks(int scienceMarks) {
		this.scienceMarks = scienceMarks;
	}

	public int getHindiMarks() {
		return hindiMarks;
	}

	public void setHindiMarks(int hindiMarks) {
		this.hindiMarks = hindiMarks;
	}

	public int getDbmsMarks() {
		return dbmsMarks;
	}

	public void setDbmsMarks(int dbmsMarks) {
		this.dbmsMarks = dbmsMarks;
	}

	public int getEnglishMarks() {
		return englishMarks;
	}

	public void setEnglishMarks(int englishMarks) {
		this.englishMarks = englishMarks;
	}

	public float getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(float totalMarks) {
		this.totalMarks = totalMarks;
	}

	public float getPercantage() {
		return percantage;
	}

	public void setPercantage(float percantage) {
		this.percantage = percantage;
	}

	@Override
	public String toString() {
		return "Student [firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName
				+ ", college=" + college + ", branch=" + branch + ", maths=" + maths + ", science=" + science
				+ ", hindi=" + hindi + ", dbms=" + dbms + ", english=" + english + ", mathsMarks=" + mathsMarks
				+ ", scienceMarks=" + scienceMarks + ", hindiMarks=" + hindiMarks + ", dbmsMarks=" + dbmsMarks
				+ ", englishMarks=" + englishMarks + ", totalMarks=" + totalMarks + ", percantage=" + percantage
				+ ", grade=" + grade + "]";
	}

}

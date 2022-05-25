package com.example.demo.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "students")
public class Students {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Sid;
	
	

	@Column(name = "registraion_no",nullable = false, unique = true)
	private long Registration_no;
	
	@Column(name = "sname")
	private String Sname;
	
	@Column(name = "saddress")
	private String Saddress;
	
	@Column(name = "sdob")
	private String SDoB;
	
	@Column(name = "smark")
	private String SMarks;
	
	@Column(name = "sgender")
	private String SGender;
	
	@Column(name ="standard")
	private long Standard;
	
	@Column(name = "attendence")
	private String Attendence;
	
	
	public long getRegistration_no() {
		return Registration_no;
	}

	public void setRegistration_no(long registration_no) {
		Registration_no = registration_no;
	}
	
	
	public String getAttendence() {
		return Attendence;
	}

	public void setAttendence(String attendence) {
		Attendence = attendence;
	}

	public long getStandard() {
		return Standard;
	}

	public void setStandard(long standard) {
		Standard = standard;
	}

	public long getSid() {
		return Sid;
	}
	
	public void setSid(long sid) {
		Sid = sid;
	}
	
	public String getSname() {
		return Sname;
	}
	
	public void setSname(String sname) {
		Sname = sname;
	}
	
	public String getSaddress() {
		return Saddress;
	}
	
	public void setSaddress(String saddress) {
		Saddress = saddress;
	}
	
	public String getSDoB() {
		return SDoB;
	}
	
	public void setSDoB(String sDoB) {
		SDoB = sDoB;
	}
	
	public String getSMarks() {
		return SMarks;
	}
	
	public void setSMarks(String sMarks) {
		SMarks = sMarks;
	}
	
	public String getSGender() {
		return SGender;
	}
	
	public void setSGender(String sGender) {
		SGender = sGender;
	}
	
	
	
	
	public Students(long registration_no, String sname, String saddress, String sDoB, String sMarks, String sGender,
			long standard, String attendence) {
		super();
		Registration_no = registration_no;
		Sname = sname;
		Saddress = saddress;
		SDoB = sDoB;
		SMarks = sMarks;
		SGender = sGender;
		Standard = standard;
		Attendence = attendence;
	}

	public Students() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}

package com.example.demo.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "teachers")
public class Teachers {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Tid;
	
	@Column(name = "treg_no",nullable = false,unique = true)
	private long TReg_no;
	
	@Column(name = "tname")
	private String Tname;
	
	@Column(name = "taddress")
	private String Taddress;
	
	@Column(name = "subject")
	private String Subject;
	
	
	public long getTReg_no() {
		return TReg_no;
	}
	public void setTReg_no(long tReg_no) {
		TReg_no = tReg_no;
	}
	public Teachers(long tReg_no, String tname, String taddress, String subject) {
		super();
		TReg_no = tReg_no;
		Tname = tname;
		Taddress = taddress;
		Subject = subject;
	}
	public Teachers() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getTid() {
		return Tid;
	}
	public void setTid(long tid) {
		Tid = tid;
	}
	public String getTname() {
		return Tname;
	}
	public void setTname(String tname) {
		Tname = tname;
	}
	public String getTaddress() {
		return Taddress;
	}
	public void setTaddress(String taddress) {
		Taddress = taddress;
	}
	public String getSubject() {
		return Subject;
	}
	public void setSubject(String subject) {
		Subject = subject;
	}
	
	

}

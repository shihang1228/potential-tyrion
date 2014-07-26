package com.baldurtech;

public class Contact
{
	private Long id;
	private String name;
	private String mobile;
	private String vpmn;
	private String email;
	private String homeAddress;
	private String officeAddress;
	private String memo;
	private String job;
	private int jobLevel;
	
	public Long getId()
	{
		return id;
	}
	public void setId(Long id)
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getMobile()
	{
		return mobile;
	}
	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}
	public String getVpmn()
	{
		return vpmn;
	}
	public void setVpmn(String vpmn)
	{
		this.vpmn = vpmn;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getHomeAddress()
	{
		return homeAddress;
	}
	public void setHomeAddress(String homeAddress)
	{
		this.homeAddress = homeAddress;
	}
	public String getOfficeAddress()
	{
		return officeAddress;
	}
	public void setOfficeAddress(String officeAddress)
	{
		this.officeAddress = officeAddress;
	}
	public String getMemo()
	{
		return memo;
	}
	public void setMemo(String memo)
	{
		this.memo = memo;
	}
	public String getJob()
	{
		return job;
	}
	public void setJob(String job)
	{
		this.job = job;
	}
	public int getJobLevel()
	{
		return jobLevel;
	}
	public void setJobLevel(int jobLevel)
	{
		this.jobLevel = jobLevel;
	}
	
	public String toString()
	{
		return name;
	}
	
}
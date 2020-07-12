package com.test;
public class Student {
	public String getName() {
		return name;
	}
	public String getSubject() {
		return subject;
	}
	public double getMark() {
		return mark;
	}
	public String name=null;
	public String subject=null;
	public double mark=0.0;

	public Student(String name,String subject ,double mark)
	{
		this.name=name;
		this.subject=subject;
		this.mark=mark;
	}
	@Override
	public String toString()
	{
		return "\n name ="+name+",subject ="+subject+",mark ="+mark;
		
	}
	
}


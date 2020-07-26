package com.test;
/*
 * public interface Predicate<T> {

boolean test(T t); // functional descriptor
}
 */

import java.util.function.Predicate;
import java.util.function.BiPredicate;
public class DemoPredicate {
	
	public static void main(String[] args) {
		DemoPredicate predicateobject = new DemoPredicate(); 
		
		
		
		
		Predicate<String> custompredicate =new Predicate<String>() {

			@Override
			public boolean test(String t) {
				// TODO Auto-generated method stub
				return t.equalsIgnoreCase("JAVA");
			}
		};
		
		
		
		Predicate<String> lambdaPredicate=(t)->((String) t).equalsIgnoreCase("JAVA");
		
		
		//System.out.println("Am I equal to JAVA ::"+custompredicate.test("Student"));
		//System.out.println("Am I equal to JAVA ::"+custompredicate.test("JAVA"));
		System.out.println("Am I equal to JAVA ::"+lambdaPredicate.test("Student"));//in this we have not created custom method test .
		System.out.println("Am I equal to JAVA ::"+lambdaPredicate.test("JAVA"));//in this we have not created custom method test .
		
		
		BiPredicate customBiPredicate=new BiPredicate<String, String>() {

			@Override
			public boolean test(String t, String u) {
				// TODO Auto-generated method stub
				 return t.equalsIgnoreCase(u);
			}
		};
		
		BiPredicate<String,String> lambdaBiPredicate=(t,u)->(t).equalsIgnoreCase(u);
		
		System.out.println("BiPredicate output custom method ::"+customBiPredicate.test("Java","Java"));
		System.out.println("BiPredicate output ::"+lambdaBiPredicate.test("Java","Student"));//in this we have not created custom method test .
		
	}

}

package com.test;
/*
 * Interface must have exactly one abstract method. commonly called as SAM   in cloud SAM implies server less application model 
It can have any number of default methods because they are not abstract and implementation is already provided by same.
Interface can declares an abstract method overriding one of the public method from java.lang.Object, that still can be considered as functional interface.
 *
 *Function<T, R>
 *BiFunction<T,U,R>
 */
import java.util.function.BiFunction;
import java.util.function.Function;

public class DemoFunctionalInterface implements  FunctionalInterfacedemo,FunctionalInterfacewithtwoargumentsdemo{
	
	public String compute(String msg){  
       return msg+" I am the computed functional interface";
    }  
	
	public String compute(String msg,String secondmessage){  
	       return msg .concat(secondmessage);
	    }  

	public static void main(String[] args) {
		DemoFunctionalInterface interfacedemoobj = new DemoFunctionalInterface();  
	/*	String reterievevalue = interfacedemoobj.compute("Hello there");
		System.out.println(reterievevalue);
		interfacedemoobj.afterTask();
		interfacedemoobj.beforeTask();
		interfacedemoobj.toString();
		String reterivedvaluewith2arguments=interfacedemoobj.compute("Hello there","I am the computed functional interface with 2 arguments");
		System.out.println(reterivedvaluewith2arguments);
    */    
        
		//I have another way if you are using java 8 or upper version 
		//you can use me as function and bifunction 
		
		
		
		Function<String, String> func=new Function<String, String>() {

			@Override
			public String apply(String t) {
				return t;
			}
		};
		
		String returnedvalue =(String) func.apply("Hello there using java 8 function");
		
	//	System.out.println(returnedvalue);
		
	
BiFunction<String, String, String> bifunc=new BiFunction<String, String, String>() {

	@Override
	public String apply(String t, String u) {
		
		return t.concat(u);
	}
};	

String returnedvaluefrombifunction =(String) bifunc.apply("Hello there using java 8 function","I am the computed functional interface with 2 arguments");

//System.out.println(returnedvaluefrombifunction);
	 

//we can simplify this using lamda

Function<String, String> func1=(t)->t;

BiFunction<String, String, String> func2=(t,u) ->	t.concat(u);

System.out.println(func1.apply("Lambda Function"));
System.out.println(func2.apply("Hi ", "Lambda BiFunction"));




	}
	
	}


@FunctionalInterface  //intentionally commented 
 interface FunctionalInterfacedemo 
{
	String compute(String passedParameter);
	
	@Override
    String toString();

    default void beforeTask() {
        System.out.println("beforeTask... ");
    }

    default void afterTask() {
        System.out.println("afterTask... ");
    }
}

@FunctionalInterface
interface FunctionalInterfacewithtwoargumentsdemo 
{
	String compute(String passedParameter,String secondargument);
	

}


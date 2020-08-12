package com.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DemoReduceBy {
	public static void main(String[] args) {
	List<Student> studentList=new ArrayList<Student>();
	studentList.add(new Student("Aadyam","English",95.0));
	studentList.add(new Student("Vaibhav","English",89.0));
	studentList.add(new Student("Shrishi","English",88.0));



	studentList.add(new Student("Aadyam","Math",85.0));
	studentList.add(new Student("Vaibhav","Math",87.0));
	studentList.add(new Student("Shrishi","Math",95.0));

	studentList.add(new Student("Aadyam","Hindi",85.0));
	studentList.add(new Student("Vaibhav","Hindi",91.0));
	studentList.add(new Student("Shrishi","Hindi",84.0));
	
	/*studentList.stream().reduce(Student::getMark);
	studentList.stream().reduce(identity, accumulator);
	studentList.stream().reduce(identity, accumulator, combiner);*/


	Optional<Double> reducedvalue = studentList.stream().map(Student::getMark).reduce(Double::sum);
	
	System.out.println("Total value:: "+ reducedvalue.get());
	 Double reducedvaluewithidentity = studentList.stream().map(Student::getMark).reduce(0d,Double::sum); //Initial value is 0 and converted to double
	 System.out.println("Total value with initial offset:: "+reducedvaluewithidentity);
	 
	 Double reducedvalueincrementedby10 = studentList.stream().map(Student::getMark).reduce(10d,Double::sum); //Initial value is 10 and converted to double
	 System.out.println("Total value with initial offset 10:: "+reducedvalueincrementedby10);
	 
	
	 
	
	
	
	}
}

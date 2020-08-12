package com.test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DemoPartition {

	public static void main(String[] args) {
		List<Student> studentList=new ArrayList<Student>();
		studentList.add(new Student("Vaibhav","English",89.0));
		studentList.add(new Student("Aadyam","English",95.0));
		studentList.add(new Student("Shrishi","English",88.0));


		studentList.add(new Student("Vaibhav","Math",87.0));
		studentList.add(new Student("Aadyam","Math",85.0));
		studentList.add(new Student("Shrishi","Math",95.0));

		studentList.add(new Student("Vaibhav","Hindi",91.0));
		studentList.add(new Student("Aadyam","Hindi",85.0));
		studentList.add(new Student("Shrishi","Hindi",84.0));

		//studentList.stream().collect(Collectors.partitioningBy(predicate));
		//studentList.stream().collect(Collectors.partitioningBy(predicate, downstream));
		//There are no guarantees on the type, mutability,serializability, or thread-safety of the Map returned.
		
		Map<Boolean, List<Student>> partitioneddata = studentList.stream().collect(Collectors.partitioningBy( student->((Student) student).getMark()>90));

		//	System.out.println(partitioneddata);
		//Name of the student who got marks above 90 in any subject 
		Map<Boolean, Map<String, List<Student>>> names = studentList.stream().collect(Collectors.partitioningBy( student->((Student) student).getMark()>90,Collectors.groupingBy(Student::getName)));

		System.out.println(names.get(true));


		//Name of the student who got marks above 90 in any subject 
		Map<Boolean, Map<String, Map<String, Double>>> namedownstream = studentList.stream().collect(Collectors.partitioningBy( student->((Student) student).getMark()>90,Collectors.groupingBy(Student::getName,Collectors.toMap(Student::getSubject, Student::getMark,(old,newvaue) -> old))));

		System.out.println(namedownstream.get(false));
		
		
		
		//Name of the student who got marks less than 90 in any subject error check
				//Map<Boolean, Map<String, Map<String, Double>>> namedownstreamwithmaperror = studentList.stream().collect(Collectors.partitioningBy( x->((Student) x).getMark()>90,Collectors.groupingBy(Student::getSubject,Collectors.toMap(Student::getSubject, Student::getMark))));

				//System.out.println(namedownstreamwithmaperror.get(false));
				
				
				
				
				
		





	}

}

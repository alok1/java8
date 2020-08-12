package com.test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

//Group by marks 
//group by name 
//group by subject 
public class DemoGroupby {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
		//if we group it by marks it will be as

		//English->95,89,88
		//Hindi->	85,	91,	84
		//Math->	85,	87,	95

		//problem statement 
		//Maximum mark obtained in each subject 
		//group by subject 

		//studentList.stream().collect(Collectors.groupingBy(classifier));
		//studentList.stream().collect(Collectors.groupingBy(classifier, downstream));
		//studentList.stream().collect(Collectors.groupingBy(classifier, mapFactory, downstream));
		//studentList.stream().collect(Collectors.groupingByConcurrent(classifier));

		Map<String, List<Student>> grouped_on_subject_withoutdownstreaming = studentList.stream().collect(Collectors.groupingBy(Student::getSubject));

		 grouped_on_subject_withoutdownstreaming.entrySet().stream().forEach(x-> System.out.println(x.getValue()));
System.out.println("*************************************************");
		Map<String, Optional<Student>> grouped_on_subject = studentList.stream().collect(Collectors.groupingBy(Student::getSubject,Collectors.maxBy(Comparator.comparing(Student::getMark))));


			grouped_on_subject.entrySet().stream().forEach(x-> System.out.println(x.getValue()));
			System.out.println("*************************************************");
		Map<String, Optional<Student>> mapfactory = studentList.stream().collect(Collectors.groupingBy(Student::getSubject,HashMap::new ,Collectors.maxBy(Comparator.comparing(Student::getMark))));

		mapfactory.entrySet().stream().forEach(x-> System.out.println(x.getValue()));
		System.out.println("*************************************************");

		Map<String, Optional<Student>> downstreamMaxBy = studentList.stream().collect(Collectors.groupingBy(Student::getSubject,Collectors.maxBy(Comparator.comparing(Student::getMark))));

		System.out.println(downstreamMaxBy);
		System.out.println("*************************************************");

		Map<String, Optional<Student>> downStreamMinBy = studentList.stream().collect(Collectors.groupingBy(Student::getName,Collectors.minBy(Comparator.comparing(Student::getMark))));

		System.out.println(downStreamMinBy);
		System.out.println("*************************************************");
		Map<String, Map<String, List<Student>>> downStreamGroupByNameandSubject = studentList.stream().collect(Collectors.groupingBy(Student::getName,Collectors.groupingBy(Student::getSubject)));

		System.out.println(downStreamGroupByNameandSubject);
		System.out.println("*************************************************");

		Map<String, Map<Double, List<Student>>> downstreamgroupbymarks = studentList.stream().collect(Collectors.groupingBy(Student::getName,Collectors.groupingBy(Student::getMark)));

		System.out.println(downstreamgroupbymarks);

		System.out.println("*************************************************");

		Map<String, List<Double>> namedownstreamgroupbymarkscollect = studentList.stream().collect(Collectors.groupingBy(Student::getName,Collectors.mapping(Student::getMark,Collectors.toList())));

		System.out.println(namedownstreamgroupbymarkscollect);

		System.out.println("*************************************************");


		Map<String, Optional<Double>> downstreamgroubyanddoublemapping = studentList.stream().collect(Collectors.groupingBy(Student::getName,Collectors.mapping(Student::getMark,Collectors.mapping(x->x, Collectors.reducing(Double::sum)))));

		System.out.println(downstreamgroubyanddoublemapping);

		System.out.println("*************************************************");
		//Finding total marks obtained by student name wise 
		Map<String, Optional<Double>> downStreamgroupbywithmapping = studentList.stream().
				collect(Collectors.groupingBy(Student::getName,
						Collectors.mapping(Student::getMark, Collectors.reducing(Double::sum))));

		System.out.println(downStreamgroupbywithmapping);
		
		System.out.println("*************************************************");

		//subject exams students given by student name 
		Map<String, Optional<String>> downstreamgroubyanddoublemappingwithnmeandsubject = studentList.stream().collect(Collectors.groupingBy(Student::getName,Collectors.mapping(Student::getSubject,Collectors.mapping((String x)->x, Collectors.reducing((b,a)-> a+b)))));

		System.out.println(downstreamgroubyanddoublemappingwithnmeandsubject);

		System.out.println("*************************************************");
		 
		 Double reduced_avg = studentList.stream().map(Student::getMark).collect(Collectors.averagingDouble(x->x)); 
		 System.out.println("The avaerage value is :: "+reduced_avg);

		 System.out.println("*************************************************");
		 
		 // average marks obtained by each student 
		 
		 
		 Map<String, Double> avg_studentwise = studentList.stream().collect(Collectors.groupingBy(Student::getName, HashMap::new, Collectors.averagingDouble(Student::getMark))); 
		 System.out.println("The avaerage value is :: "+avg_studentwise);
	
		 System.out.println("*************************************************");
			//Exception check during writing code  
			//		studentList.stream().collect(Collectors.groupingBy(Student::getName,Collectors.mapping(Student::getMark,Collectors.mapping(Collectors.groupingBy(Student::getName), Collectors.reducing(Double::sum)))));

			

		 
	}

}

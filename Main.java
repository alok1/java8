package com.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;




public class Main {

	public static int x = 20;
	
	public static void main(String[] args) {
List<Student> studentList=new ArrayList<Student>();
studentList.add(new Student("Alok","English",70.0));
studentList.add(new Student("Aadyam","English",75.0));
studentList.add(new Student("Manya","English",78.0));
studentList.add(new Student("Vaibhav","English",95.0));
studentList.add(new Student("Shrishi","English",88.0));



studentList.add(new Student("Alok","Math",75.0));
studentList.add(new Student("Aadyam","Math",89.0));
studentList.add(new Student("Manya","Math",76.0));
studentList.add(new Student("Vaibhav","Math",85.0));
studentList.add(new Student("Shrishi","Math",88.0));

studentList.add(new Student("Alok","Hindi",90.0));
studentList.add(new Student("Aadyam","Hindi",85.0));
studentList.add(new Student("Manya","Hindi",98.0));
studentList.add(new Student("Vaibhav","Hindi",85.0));
studentList.add(new Student("Shrishi","Hindi",88.0));
//Find max marks in each subject  
//Type inference

List<String> list = new ArrayList<>();
list.addAll(Arrays.asList());
List<Integer> intlist = new ArrayList<>();
intlist.addAll(Arrays.asList());
List<Double> doublelist = new ArrayList<>();
doublelist.addAll(Arrays.asList());
//exception check 
//intlist.addAll(list);

//using outside object

int localvariable=70;

Consumer<Integer> myConsumer = (y) -> {   
    System.out.println("x = " + x);
    System.out.println("y = " + y);
    //localvariable=localvariable+20; //localvariable
    x=x+20;//static variable
    System.out.println("x = " + x);
    System.out.println("localvariable = " + localvariable);
    
};

 myConsumer.accept(30);






//without java 8 

Map<String, Double> maxmarks = method2(studentList);
	
//System.out.println(maxmarks);



//With jav8 

//same output as old java 8 output
/*Map<String, Double> subject_marks_map = studentList.stream().collect(
	    groupingBy(Student::getSubject,                          
	        mapping(Student::getMark,                             
	            collectingAndThen(maxBy(Comparator.naturalOrder()),  
	                Optional::get))));

subject_marks_map.entrySet().stream().forEach(System.out::println);
*/

//finding the maximum marks obtained subject wise with mark optional object   
/*Map<String, Optional<Double>> subject_marks_map_optional = studentList.stream().collect(
	    groupingBy(Student::getSubject,                          
	        mapping(Student::getMark,                             //here marks is deciding what will be inside the next collector
	            collectingAndThen(maxBy(Comparator.naturalOrder()),  
	                Function.identity()))));
subject_marks_map_optional.entrySet().stream().forEach(System.out::println);*/



//just doing group by 
Map<String, List<Student>> groupbysubject = studentList.stream().collect(groupingBy(Student::getSubject));

//groupbysubject.entrySet().stream().forEach(System.out::println);


//grouping by subject and finding max using reduce function
/*Map<String, Optional<Student>> maxemployee = studentList.stream().
collect(Collectors.groupingBy
		(Student::getSubject,
				Collectors.reducing(BinaryOperator.maxBy(Comparator.comparing(Student::getMark)))));


maxemployee.entrySet().stream().forEach(System.out::println);
*/

//otherway of doing as above using mapping 
/*Map<String, Optional<Student>> datacheck = studentList.stream()
.collect(Collectors.groupingBy(Student::getSubject, 
		mapping(Function.identity(), Collectors.maxBy(Comparator.comparing(Student::getMark)))));
datacheck.entrySet().stream().forEach(System.out::println);*/

	}
	
	public static Map<String, Double> method2(List<Student> studentList) {
	    Map<String, List<Student>> temp = new HashMap<>();
	    for (Student e : studentList) {
	        temp.putIfAbsent(e.subject, new ArrayList<>());
	        temp.get(e.subject).add(e);
	    }

	    Map<String, Double> map = new HashMap<>();
	    for (Entry<String, List<Student>> ent : temp.entrySet()) {
	        double max = 0;
	        for (Student e2 : ent.getValue()) {
	            max = Double.max(max, e2.mark);
	        }
	        map.put(ent.getKey(), max);
	    }
	    return map;
	}

}

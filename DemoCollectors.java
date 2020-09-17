package com.test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DemoCollectors {

	/*
	 * interface Collector<T, A, R> {
    Supplier<A> supplier();
    BiConsumer<A, T> accumulator();
    BinaryOperator<A> combiner();
    Function<A, R> finisher();
    Set<Characteristics> characteristics();
}
	 */
	
	
	static String totalappended = "";
	static ArrayList<Integer> total= new ArrayList();
	
	
	StringBuilder sb1=new StringBuilder();
    static BiConsumer<String, String> consumer=new BiConsumer<String, String>() {
    	StringBuilder sb=new StringBuilder();
		@Override
		public void accept(String t, String u) {
			sb.append(t).append(u);
			System.out.println("I am biConsumer accumulator"+sb);
			
			//totalappended.concat(t+u);
		}

		
	};
	
	
	static BiConsumer<String, String> combiner=new BiConsumer<String, String>() {
		StringBuilder sb=new StringBuilder();
		@Override
		public void accept(String t, String u) {
			sb.append(t).append(u);
			System.out.println("I am biConsumer combiner "+ t.toLowerCase() +" and "+u.toLowerCase() );
			totalappended.concat(t+u);
		}

		
	};
	
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
		
		
		//studentList.stream().collect(collector);
		//studentList.stream().collect(supplier, accumulator, combiner);
		//studentList.stream().collect(Collectors.collectingAndThen(downstream, finisher));
		//studentList.stream().collect(Collectors.mapping(mapper, downstream));
		//studentList.stream().collect(Collectors.toMap(keyMapper, valueMapper));
		//studentList.stream().collect(Collectors.toMap(keyMapper, valueMapper, mergeFunction));
		//studentList.stream().collect(Collectors.toMap(keyMapper, valueMapper, mergeFunction, mapSupplier));
		//studentList.stream().collect(Collectors.toCollection(collectionFactory));
		
		//stream().collect(collector)
		
		//short curciut  //limit ,find first ,find any 
		
		
				//Intermediate Operations: stateless and stateful
		
		//Statefull (Sort)
		
		Supplier<Stream<Student>> supplier=()->studentList.stream();
		    
		List<Student> simplecollectedaslist = supplier.get().collect(Collectors.toList());
		System.out.println("simplecollectedaslist"+simplecollectedaslist);
		Set<Student> simplecollectedset = supplier.get().collect(Collectors.toSet());
		System.out.println("simplecollectedset"+simplecollectedset);
		
		//using collection factory 
		//stream().collect(Collectors.toCollection(collectionFactory));
		
		Collection<Student> concurrentlist = supplier.get().collect(Collectors.toCollection(CopyOnWriteArrayList::new));//use of ArrayList
		System.out.println("concurrentlist"+concurrentlist);
		
		Collection<Student> concurrrentset = supplier.get().collect(Collectors.toCollection(CopyOnWriteArraySet::new));//use of set
		System.out.println("concurrrentset"+concurrrentset);

		//studentList.stream().collect(supplier, accumulator, combiner);

		
	    List<Integer> returnevalue = IntStream.range(0, 10).collect(ArrayList::new,ArrayList::add, ArrayList::addAll);//ArrayList::addAll is a Biconsumer not a Bioperator as Integer::sum is binaryoperator
		
	    System.out.println("returnevalue"+returnevalue);
	    
	
		
		  /* String returnevalue2 = IntStream.range(0, 10).mapToObj(x->String.valueOf(x)).parallel().collect(String::new,consumer, combiner);//ArrayList::addAll is a Biconsumer not a Bioperator

			  System.out.println("returnevalue2:::"+returnevalue2);
			  
			  total.add(0);
			  
			     List<Integer> returnevalue3 = IntStream.range(0, 10).boxed().parallel().collect(ArrayList::new,(a,b)->{    //new Integer[] {0}  is an array 
			    	 System.out.println("A value" +a +"b value"+b);
			    	 if (a.isEmpty())
			    	 {
			    		 a=new ArrayList(0);
			    	 }
			    	 total.add(Integer.sum(Integer.valueOf(a.get(0)), Integer.valueOf(b.toString())));
			   }, (a,b)->{
				   total.add(Integer.sum(Integer.valueOf(a.get(0)), Integer.valueOf(b.toString()))); 
			   });//ArrayList::addAll is a Biconsumer not a Bioperator

				  System.out.println("returnevalue3:::"+returnevalue3);
				  
				  */

			  
			  List<Integer> resultcombiner = IntStream.range(0, 10)
				        .parallel()
				        .collect(ArrayList::new, ArrayList::add, (list1, list2) -> {
				            System.out.println("Combiner called for " + list1 + " and " + list2);
				            list1.addAll(list2);
				        });
				System.out.println(resultcombiner);
		  
	    
	  String returnevalue1 =  IntStream.range(0, 10).mapToObj(x->String.valueOf(x)).peek(System.out::println).collect(StringBuilder::new,StringBuilder::append, StringBuilder::append).toString();//ArrayList::addAll is a Biconsumer not a Bioperator

		
	  System.out.println("returnevalue1:::"+returnevalue1);
		
		
		
		
		Map<String, Optional<Student>> downstreamMaxBy = supplier.get().collect(Collectors.groupingBy(Student::getSubject,Collectors.maxBy(Comparator.comparing(Student::getMark))));

		System.out.println(downstreamMaxBy);
		System.out.println("*************************************************");

		Map<String, Optional<Student>> downStreamMinBy = supplier.get().collect(Collectors.groupingBy(Student::getName,Collectors.minBy(Comparator.comparing(Student::getMark))));

		System.out.println(downStreamMinBy);
		System.out.println("*************************************************");

		Map<String, List<Double>> namedownstreamgroupbymarkscollect = supplier.get().collect(Collectors.groupingBy(Student::getName,Collectors.mapping(Student::getMark,Collectors.toList())));

		System.out.println(namedownstreamgroupbymarkscollect);

		System.out.println("*************************************************");


		Map<String, Optional<Double>> downstreamgroubyanddoublemapping = supplier.get().collect(Collectors.groupingBy(Student::getName,Collectors.mapping(Student::getMark,Collectors.mapping(x->x, Collectors.reducing(Double::sum)))));

		System.out.println(downstreamgroubyanddoublemapping);


		
		Double reduced_avg = studentList.stream().map(Student::getMark).collect(Collectors.averagingDouble(x->x)); 
		System.out.println("The avaerage value is :: "+reduced_avg);

		System.out.println("*************************************************");

		// average marks obtained by each student using map factory


		Map<String, Double> avg_studentwise = supplier.get().collect(Collectors.groupingBy(Student::getName, HashMap::new, Collectors.averagingDouble(Student::getMark))); 
		System.out.println("The avaerage value is :: "+avg_studentwise);

		System.out.println("*************************************************");

		
		Map<String, Map<Double, List<Student>>> downstreamgroupbymarks = supplier.get().collect(Collectors.groupingBy(Student::getName,Collectors.groupingBy(Student::getMark)));

		System.out.println(downstreamgroupbymarks);

		System.out.println("*********************4****************************");

		 Map<String, Map<String, Double>> namedownstream = studentList.stream().collect(Collectors.groupingBy(Student::getName,Collectors.toMap(Student::getSubject, Student::getMark,(old,newvaue) -> old)));

		System.out.println(namedownstream);
		System.out.println("*********************5****************************");

		Map<String, Map<String, Double>> namedownstreammapsupplier = studentList.stream().collect(Collectors.groupingBy(Student::getName,Collectors.toMap(Student::getSubject, Student::getMark,(old,newvaue) -> old,TreeMap::new)));

		System.out.println(namedownstreammapsupplier);

		
		
	}

}

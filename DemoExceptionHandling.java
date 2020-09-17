package com.test;

import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.OptionalInt;
import java.util.function.Consumer;
import java.util.function.IntConsumer;





public class DemoExceptionHandling {

	static Consumer<Integer> consumer =new Consumer<Integer>(){

		@Override
		public void accept(Integer t) {
			// TODO Auto-generated method stub
			try {
				t=t/0;
			}catch (ArithmeticException e)
			{
				System.err.println("Divide by zero ");
			}

		}

	};


	static IntConsumer lambdaExceptionWrapper(Consumer<Integer> consumer) {
		return i -> {
			try {
				consumer.accept(i);
			} catch (ArithmeticException e) {
				System.err.println(
						"Arithmetic Exception occured : " + e.getMessage());
			}
		};
	}

	//generic exception handling consumer 

	static <T, E extends Exception> Consumer<T>
	consumerExceptionWrapper(Consumer<T> consumer, Class<E> clas) {

		return i -> {
			try {
				consumer.accept(i);
			} catch (Exception ex) {
				try {
					E exCast = clas.cast(ex);
					System.err.println(
							"Exception occured : " + exCast.getMessage());
				} catch (ClassCastException ccEx) {
					throw ex;
				}
			}
		};
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Supplier<IntStream> supplier = ()->IntStream.range(0, 9);
	/*	OptionalInt firstvalue = supplier.get().map(x->x/0).findFirst();
		System.out.println("firstvalue is"+firstvalue);
*/


		//1st way 
		supplier.get().forEach(x->  {
			try {
				x=x/0;

			}catch (ArithmeticException e)
			{
				//e.printStackTrace();
			}
		});
		System.out.println("I have successfully handled the exception");

		//the above try catch is not as per lambda

		supplier.get().boxed().forEach( consumer);
		System.out.println("I have successfully handled the exception again using consumer ");

		//2nd way
		//using consumer
		supplier.get().forEach(lambdaExceptionWrapper(x->System.out.println(x/0)));
		//3rd way making generic extending the 2nd way
		supplier.get().boxed().forEach(consumerExceptionWrapper(x->System.out.println(x/0),ArithmeticException.class));

	}

}

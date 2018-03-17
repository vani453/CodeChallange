package RandomisedPrimeChecker;

import java.util.concurrent.*;


class OutputFormat{
	
	int number;
	boolean res;
	OutputFormat(int num, boolean r){
		this.number=num;
		this.res= r;
		
		
	}
}
public class RandomizedPrime {
	public static void main(String args[]){
	ConcurrentLinkedQueue<Integer> inputqueue = new ConcurrentLinkedQueue<Integer>();
	ConcurrentLinkedQueue<OutputFormat> outputqueue = new ConcurrentLinkedQueue<OutputFormat>();
	System.out.println("Sample run for Prime Checker:");
	Thread randomizer= new Thread(new Randomizer(inputqueue,outputqueue));
	Thread prime= new Thread(new Prime(inputqueue,outputqueue));
	randomizer.start();
	prime.start();
	}
}
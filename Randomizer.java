package RandomisedPrimeChecker;

import java.util.Random;
import java.util.concurrent.*;

public class Randomizer implements Runnable{
	ConcurrentLinkedQueue<Integer> inputqueue;
	ConcurrentLinkedQueue<OutputFormat> outputqueue;
	Randomizer(ConcurrentLinkedQueue<Integer> in,ConcurrentLinkedQueue<OutputFormat>out){
		this.inputqueue=in;
		this.outputqueue=out;
	}
	public void run(){
		Random r= new Random();
		System.out.println("Randomizer started");
		try{
			for(int i=1;i<10;i++){
				int num= r.nextInt(100);
				inputqueue.add(num);
				System.out.println("Added to input queue from randomizer:"+num);
			    Thread.currentThread().sleep(500);
			    while(!outputqueue.isEmpty()){
			    	OutputFormat o= outputqueue.poll();
			    	System.out.println(" Integer: "+o.number+" isPrime: "+o.res);
			    
			   }
			 
			}
		}
	catch(Exception e){e.printStackTrace();}
	}
	
}

package basic;

import java.util.Scanner;

public class ReverseOfString {
	
	static String reverseOfString(String s) {
		   StringBuilder sb = new StringBuilder();
		   for(int i = s.length() - 1; i >= 0; --i)
		     sb.append(s.charAt(i));
		   return sb.toString();
		 }

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
	     String inPutStr=in.nextLine();
	     ReverseOfString obj = new ReverseOfString();
	     System.out.println("Reverse of String is: "+obj.reverseOfString(inPutStr));
	  
		} 

}
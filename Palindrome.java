package basic;

import java.util.Scanner;

public class Palindrome {

	public static void main(String[] args) {
		String str; 
	      Scanner in = new Scanner(System.in);
	      str=in.nextLine();
	      String strRev=ReverseOfString.reverseOfString(str);
	      if (strRev.equals(str))
	    	  System.out.println(str+ " is Palindraome");
	      else
	    	  System.out.println(str+" is not Palindraome"); 
	    	  

	}

}
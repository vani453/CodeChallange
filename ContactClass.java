package basic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ContactClass{
	public static void main(String args[])throws IOException{
	ArrayList<Contact> contact= new ArrayList();
	Contact c1= new Contact(11,"Vikrant","8572947714","75 Gardner St, Allston");
	Contact c2= new Contact(100,"John","8572947715","76	Gardner St, Boston");
	Contact c3= new Contact(113,"Sam","8572947717","77 Gardner St, California");
	contact.add(c1);
	contact.add(c2);
	contact.add(c3);
	Iterator<Contact> i = contact.iterator();
	while(i.hasNext()){
		System.out.println(i.next().getdata());
		}
		}
		}

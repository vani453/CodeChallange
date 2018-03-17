package basic;
class Contact{
private int id;
private String name;
private String phone;
private String address;
public Contact(){
	this.name=null;
	this.phone= null;
	this.address=null;
	}
	public Contact(int value,String name, String
	phone,String address){
	this.id= value;
	this.name= name;
	this.phone= phone;
	this.address= address;
	}
	//get data method as per requirement which returns the data
	public String getdata(){
	return String.format("ID: %d NAME: %s PHONE: %s	ADDRESS: %s",this.id,this.name,this.phone,this.address);
	}
	
	
}
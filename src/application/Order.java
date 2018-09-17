package application;

import java.util.ArrayList;
import java.util.Date;


public class Order implements java.io.Serializable {
	
	private int count = Main.orderArrayList.size();
	private int orderNum;
	private ArrayList<Thneed> thneedList;
	
	private int customerID;
	private String customerName;
	
	private String orderDate, filledDate;
	
	Order(){
		orderNum = 0;
		thneedList = new ArrayList<>();
		customerID = 0;
		customerName = "";
		orderDate = null;
		filledDate = null;
	}
	
	public Order(ArrayList<Thneed> thneedList, int customerID, String customerName, String orderDate, String filledDate){
		orderNum = ++count;
		this.thneedList = new ArrayList<Thneed>(thneedList);
		
		this.customerID = customerID;
		this.customerName = customerName;
		
		this.orderDate = orderDate;
		this.filledDate = filledDate;
	}
	
	// order number#ID
	public int orderNumGetter() {
		return this.orderNum;
	}
	public void orderNumSetter(int num) {
		this.orderNum = num;
	}
	
	//ThneedList
	public ArrayList<Thneed> listGetter() {
		return this.thneedList;
	}
	public void listSetter(ArrayList<Thneed> list) {
		this.thneedList = list;
	}
	
	// customer information
	public int customerIDGetter() {
		return this.customerID;
	}
	public void customerIDSetter(int id) {
		this.customerID = id;
	}
	
	public String customerNameGetter() {
		return this.customerName;
	}
	public void customerNameSetter(String name) {
		this.customerName = name;
	}
	
	// order placed date
	public String orderDateGetter() {
		return this.orderDate;
	}
	public void orderDateSetter(String date) {
		this.orderDate = date;
	}
	
	// order filled date
	public String filledDateGetter() {
		return this.filledDate;
	}
	public void filledDateSetter(String date) {
		this.filledDate = date;
	}
	
	public String toString() {
		return "Order ID: "+ orderNum +", ThneedList: " + thneedList + ", CustomerId: "+ customerID +" , Customer Name: "+ customerName +" , OrderDate: "+ orderDate + ", FilledDate: " + filledDate;
	}

}

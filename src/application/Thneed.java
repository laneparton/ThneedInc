package application;

public class Thneed implements java.io.Serializable {
	private String size;
	private String color;
	private int quantity;
	
	Thneed(){
		this.size = "S";
		this.color = "BLACK";
		this.quantity = 1;
	}
	
	Thneed(String size, String color, int quantity){
		this.size = size;
		this.color = color;
		this.quantity = quantity;
	}
	
	public void sizeSetter(String size) {
		this.size = size;
	}
	public void colorSetter(String color) {
		this.color = color;
	}
	public void quantitySetter(int num) {
		this.quantity = num;
	}
	
	public String sizeGetter() {
		return this.size;
	}
	public String colorGetter() {
		return this.color;
	}
	
	public int quantityGetter() {
		return this.quantity;
	}
	
	public String toString() {
		return "Size: " + size + " Color: " + color + " Quantity: " + quantity;
	}
}

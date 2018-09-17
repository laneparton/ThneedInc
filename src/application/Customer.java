package application;

public class Customer implements java.io.Serializable {

		private int count = Main.customerArrayList.size();
		
		private int customerID = 0;
		private String name = "";
		private String address = "";
		private String phone = "";
		
		
		// No-arg constructor
		public Customer() {
		}
		
		Customer(String name, String address, String phone) {
			customerID = ++count;
			this.name = name;
			this.address = address;
			this.phone = phone;

		}
		
		int getId() {
			return customerID;
		}
		String getName() {
			return name;
		}
		String getAddress() {
			return address;
		}
		String getPhone() {
			return phone;
		}
		
		
		void setId(int newID) {
			this.customerID = newID;
		}
		void setName(String newName){
			this.name = newName;
		}
		void setAddress(String newAddress){
			this.address = newAddress;
		}
		void setPhone(String newPhone){
			this.phone = newPhone;
		}
		
		
		public String toString() {
			return "Customer ID: "+customerID+", Name: "+ name +" , address: "+ address +" , phone: "+phone+".";
		}

		public static void main(String[] args) {
			// TODO Auto-generated method stub

		}

}

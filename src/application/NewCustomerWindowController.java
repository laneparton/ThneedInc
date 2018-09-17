package application;

import javafx.fxml.FXML;

import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javafx.event.ActionEvent;

public class NewCustomerWindowController {
	@FXML
	private AnchorPane mainPane;
	@FXML
	private TextField nameField;
	@FXML
	private TextField addressField;
	@FXML
	private TextField phoneField;
	
	private NewOrderWindowController newOrderController;
	public void setNewOrderController(NewOrderWindowController c) {
		newOrderController = c;
	}
	
	private void clearFields() {
		nameField.clear();
		addressField.clear();
		phoneField.clear();
	}

	// Event Listener on Button.onAction
	@FXML
	public void saveButtonClicked(ActionEvent event) {
		// We probably need a method to assign a unique ID
		String name = nameField.getText();
		String address = addressField.getText();
		String phone = phoneField.getText();
		
		//Create the customer object, print it out for good measure, and send it back to the
		//new order window.
		Customer newCustomer = new Customer(name, address, phone);
		
		Main.customerArrayList.add(newCustomer);
		newOrderController.updateCustomerList(newCustomer);
		//Debugging
		if(Main.DEBUG = true) {
			System.out.println("[Debug] saveButtonClicked newCustomer: " + newCustomer);
			System.out.println("[Debug] Main.customerArrayList size: " + Main.customerArrayList.size());
		}
		
		saveCustomers();
		
		clearFields();
		// Close Window
		mainPane.getScene().getWindow().hide();
	}

	private void saveCustomers() {
		try {
			File orders = new File("customers.ser");
			orders.createNewFile();
			FileOutputStream fileOut = new FileOutputStream(orders);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			
			if(Main.DEBUG = true) {
				System.out.println("[Debug] saveCustomers() Main.customerArrayList size: " + Main.customerArrayList.size());
			}
			
			ArrayList<Customer> customerList = Main.customerArrayList;
			out.writeObject(customerList);
			out.close();
			fileOut.close();
			System.out.println("[System] Customers data has been saved in customers.ser");
			
		}catch (IOException i) {
			i.printStackTrace();
		}
	}

	// Event Listener on Button.onAction
	@FXML
	public void closeButtonClicked(ActionEvent event) {
		// Close Window
		clearFields();
		mainPane.getScene().getWindow().hide();
	}
}

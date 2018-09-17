package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CustomerWindowController {
	@FXML
	private Label customerId;
	@FXML
	private ListView ordersList;
	@FXML
	private Label fullName;
	@FXML
	private Label address;
	@FXML
	private Label phone;
	@FXML
	private Button closeButton;
	
	private	MainWindowController MainController;
	public void setCallingController(MainWindowController c) {
		MainController = c;
	}
	
	private void clearFields() {
		this.customerId.setText(" ");
		this.fullName.setText(" ");
		this.address.setText(" ");
		this.phone.setText(" ");
	}

	public void lookupCustomer(int id) {
		Customer newCustomer = null;
		
		for(int i = 0; i < Main.customerArrayList.size(); i++) {
			//Get the customer at index i
			Customer currentCustomer = Main.customerArrayList.get(i);
			//Does the customer at index i have a name equal to the listview name selected?
			if(currentCustomer.getId() == id) {
				//If so, assign the current customer to the selected customer object.
				newCustomer = currentCustomer;
			}
		}
		
		if(Main.DEBUG = true) {
			System.out.println("[Debug] lookupCustomer - Main.customerArrayList.size: " + Main.customerArrayList.size());
		}
		
		customerId.setText(Integer.toString(newCustomer.getId()));
		fullName.setText(newCustomer.getName());
		address.setText(newCustomer.getAddress());
		phone.setText(newCustomer.getPhone());
		
		//Go through the orderArrayList
		for(int i = 0; i < Main.orderArrayList.size(); i++) {
			//Grab the order at position i
			Order order = Main.orderArrayList.get(i);
			//Does the customer id of order = the customer selected
			if(order.customerIDGetter() == newCustomer.getId()) {
				//if it does, add that order to the customer's orderlist & show just the orderId
				this.ordersList.getItems().add(Integer.toString(order.orderNumGetter()));
			}
		}
		
	}
	
	@FXML
	public void closeButtonClicked(ActionEvent event) {
		if(Main.DEBUG = true) {
			System.out.println("[Debug] Customer Window closeButtonClicked");
		}
		
		//Important so that lookupCustomer can be called again
		MainController.customerLookupDialogStage = null;
		
		Stage stage = (Stage) closeButton.getScene().getWindow();
	    stage.close();
		clearFields();
	}

}

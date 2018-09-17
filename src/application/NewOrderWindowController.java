package application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javafx.event.ActionEvent;

import javafx.scene.control.ListView;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

public class NewOrderWindowController {
	@FXML
	private ListView customerList;
	@FXML
	private ChoiceBox sizeCombo;
	@FXML
	private ChoiceBox colorCombo;
	@FXML
	private TextField quantityField;
	@FXML
	private ListView orderSummaryList;
	@FXML
	private Button saveButton;
	
	ArrayList<Thneed> thneedList = new ArrayList<>();
	
	// Initialize to Populate the choiceboxes
	@FXML
    public void initialize() {
		//Populate Choiceboxes
        this.sizeCombo.getItems().add("Small");
        this.sizeCombo.getItems().add("Medium");
        this.sizeCombo.getItems().add("Large");
        
        this.colorCombo.getItems().add("White");
        this.colorCombo.getItems().add("Black");
        this.colorCombo.getItems().add("Red");
        this.colorCombo.getItems().add("Blue");
        
        //Populate the Customer listview from the Customer ArrayList
        for(int i = 0; i < Main.customerArrayList.size(); i++) {
        	Customer currentCustomer = Main.customerArrayList.get(i);
        	customerList.getItems().add(currentCustomer.getName());
        }
    }
	
	private Stage dialogStage;
	private NewCustomerWindowController NewCustomerController;
	
	private	MainWindowController MainController;
	public void setCallingController(MainWindowController c) {
		MainController = c;
	}
	
	private void clearOrderWindow() {
		this.orderSummaryList.getItems().clear();
		this.sizeCombo.getSelectionModel().clearSelection();
		this.colorCombo.getSelectionModel().clearSelection();
		this.quantityField.clear();
		thneedList.clear();
	}
	
	public void updateCustomerList(Customer newCustomer) {
		customerList.getItems().add(newCustomer.getName());
	}
	
	// Event Listener on Button.onAction
	@FXML
	public void AddNewCustomerButtonClick(ActionEvent event) {
		if(dialogStage == null) 
		{
			//Add Customer Window
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/NewCustomerWindow.fxml"));
			AnchorPane dialogRoot;
			
			try {
				dialogRoot = (AnchorPane)loader.load();
				Scene dialogScene = new Scene(dialogRoot);
				dialogStage = new Stage();
				dialogStage.setTitle("Add Customer");
				dialogStage.setScene(dialogScene);
				NewCustomerController = (NewCustomerWindowController) loader.getController();
				NewCustomerController.setNewOrderController(this);
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		dialogStage.show();
	}
	

	// Event Listener on Button.onAction
	@FXML
	public void AddToOrderButtonClick(ActionEvent event) {
		String size = this.sizeCombo.getValue().toString();
		String color = this.colorCombo.getValue().toString();
		int quantity = Integer.parseInt(quantityField.getText());
		
		Thneed newThneed = new Thneed(size, color, quantity);
		
		//Add it to the arraylist
		thneedList.add(newThneed);
		//Debugging
		if(Main.DEBUG = true) {
			System.out.println("[Debug] AddToOrderButtonClick thneedList: " + thneedList);
		}
		//Add it to the GUI
		orderSummaryList.getItems().add(newThneed.toString());
	}	

	
	// Event Listener on Button.onAction
	@FXML
	public void SaveOrderButtonClick(ActionEvent event) {
		try {
			//Get the name selected in the customerList listview
			String customerName = customerList.getSelectionModel().getSelectedItem().toString();
			Customer selectedCustomer = null;
			
			for(int i = 0; i < Main.customerArrayList.size(); i++) {
				//Get the customer at index i
				Customer currentCustomer = Main.customerArrayList.get(i);
				//Does the customer at index i have a name equal to the listview name selected?
				if(currentCustomer.getName() == customerName) {
					//If so, assign the current customer to the selected customer object.
					selectedCustomer = currentCustomer;
				}
			}
			//Debugging
			if(Main.DEBUG = true) {
				System.out.println("[Debug] SaveOrderButtonClick selectedCustomer: " + selectedCustomer);
			}
			//Get today's date
			String date = new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime());
			
			//Create a new order
			Order newOrder = new Order(thneedList, selectedCustomer.getId(), selectedCustomer.getName(), date, date);
			
			//Debugging
			if(Main.DEBUG = true) {
				System.out.println("[Debug] SaveOrderButtonClick newOrder: " + newOrder.toString());
			}
			//Add it to the Order ArrayList and the Order Listview on the Main Window
			MainController.addOrder(newOrder);
			clearOrderWindow();
			// Close Window
			Stage stage = (Stage) saveButton.getScene().getWindow();
		    stage.close();
			
		} catch(NullPointerException e) {
			System.out.println("[Error] You did not select a Customer.");
		}
	}

}

package application;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.ListView;

import javafx.scene.control.Label;

public class MainWindowController {
	@FXML
	private ListView<String> orderList;
	@FXML
	private Label orderNum;
	@FXML
	private Label customer;
	@FXML
	private Label dateOrdered;
	@FXML
	private TextField dateFilled;
	@FXML
	private Button updateDate;
	@FXML
	private ListView thneedList;
	@FXML
	private Button newOrder;
	@FXML
	private Button saveAll;
	
	private Stage orderDialogStage;
	Stage customerLookupDialogStage;
	
	private NewOrderWindowController orderDialogController;
	private CustomerWindowController customerDialogController;
	
	private Order order = null;
	
	//Initialize method to be called everytime the window is loaded
	public void initialize() {
		//Solution found online for adding a listener when selecting an order
		orderList.getSelectionModel().selectedItemProperty()
		.addListener(new ChangeListener<String>() {

			public void changed(
					ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				//Set Order Details on Selection
				for(int i = 0; i < Main.orderArrayList.size(); i++) {
					//Get the order at index i
					Order currentOrder = Main.orderArrayList.get(i);
					//Does the order at index i have an id equal to the listview id selected?
					if(currentOrder.orderNumGetter() == Integer.parseInt(newValue)) {
						//If so, assign the current order to the selected o object.
						order = currentOrder;
					}
				}
				thneedList.getItems().clear();
				//Start setting the labels based on the order object
				orderNum.setText(Integer.toString(order.orderNumGetter()));
				//Thneeds Ordered
				ArrayList<Thneed> thneedArrayList = order.listGetter();
				if(Main.DEBUG = true) {
					System.out.println("[Debug] Order Details thneedList: " + thneedArrayList);
				}
				for(int i = 0; i < thneedArrayList.size(); i++) {
					Thneed newThneed = thneedArrayList.get(i);
					thneedList.getItems().add(newThneed.toString());
				}
				//Customer Label
				customer.setText(Integer.toString(order.customerIDGetter()));
				//Date Ordered
				dateOrdered.setText(order.orderDateGetter());
				//Date Filled
				dateFilled.setText(order.filledDateGetter());
				
			}
		});
		
		loadCustomers();
		loadOrders();
	}
	
	private void loadCustomers() {
		ArrayList<Customer> customerList = new ArrayList<>();
		
		try {
			FileInputStream fileIn = new FileInputStream("customers.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			customerList = (ArrayList<Customer>) in.readObject();
			
			System.out.println("[Debug] loadCustomer: " + customer);
			in.close();
			fileIn.close();
		} catch (EOFException e) {
			System.out.println("[System] There were no customers to load.");
			return;
		}catch(IOException i) {
			i.printStackTrace();
			return;
		}catch(ClassNotFoundException c){
			System.out.println("[Error] Customer class not found");
			c.printStackTrace();
			return;
		}
		
		for(int i = 0; i < customerList.size(); i++) {
			addCustomer(customerList.get(i));
		}
		
		System.out.println("[System] Customers Loaded");
	}

	private void loadOrders() {
		ArrayList<Order> orderList = new ArrayList<>();
		
		try {
			FileInputStream fileIn = new FileInputStream("orders.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			orderList = (ArrayList<Order>) in.readObject();
			
			if(Main.DEBUG = true) {
				System.out.println("[Debug] loadOrder order: " + order);
			}
			
			in.close();
			fileIn.close();
		} catch (EOFException e) {
			System.out.println("[System] There were no orders to load.");
			return;
		}catch(IOException i) {
			i.printStackTrace();
			return;
		}catch(ClassNotFoundException c){
			System.out.println("[Error] Order class not found");
			c.printStackTrace();
			return;
		}
		
		for(int i = 0; i < orderList.size(); i++) {
			addOrder(orderList.get(i));
		}
		
		System.out.println("[System] Orders Loaded");
	}
	
	//Method to add an order both to the arrayList as well as the listview
	public void addOrder(Order newOrder) {
		Main.orderArrayList.add(newOrder);
		orderList.getItems().add(Integer.toString(newOrder.orderNumGetter()));
		if(Main.DEBUG = true) {
			System.out.println("[Debug] addOrder: " + newOrder);
			System.out.println("[Debug] Main.orderArrayList size: " + Main.orderArrayList.size());
		}
	}
	
	public void addCustomer(Customer customer) {
		Main.customerArrayList.add(customer);
		if(Main.DEBUG = true) {
			System.out.println("[Debug] addCustomer: " + customer);
			System.out.println("[Debug] Main.customerArrayList size: " + Main.customerArrayList.size());
		}
	}
	
	
	/*
	 * Button Controllers
	 */
	
	@FXML
	private void saveOrderButtonClick(ActionEvent event) {
		try {
			File orders = new File("orders.ser");
			orders.createNewFile();
			FileOutputStream fileOut = new FileOutputStream(orders);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			
			if(Main.DEBUG = true) {
				System.out.println("[Debug] saveOrder Main.orderArrayList size: " + Main.orderArrayList.size());
			}
			
			ArrayList<Order> orderList = Main.orderArrayList;
			out.writeObject(orderList);
			out.close();
			fileOut.close();
			System.out.println("[System] Serialized data has been saved in orders.ser");
			
		}catch (IOException i) {
			i.printStackTrace();
		}
	}
	
	@FXML
	private void newOrderButtonClick(ActionEvent event) {
		if(orderDialogStage == null)
		{
			//Setup New Order Window and Show on Click
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/NewOrderWindow.fxml"));
			AnchorPane dialogRoot;
			
			try {
				dialogRoot = (AnchorPane) loader.load();
				Scene dialogScene = new Scene(dialogRoot);
				orderDialogStage = new Stage();
				orderDialogStage.setTitle("New Order");
				orderDialogStage.setScene(dialogScene);
				orderDialogController = (NewOrderWindowController) loader.getController();
				orderDialogController.setCallingController(this);
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		orderDialogStage.show();
	}
	
	
	@FXML
	private void lookupCustomerButtonClick(ActionEvent event) {
		if(customerLookupDialogStage == null)
		{
			//Setup New Order Window and Show on Click
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/CustomerWindow.fxml"));
			AnchorPane dialogRoot;
			
			try {
				dialogRoot = (AnchorPane) loader.load();
				Scene dialogScene = new Scene(dialogRoot);
				customerLookupDialogStage = new Stage();
				customerLookupDialogStage.setTitle("Customer Lookup");
				customerLookupDialogStage.setScene(dialogScene);
				customerDialogController = (CustomerWindowController) loader.getController();
				customerDialogController.setCallingController(this);
				customerDialogController.lookupCustomer(Integer.parseInt(customer.getText()));
				if(Main.DEBUG = true) {
					System.out.println("[Debug] lookupCustomer: " + Integer.parseInt(customer.getText()));
				}
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		customerLookupDialogStage.show();
	}
	
	@FXML
	private void updateDateButtonClick(ActionEvent event) {
		order.filledDateSetter(dateFilled.getText());
		
		if(Main.DEBUG = true) {
			System.out.println("[Debug] updateDateButtonClick updated order: " + order);
		}
	}
	
}

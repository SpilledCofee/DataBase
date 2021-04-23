package mySqlDriver2;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.Date;

public class cust_order_db_remote {
	
	private static String url = "";
	private static String username = "";
	private static String password = "";

	private Scanner in = new Scanner(System.in);

	public static void main(String[] args) {
		cust_order_db_remote drive = new cust_order_db_remote();

		Scanner log = new Scanner(System.in);

		System.out.println("Enter your Username: ");
		username = log.next();

		System.out.println("Enter your Password: ");
		password = log.next();

		drive.displayMenu();
	}

	public void displayMenu(){
		Scanner input = new Scanner(System.in);
		System.out.println("Select a prompt: ");
		System.out.println("a. Search for orders\n"
			+ "b. Add a new order\n"
			+ "c. Update an order\n"
			+ "d. Delete an order\n"
			+ "f. To quit\n");

		String prompt = input.next();
		
		if(prompt.contains("a")) {
			searchOrder();
		}
		if(prompt.contains("b")) {
			addOrder();
		}
		if(prompt.contains("c")) {
			updateOrder();
		}
		if(prompt.contains("d")) {
			deleteOrder();
		}
		if(prompt.contains("f")) {
			System.exit(1);
		}
		else{
			System.out.println("Please enter a valid selection.\n");
			displayMenu();
		}
	}
	public void searchEmail(String email){

		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			PreparedStatement myStmt = connection.prepareStatement("Select * FROM new_customer_orders WHERE user_id = '" + email + "'", ResultSet.TYPE_SCROLL_SENSITIVE, 
			ResultSet.CONCUR_UPDATABLE);
		
			
			ResultSet myRs = myStmt.executeQuery();
			if (myRs.next()) {

				myRs.beforeFirst();

				while (myRs.next()) {
					System.out.println();
					System.out.println("Order ID: " + myRs.getInt("order_id"));
					System.out.println("Date: " + myRs.getString("ordered_at"));
					System.out.println("Status: " + myRs.getString("order_status"));
					System.out.println("Email: " + myRs.getString("user_id"));
					System.out.println("Quantity: " + myRs.getInt("order_quantity"));
					System.out.println("Order Total: " + myRs.getDouble("order_total"));
					System.out.println("Street: " + myRs.getString("shipping_street"));
					System.out.println("City: " + myRs.getString("shipping_city"));
					System.out.println("Street: " + myRs.getString("shipping_state"));
					System.out.println("Zip Code: " + myRs.getString("shipping_zipcode"));
					System.out.println();
				}
			}
		else{
            System.out.println("Sorry, email was not found.");
            System.out.println();
		}


		}catch (SQLException e) {	
			// TODO Auto-generated catch block
			System.out.println("oops, error!");
			e.printStackTrace();
			}

	}
	public void searchZip(String zip){
		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			PreparedStatement myStmt = connection.prepareStatement("Select * FROM new_customer_orders WHERE shipping_zipcode = '" + zip + "'", ResultSet.TYPE_SCROLL_SENSITIVE, 
			ResultSet.CONCUR_UPDATABLE);
		
			ResultSet myRs = myStmt.executeQuery();
			if (myRs.next()) {
				
				myRs.beforeFirst();

				while (myRs.next()) {
					System.out.println();
					System.out.println("Order ID: " + myRs.getInt("order_id"));
					System.out.println("Date: " + myRs.getString("ordered_at"));
					System.out.println("Status: " + myRs.getString("order_status"));
					System.out.println("Email: " + myRs.getString("user_id"));
					System.out.println("Quantity: " + myRs.getInt("order_quantity"));
					System.out.println("Order total: " + myRs.getDouble("order_total"));
					System.out.println("Street: " + myRs.getString("shipping_street"));
					System.out.println("City: " + myRs.getString("shipping_city"));
					System.out.println("Street: " + myRs.getString("shipping_state"));
					System.out.println("Zip Code: " + myRs.getString("shipping_zipcode"));
					System.out.println();
				}
			}
			else{
				System.out.println("Sorry no orders with that zipcode was found");
				System.out.println();
				}
		}catch (SQLException e) {
			
			// TODO Auto-generated catch block
			System.out.println("oops, error!");
			e.printStackTrace();
			}
		
	}
	public void searchProcuctID(String pid){
		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			PreparedStatement myStmt = connection.prepareStatement("Select * FROM new_order_items WHERE product_id = '" + pid + "'", ResultSet.TYPE_SCROLL_SENSITIVE, 
			ResultSet.CONCUR_UPDATABLE);

			ResultSet myRs = myStmt.executeQuery();
				
			if (myRs.next()) {
				
				myRs.beforeFirst();

				while (myRs.next()) {
					System.out.println();
					System.out.println("Order ID: " + myRs.getInt("order_id"));
					System.out.println("Email: " + myRs.getString("user_id"));
					System.out.println("Email: " + myRs.getString("product_id"));
					System.out.println("Quantity: " + myRs.getInt("Quantity"));
					System.out.println("Sale Price: " + myRs.getDouble("sale_price"));
					System.out.println("Item total: " + myRs.getDouble("item_total"));
					System.out.println("Item Sale ID: " + myRs.getString("id"));

					System.out.println();
				}
			}
		else{
            System.out.println("Sorry, product ID was not found.");
            System.out.println();
		}
		}catch (SQLException e) {
			
			// TODO Auto-generated catch block
			System.out.println("oops, error!");
			e.printStackTrace();
			}
		
	}
	public void searchDate(String date){
		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			
			PreparedStatement myStmt = connection.prepareStatement("Select * FROM new_customer_orders WHERE ordered_at Like('%" + date + "%')", ResultSet.TYPE_SCROLL_SENSITIVE, 
			ResultSet.CONCUR_UPDATABLE);
	
			ResultSet myRs = myStmt.executeQuery();
			
			if (myRs.next()) {
				
				myRs.beforeFirst();

				while (myRs.next()) {
					System.out.println();
					System.out.println("Order ID: " + myRs.getInt("order_id"));
					System.out.println("Date: " + myRs.getString("ordered_at"));
					System.out.println("Status: " + myRs.getString("order_status"));
					System.out.println("Email: " + myRs.getString("user_id"));
					System.out.println("Quantity: " + myRs.getInt("order_quantity"));
					System.out.println("Order total: " + myRs.getDouble("order_total"));
					System.out.println("Street: " + myRs.getString("shipping_street"));
					System.out.println("City: " + myRs.getString("shipping_city"));
					System.out.println("Street: " + myRs.getString("shipping_state"));
					System.out.println("Zip Code: " + myRs.getString("shipping_zipcode"));
					System.out.println();
				}
				
			}
		else{
            System.out.println("Sorry,no orders with that date was found.");
			System.out.println("Make sure that the format is correct, or search wont work!");
            System.out.println();
		}
		}catch (SQLException e) {
			
			// TODO Auto-generated catch block
			System.out.println("oops, error!");
			e.printStackTrace();
			}
		
	}
	public void searchOrderID(String oid){
		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			
			PreparedStatement myStmt = connection.prepareStatement("Select * FROM new_customer_orders WHERE order_id = '" + oid + "'");
		
			ResultSet myRs = myStmt.executeQuery();
			if (myRs.next()) {	
				//No while loop cuz cust_id is unique
				System.out.println();
				System.out.println("Order ID: " + myRs.getInt("order_id"));
				System.out.println("Date: " + myRs.getString("ordered_at"));
				System.out.println("Status: " + myRs.getString("order_status"));
				System.out.println("Email: " + myRs.getString("user_id"));
				System.out.println("Quantity: " + myRs.getInt("order_quantity"));
				System.out.println("Order Total: " + myRs.getDouble("order_total"));
				System.out.println("Street: " + myRs.getString("shipping_street"));
				System.out.println("City: " + myRs.getString("shipping_city"));
				System.out.println("Street: " + myRs.getString("shipping_state"));
				System.out.println("Zip Code: " + myRs.getString("shipping_zipcode"));
				System.out.println();
			
			}
		else{
            System.out.println("Sorry, no order with that ID was found.");
            System.out.println();
		}
		}catch (SQLException e) {	
		// TODO Auto-generated catch block
		System.out.println("oops, error!");
		e.printStackTrace();
		}
	}

	public void searchOrder() {
		
		Scanner scanner = new Scanner(System.in);

			System.out.println();
			System.out.println("What field would you like to search by: ");
			System.out.println("a. Customer email.");
			System.out.println("b. Customer Address.");
			System.out.println("c. Product ID");
			System.out.println("d. Date of Order.");
			System.out.println("e. Order ID");
			System.out.println();
			String select = scanner.next();
			

			if(select.contains("a")) {
				
				System.out.println("Enter Customer Email: ");
				String email = scanner.next();
				searchEmail(email);			
			}
			
			if(select.contains("b")) {

				System.out.println("Enter 5 digit ZIP code: ");
				String zip = scanner.next();

				searchZip(zip);
			}
			
			if(select.contains("c")) {
					
				System.out.println("Enter product ID: ");
				String pid = scanner.next();
				searchProcuctID(pid);		
			}
			
			if(select.contains("d")) {

				System.out.println("Enter Date(ex: 2021-01-01): ");
				String date = scanner.next();
				searchDate(date);

			}
			
			if(select.contains("e")) {
				
				System.out.println("Enter Order ID: ");
				String oid = scanner.next();
				searchOrderID(oid);
				
			}
		System.out.println("Would you like to see more details of the order?\n" 
			+ "Yes or no?\n");
		String answer1 = in.nextLine();
		if(answer1.contains("y") || answer1.contains("Y")){
			System.out.println("Enter the order id of the order you would like to view: ");
			int oid2 = scanner.nextInt();
			searchProducts(oid2);
		}
		else{
			displayMenu();
		}

		System.out.println("Would you like to make another search?\n" 
			+ "Yes or no?\n");
		String answer = in.nextLine();
		if(answer.contains("y") || answer.contains("Y")){
			searchOrder();
		}
		else{
			displayMenu();
		}
	
	}

		public void searchProducts(int oid){
			try {
				Connection connection = DriverManager.getConnection(url, username, password);
				
				PreparedStatement myStmt = connection.prepareStatement("SELECT * FROM new_order_items WHERE order_id = '" + oid + "'", ResultSet.TYPE_SCROLL_SENSITIVE, 
				ResultSet.CONCUR_UPDATABLE);
			
				ResultSet myRs = myStmt.executeQuery();
				if (myRs.next()) {	
					
					myRs.beforeFirst();
					System.out.println();
					System.out.println("ITEMS ORDERED");
					System.out.println("----------------");
					System.out.println();
					while (myRs.next()) {
						System.out.println();
						System.out.println("Order ID: " + myRs.getInt("order_id"));
						System.out.println("Email: " + myRs.getString("user_id"));
						System.out.println("Product ID: " + myRs.getString("product_id"));
						System.out.println("Quantity: " + myRs.getInt("quantity"));
						System.out.println("Sale Price: " + myRs.getDouble("sale_price"));
						System.out.println("Item Total: " + myRs.getDouble("item_total"));
						System.out.println();
					}
				
				
				}
			else{
				System.out.println("Sorry, no oder with that ID was found.");
				System.out.println();
			}
			}catch (SQLException e) {	
			// TODO Auto-generated catch block
			System.out.println("oops, error!");
			e.printStackTrace();
			}
		}
		public void addOrder() {

			Scanner scanner = new Scanner(System.in);
			Connection connection = null;
			Statement myStmt = null;
			
			try {
				
				connection = DriverManager.getConnection(url, username, password);
				
				String query = " INSERT INTO new_customer_orders (ordered_at, user_id, shipping_street, shipping_city, shipping_state, shipping_zipcode)" +
						" VALUES (?, ?, ?, ?, ?, ?)";

				String iquery = " INSERT INTO new_order_items (order_id, user_id, product_id, quantity, sale_price, item_total) VALUES (?, ?, ?, ?, ?, ?)";
				

				System.out.println();
				System.out.println();
				System.out.println("Enter Customer Email or User Id: ");
				String userId = scanner.next();
				System.out.println();
				
				scanner.nextLine();
				System.out.println();
				System.out.println("Enter the customer street address: ");
				String street = scanner.nextLine();
				System.out.println();

				System.out.println();
				System.out.println("Enter the customer City: ");
				String city = scanner.nextLine();
				System.out.println();

				System.out.println();
				System.out.println("Enter the customer State(abbreviation only ex: CO for Colorado): ");
				String state = scanner.next();
				System.out.println();

				System.out.println();
				System.out.println("Enter customer zip code: ");
				String custZip = scanner.next();

				while(custZip.length() != 5){
					System.out.println("Invalid zip, try again: ");
					custZip = scanner.next();
				}

				System.out.println();
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			

				PreparedStatement prepStmt = connection.prepareStatement(query);
				prepStmt.setTimestamp(1, timestamp);
				prepStmt.setString (2, userId);
				prepStmt.setString(3, street);
				prepStmt.setString(4, city);
				prepStmt.setString(5, state);
				prepStmt.setString(6, custZip);

				prepStmt.execute();


				
				int cont = 0;
				while(cont == 0){
					System.out.println();
					System.out.println();
					System.out.println("Enter the product id: ");
					int productId = scanner.nextInt();
					System.out.println();

					System.out.println();
					System.out.println();
					System.out.println("Enter the quantity: ");
					int quantity = scanner.nextInt();
					System.out.println();

					PreparedStatement myStmt3 = connection.prepareStatement("Select sale_price FROM new_inventory WHERE product_id = '" + productId + "'");

					ResultSet myRs = myStmt3.executeQuery();

					PreparedStatement prepStmt2 = connection.prepareStatement("SELECT order_id FROM new_customer_orders ORDER BY order_id DESC LIMIT 1");

					ResultSet myRs1 = prepStmt2.executeQuery();

					
					
					
					if (myRs1.next()){
						int oid = myRs1.getInt("order_id");

						if(myRs.next()){
							double price = myRs.getDouble("sale_price");
							double total = quantity * price; 
	
						
						PreparedStatement prepStmt1 = connection.prepareStatement(iquery);
						prepStmt1.setInt(1, oid);
						prepStmt1.setString (2, userId);
						prepStmt1.setInt(3, productId);
						prepStmt1.setInt (4, quantity);
						prepStmt1.setDouble(5, price);
						prepStmt1.setDouble(6, total);
						

						prepStmt1.execute();
						
						PreparedStatement myStmt6 = connection.prepareStatement("SELECT SUM(quantity) total_quantity FROM new_order_items WHERE order_id = '" + oid + "'");

						ResultSet myRs2 = myStmt6.executeQuery();
						if(myRs2.next()){

							int item_total = myRs2.getInt("total_quantity");
							PreparedStatement myStmt7 = connection.prepareStatement("SELECT SUM(item_total) total_cost FROM new_order_items WHERE order_id = '" + oid + "'");
							ResultSet myRs3 = myStmt7.executeQuery();
							if(myRs3.next()){

								
								double order_total = myRs3.getDouble("total_cost");
								PreparedStatement prepStmt3 = connection.prepareStatement("UPDATE new_customer_orders SET order_total = '"+ order_total +"' WHERE order_id = '"+ oid +"'");
								prepStmt3.execute();
								PreparedStatement prepStmt4 = connection.prepareStatement("UPDATE new_customer_orders SET order_quantity = '"+ item_total +"' WHERE order_id = '"+ oid +"'");
								prepStmt4.execute();

						}}
			
					}
				}

			
					System.out.println("Would you like to add another product?");
				String more = scanner.next();
					
				if(more.contains("n")){
					cont = cont +1;
					}
				
				
				
				
				//PreparedStatement myStmt3 = connection.prepareStatement("Select sale_price FROM new_inventory WHERE product_id = '" + productId + "'");
				//ResultSet myRs = myStmt3.executeQuery();
				
				//Should this be something that is auto calculated with the quantity from the frontend or inventory?
				//if(myRs.next()){

					//double price = myRs.getDouble("sale_price");
					//double total = quantity * price; 

				
				}
				
			} catch (SQLException se) {
				
				// TODO Auto-generated catch block
				System.out.println("oops, error!");
				se.printStackTrace();
				
			} 
			try {
				if(connection!=null)
					connection.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
			System.out.println("Would you like to add another order?\n" 
			+ "Yes or no?\n");
		String answer = in.nextLine();
		if(answer.contains("y") || answer.contains("Y")){
			addOrder();
		}
		else{
			displayMenu();
		}
			
	}
	public void updateOrder() {
			
		Scanner scanner = new Scanner(System.in);
		Connection connection = null;
		Statement myStmt = null;
			
		try {
				
			connection = DriverManager.getConnection(url, username, password);

			System.out.println("Do you have the order ID? 'Yes' or 'No'");
			String answer = in.next();
			if(answer.contains("y") || answer.contains("Y")){

			}
			else{
				System.out.println("How would you like to search for the order to update? ");
				System.out.println("a. Customer email.");
				System.out.println("b. Zip.");
				System.out.println("c. Product ID.");
				String input = in.next();
				boolean search = false;
				while(!search){
					if(input.contains("a")){
						System.out.println("Enter Customer Email: ");
						String email = scanner.next();
						searchEmail(email);
						search = true;
						break;
				}
				if(input.contains("b")){
					System.out.println("Enter zip code: ");
					String zip = scanner.next();
					searchZip(zip);
					search = true;
					break;
				}
				if(input.contains("c")){
					System.out.println("Enter product ID: ");
					String pid = scanner.next();
					searchZip(pid);
					search = true;
					break;
				}
				else{
					System.out.println("Please enter a valid option.");
					input = in.next();
				}
			}
		}

		System.out.println("Enter the order ID of the order you would like to update: ");
		String oid = scanner.next();
		searchOrderID(oid);

		System.out.println("Which field would you like to update? ");
		System.out.println("a. Date");
		System.out.println("b. Shipping Information");
		System.out.println("c. Order Details");
		System.out.println("d. Email");
		System.out.println();
					
		String ans = scanner.next();
					
		if(ans.contains("a")) {
						
			System.out.println();
			System.out.println("Enter the new date and time(ex: 2021-01-01 09:50:22): ");
			System.out.println();
			String date = scanner.nextLine();
						
			PreparedStatement upStmt2 = connection.prepareStatement("UPDATE new_customer_orders SET ordered_at = '" + date +"' WHERE order_id = '"+ oid +"'");
			upStmt2.execute();
		}
					
		if(ans.contains("b")) {
						
			System.out.println();
			System.out.println("Enter the new zip code: ");
			System.out.println();
			String zip = scanner.next();
					
			PreparedStatement upStmt2 = connection.prepareStatement("UPDATE new_customer_orders SET shipping_zipcode = '" + zip +"' WHERE order_id = '"+ oid +"'");
			upStmt2.execute();

			scanner.nextLine();
			System.out.println();
			System.out.println("Enter the new street address: ");
			System.out.println();
			String street = scanner.nextLine();
					
			PreparedStatement upStmt3 = connection.prepareStatement("UPDATE new_customer_orders SET shipping_street = '" + street + "' WHERE order_id = '"+ oid +"'");
			upStmt3.execute();

			System.out.println();
			System.out.println("Enter the new city: ");
			System.out.println();
			String city = scanner.nextLine();
					
			PreparedStatement upStmt5 = connection.prepareStatement("UPDATE new_customer_orders SET shipping_city = '" + city +"' WHERE order_id = '"+ oid +"'");
			upStmt5.execute();

			System.out.println();
			System.out.println("Enter the new state(example: CO for Colorado): ");
			System.out.println();
			String state = scanner.nextLine();
					
			PreparedStatement upStmt4 = connection.prepareStatement("UPDATE new_customer_orders SET shipping_state = '" + state +"' WHERE order_id = '"+ oid +"'");
			upStmt4.execute();
						
		}
					
							
		if(ans.contains("c")) {
						

			System.out.println();
			System.out.println("Enter the new Product ID: ");
			System.out.println();
			String pid = scanner.next();
						
			PreparedStatement upStmt3 = connection.prepareStatement("UPDATE new_customer_orders SET product_id = '" + pid +"' WHERE order_id = '"+ oid +"'");
			upStmt3.execute();

			System.out.println();
			System.out.println("Enter the new quantity: ");
			System.out.println();
			String pq = scanner.next();
						
			PreparedStatement upStmt2 = connection.prepareStatement("UPDATE new_customer_orders SET order_quantity = '" + pq +"' WHERE order_id = '"+ oid +"'");
			upStmt2.execute();
						
		}
		if(ans.contains("d")) {
						
			System.out.println();
			System.out.println("Enter the new email: ");
			System.out.println();
			String email2 = scanner.next();
						
			PreparedStatement upStmt2 = connection.prepareStatement("UPDATE new_customer_orders SET user_id = '" + email2 +"' WHERE order_id = '"+ oid +"'");
			upStmt2.execute();
		}				
		} 
		catch (SQLException se) {
				
		// TODO Auto-generated catch block
			System.out.println("oops, error!");
			se.printStackTrace();
		} 
		try {
			if(connection!=null){
			connection.close();}
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		System.out.println("Would you like to update another order?\n" 
			+ "Yes or no?\n");
		String reply = in.next();
		if(reply.contains("y") || reply.contains("Y")){
			updateOrder();
		}
		else{displayMenu();}
			
	}
		
	public void deleteOrder() {
		
		Scanner scanner = new Scanner(System.in);
		Connection connection = null;
		Statement myStmt = null;
			
		try {
				
			connection = DriverManager.getConnection(url, username, password);
					
			System.out.println("Do you have the order ID? Yes or no?");
			String answer = scanner.next();
			if(answer.contains("Y") || answer.contains("y")){
						
				System.out.println("Enter the order ID of the order you would like deleted.");
				String oid = scanner.next();
				searchOrderID(oid);
						
				System.out.println("Are you sure you want to delete this order? 'Yes' or 'No'");
				String ans = scanner.next();
						
				if(ans.contains("y") || ans.contains("Y")) {
					String query = "DELETE FROM new_customer_orders WHERE order_id = '" + oid + "'";
						
					PreparedStatement delStmt = connection.prepareStatement(query);
					delStmt.execute();
				}
			}
			else{
				System.out.println("Enter customer email: ");
				String email = scanner.next();
	
				searchEmail(email);
							
				System.out.println("Enter the order ID of the order you would like deleted.");
				int oid = scanner.nextInt();
				System.out.println();
						
				System.out.println("Are you sure you want to delete this order? 'Yes' or 'No'");
				String ans = scanner.next();
						
				if(ans.contains("y") || ans.contains("Y")) {
					String query = "DELETE FROM new_customer_orders WHERE order_id = '" + oid + "'";
						
					PreparedStatement delStmt = connection.prepareStatement(query);
					delStmt.execute();
				}

			}
		} catch (SQLException se) {
				
			// TODO Auto-generated catch block
			System.out.println("oops, error!");
			se.printStackTrace();	
		} 
		try {
			if(connection!=null){
			connection.close();
			}
		}catch(SQLException se) {se.printStackTrace();}
		
		System.out.println("Would you like to delete another order?\n" 
			+ "Yes or no?\n");
		String answer = in.nextLine();
		if(answer.contains("y") || answer.contains("Y")){
			deleteOrder();
		}
		else{displayMenu();}		
		
	}
}

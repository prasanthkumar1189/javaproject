package bill;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class iceCreamShop {
	public static void main(String[] args) throws ClassNotFoundException,SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/icecreams"; 
        String username = "root"; 
        String password = "root"; 
        
        Scanner scan=new Scanner(System.in);
        System.out.println("Enter your falvour");
        String  iceCreamFlavour =scan.next();
        System.out.println("Enter no of ice creams");
        int quantity=scan.nextInt();
        
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT price FROM iceitems  WHERE  icecreamflavour = ?";  
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, iceCreamFlavour);

            ResultSet resultSet = statement.executeQuery();
            
            if(resultSet.next()) {
                double price = resultSet.getDouble("price");
                System.out.println("for each ice cream ="+price);
                price=price*quantity;
              
                System.out.println("The price of " + iceCreamFlavour + " with quantity " + quantity + " is= Rs" + price);
            } else {
                System.out.println("Product not found");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

	}


}
package suppliertest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class items {
    private String itemname;
    private int itemprice;
    private int supplierid;

    public int getSupplier_id() {
        return supplierid;
    }

    public void setSupplier_id(int supplier_id) {
        this.supplierid = supplier_id;
    }
    
    public String getItem_name() {
        return itemname;
    }

    public void setItem_name(String item_name) {
        this.itemname = item_name;
    }

    public int getItem_price() {
        return itemprice;
    }

    public void setItem_price(int item_price) {
        this.itemprice = item_price;
    }
    public static void deleteItem(String itemn) {
        // Assuming you have a connection object named 'conn'
        String query = "DELETE FROM supplier WHERE item_name = '" + itemn + "'";


        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/supplierss", "root", "");
            Statement stmt = con.createStatement();
            int rowsAffected = stmt.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error deleting supplier.");
        }
    }
    public static items findItem(String item) {
        // Assuming you have a connection object named 'conn'
        String query = "SELECT * FROM item WHERE item_name = '" + item + "'";

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/supplierss", "root", "");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            items i = new items();
            rs.next();
            i.setItem_name(rs.getString("item_name"));
            i.setItem_price(rs.getInt("item_price"));
            i.setSupplier_id(rs.getInt("supplier_id"));
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error finding supplier.");
            return null;
        }
    }
    public static void updateItem(String itemname, int itemprice, int supplierid) {
        // Assuming you have a connection object named 'conn'
        String query = "UPDATE item SET supplier_id = " + supplierid + ", item_price = " + itemprice + " WHERE item_name = '" + itemname + "'";


        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/supplierss", "root", "");
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error updating Item");
        }
    }
    public void additem() {

        String query = "INSERT INTO item (item_name, item_price, supplier_id) VALUES ('"
                + this.itemname.replace("'", "''") + "', " 
                + this.itemprice + ", "                    
                + this.supplierid + ")";       

        try {

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/supplierss", "root", "");
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static List<items> findAllitems() {
        List<items> itemsList = new ArrayList<>();
        String query = "SELECT * FROM item";  // Query to get all suppliers

        try {
            // Assuming you have a valid connection object 'conn'
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/supplierss", "root", "");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Iterate through the result set and add suppliers to the list
            while (rs.next()) {
                items i  = new items();
                i.setItem_name(rs.getString("item_name"));
                i.setItem_price(rs.getInt("item_price"));
                i.setSupplier_id(rs.getInt("supplier_id"));
                // Add the supplier to the list
                itemsList.add(i);
            }

            rs.close();  // Close ResultSet
            stmt.close(); // Close Statement
            con.close();  // Close Connection
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error retrieving suppliers.");
        }

        return itemsList;  // Return the list of suppliers
    }
}

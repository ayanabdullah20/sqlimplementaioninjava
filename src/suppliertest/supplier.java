package suppliertest;

import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class supplier {

    private int supplier_id;
    private String supplier_name;
    private String Address;

    public int getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(int supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getSupplier_name() {
        return supplier_name;
    }

    public void setSupplier_name(String supplier_name) {
        this.supplier_name = supplier_name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public void addsupplier() {

        String query = "INSERT INTO supplier (supplier_id, supplier_name, address) VALUES ("
                + this.supplier_id + ", '"
                + this.supplier_name + "', '"
                + this.Address + "')";
        try {

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/supplierss", "root", "");
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateSupplier(int supplierId, String supplierName, String address) {
        // Assuming you have a connection object named 'conn'
        String query = "UPDATE supplier SET supplier_name = '" + supplierName + "', address = '" + address + "' WHERE supplier_id = " + supplierId;

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/supplierss", "root", "");
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error updating supplier.");
        }
    }

    public static supplier findSupplier(int supplierId) {
        // Assuming you have a connection object named 'conn'
        String query = "SELECT * FROM supplier WHERE supplier_id = " + supplierId;

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/supplierss", "root", "");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            supplier s = new supplier();
            rs.next();
            s.setSupplier_id(rs.getInt("supplier_id"));
            s.setSupplier_name(rs.getString("supplier_name"));
            s.setAddress(rs.getString("address"));
            return s;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error finding supplier.");
            return null;
        }
    }

    public static void deleteSupplier(int supplierId) {
        // Assuming you have a connection object named 'conn'
        String query = "DELETE FROM supplier WHERE supplier_id = " + supplierId;

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/supplierss", "root", "");
            Statement stmt = con.createStatement();
            int rowsAffected = stmt.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error deleting supplier.");
        }
    }
    public static List<supplier> findAllSuppliers() {
        List<supplier> suppliersList = new ArrayList<>();
        String query = "SELECT * FROM supplier";  // Query to get all suppliers

        try {
            // Assuming you have a valid connection object 'conn'
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/supplierss", "root", "");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Iterate through the result set and add suppliers to the list
            while (rs.next()) {
                supplier s = new supplier();
                s.setSupplier_id(rs.getInt("supplier_id"));
                s.setSupplier_name(rs.getString("supplier_name"));
                s.setAddress(rs.getString("address"));
                // Add the supplier to the list
                suppliersList.add(s);
            }

            rs.close();  // Close ResultSet
            stmt.close(); // Close Statement
            con.close();  // Close Connection
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error retrieving suppliers.");
        }

        return suppliersList;  // Return the list of suppliers
    }
}

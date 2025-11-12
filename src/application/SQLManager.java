package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class SQLManager {

    private final String url = "jdbc:mysql://localhost:3306/EmploymentApplication";
    private final String username = "root";
    private final String password = "Sandhu@1";

    // Constructor loads the driver
    public SQLManager() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("❌ MySQL Driver not found.");
        }
    }

    // Method to get a single application by id
    public EmploymentApplication getApplicationById(int id) {
        String query = "SELECT * FROM applications WHERE id = ?";
        EmploymentApplication app = null;
// PREPARED STATEMENT 
        try (Connection con = DriverManager.getConnection(url, username, password);
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setInt(1, id); // Set the id parameter
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                	app = new EmploymentApplication(
                		    rs.getInt("id"),
                		    rs.getString("fullName"),
                		    rs.getString("currentAddress"),
                		    rs.getString("phone"),
                		    rs.getString("email"),
                		    rs.getString("highestEducation"),
                		    Gender.valueOf(rs.getString("gender").toUpperCase()),  // conversion here
                		    rs.getDate("startDate"),
                		    rs.getString("position"),
                		    rs.getDouble("desiredSal"),
                		    rs.getBoolean("isAuthorized"),
                		    rs.getBoolean("relatedEmployees"),
                		    rs.getString("relativeExplain"),
                		    rs.getString("relocate"),
                		    rs.getString("comments"),
                		    rs.getString("signaturePic")
                		);

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return app; // Will return null if no application with this ID exists
    }
    // Method to get all applications
    public List<EmploymentApplication> getApplications() {
        List<EmploymentApplication> applications = new ArrayList<>();
        String query = "SELECT * FROM applications";

        try (Connection con = DriverManager.getConnection(url, username, password);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {
            	EmploymentApplication app = new EmploymentApplication(
            		    rs.getInt("id"),
            		    rs.getString("fullName"),
            		    rs.getString("currentAddress"),
            		    rs.getString("phone"),
            		    rs.getString("email"),
            		    rs.getString("highestEducation"),
            		    Gender.valueOf(rs.getString("gender").toUpperCase()),  // conversion here
            		    rs.getDate("startDate"),
            		    rs.getString("position"),
            		    rs.getDouble("desiredSal"),
            		    rs.getBoolean("isAuthorized"),
            		    rs.getBoolean("relatedEmployees"),
            		    rs.getString("relativeExplain"),
            		    rs.getString("relocate"),
            		    rs.getString("comments"),
            		    rs.getString("signaturePic")
            		);
                applications.add(app);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return applications;
    }
    // Method to insert a new application and give back the id
	public int insertApplication(String fullName, String currentAddress, String phone, String email,
	                             String highestEducation, Gender gender, java.sql.Date startDate,
	                             String position, double desiredSal, boolean isAuthorized,
	                             boolean relatedEmployees, String relativeExplain, String relocate,
	                             String comments, String signaturePic) {
	
	    String query = """
	        INSERT INTO applications 
	        (fullName, currentAddress, phone, email, highestEducation, gender, startDate, position, 
	         desiredSal, isAuthorized, relatedEmployees, relativeExplain, relocate, comments, signaturePic)
	        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
	        """;
	
	    int generatedId = -1;
	
	    try (Connection con = DriverManager.getConnection(url, username, password);
	         PreparedStatement pst = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
	
	        pst.setString(1, fullName);
	        pst.setString(2, currentAddress);
	        pst.setString(3, phone);
	        pst.setString(4, email);
	        pst.setString(5, highestEducation);
	        pst.setString(6, gender.name()); // ✅ store enum as string (e.g. "MALE")
	        pst.setDate(7, startDate);
	        pst.setString(8, position);
	        pst.setDouble(9, desiredSal);
	        pst.setBoolean(10, isAuthorized);
	        pst.setBoolean(11, relatedEmployees);
	        pst.setString(12, relativeExplain);
	        pst.setString(13, relocate);
	        pst.setString(14, comments);
	        pst.setString(15, signaturePic);
	
	        int rowsAffected = pst.executeUpdate();
	
	        if (rowsAffected > 0) {
	            try (ResultSet rs = pst.getGeneratedKeys()) {
	                if (rs.next()) {
	                    generatedId = rs.getInt(1);
	                    System.out.println("✅ Insert successful! New application ID: " + generatedId);
	                }
	            }
	        }
	
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("❌ Failed to insert application.");
	    }
	
	    return generatedId;
	}


}

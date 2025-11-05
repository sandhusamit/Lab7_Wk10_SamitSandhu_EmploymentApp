package application;

import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextInputDialog;

public class ReaderHandler implements EventHandler<ActionEvent> {

    public int id; 
    public SQLManager sql = new SQLManager();
    public EmploymentApplication application; // renamed to avoid JavaFX Application confusion

    
    public ReaderHandler() {
        promptForId();
        
    }

    private void promptForId() {
    	this.id = 0;
        boolean valid = false;
        while (!valid) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Enter ID");
            dialog.setHeaderText("Please enter the application ID:");
            dialog.setContentText("ID:");

            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                try {
                    id = Integer.parseInt(result.get());
                    System.out.println("✅ ID set to: " + id);
                    valid = true;
                } catch (NumberFormatException e) {
                    System.out.println("❌ Invalid input. Please enter an integer.");
                }
            } else {
                System.out.println("❌ No ID entered. Exiting.");
                id = -1;
                valid = true; // or break, depending on how you want to handle cancel
            }
            
        }
		
    }

    @Override
    public void handle(ActionEvent event) {
        try {
            if (id > 0) {
                application = sql.getApplicationById(id);
                if (application != null) {
                    System.out.println("✅ Application fetched for ID: " + id);
                } else {
                    System.out.println("❌ No application found for ID: " + id);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("❌ Error fetching application.");
        }
    }

    public int getId() {
        return id;
    }

    public EmploymentApplication getApplication() {
        return application;
    }
}

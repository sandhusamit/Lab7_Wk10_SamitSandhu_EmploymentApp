package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class OKHandlerClass implements EventHandler<ActionEvent>{
	
	@Override
	public void handle(ActionEvent arg0) {
	
		
		//event logic
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Success");
		alert.setContentText("Your application has been saved successfully! - No DB Connection");
		alert.showAndWait();
		System.out.println("Application Sent");
	}

}

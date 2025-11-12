package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

import java.sql.Date;

/**
 * Handles the submission of the Employment Application form.
 * Collects all user input from form fields, converts them to the correct types,
 * and passes them to SQLManager.insertApplication().
 */
public class SubmitHandler implements EventHandler<ActionEvent> {

    // === Form components ===
    private TextField fullNameField;
    private TextField currentAddressField;
    private TextField phoneField;
    private TextField emailField;
    private TextField highestEducationField;
    private ToggleGroup genderGroup;
    private DatePicker startDatePicker;
    private TextField positionField;
    private TextField desiredSalField;
    private ToggleGroup isAuthorizedGroup;
    private ToggleGroup relatedEmployeesGroup;
    private TextField relativeExplainField;
    private ToggleGroup relocateGroup;
    private TextArea commentsArea;

    /**
     * Constructor for SubmitHandler.
     * @param fullNameField TextField for full name
     * @param currentAddressField TextField for current address
     * @param phoneField TextField for phone
     * @param emailField TextField for email
     * @param highestEducationField TextField for highest education
     * @param genderGroup ToggleGroup for gender
     * @param startDatePicker DatePicker for start date
     * @param positionField TextField for position
     * @param desiredSalField TextField for desired salary
     * @param isAuthorizedGroup ToggleGroup for authorization
     * @param relatedEmployeesGroup ToggleGroup for related employees
     * @param relativeExplainField TextField for explanation if related
     * @param relocateGroup ToggleGroup for relocation preference
     * @param commentsArea TextArea for comments
     */
    public SubmitHandler(TextField fullNameField, TextField currentAddressField,
                         TextField phoneField, TextField emailField,
                         TextField highestEducationField, ToggleGroup genderGroup,
                         DatePicker startDatePicker, TextField positionField,
                         TextField desiredSalField, ToggleGroup isAuthorizedGroup,
                         ToggleGroup relatedEmployeesGroup, TextField relativeExplainField,
                         ToggleGroup relocateGroup, TextArea commentsArea) {

        this.fullNameField = fullNameField;
        this.currentAddressField = currentAddressField;
        this.phoneField = phoneField;
        this.emailField = emailField;
        this.highestEducationField = highestEducationField;
        this.genderGroup = genderGroup;
        this.startDatePicker = startDatePicker;
        this.positionField = positionField;
        this.desiredSalField = desiredSalField;
        this.isAuthorizedGroup = isAuthorizedGroup;
        this.relatedEmployeesGroup = relatedEmployeesGroup;
        this.relativeExplainField = relativeExplainField;
        this.relocateGroup = relocateGroup;
        this.commentsArea = commentsArea;
    }

    @Override
    public void handle(ActionEvent event) {
        try {
            // === 1. Retrieve values from form fields ===
            String fullName = fullNameField.getText();
            String currentAddress = currentAddressField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();
            String highestEducation = highestEducationField.getText();

            // Gender from ToggleGroup
            Gender gender = null;
            Toggle selectedGender = genderGroup.getSelectedToggle();
            if (selectedGender != null) {
                String g = ((RadioButton) selectedGender).getText();
                gender = Gender.valueOf(g.toUpperCase()); // assumes enum names are MALE, FEMALE, OTHER
            }

            // Date conversion
            Date startDate = null;
            if (startDatePicker.getValue() != null) {
                startDate = Date.valueOf(startDatePicker.getValue());
            }

            String position = positionField.getText();

            // Convert desired salary input to double
            double desiredSal = 0;
            try {
                desiredSal = Double.parseDouble(desiredSalField.getText());
            } catch (NumberFormatException e) {
                System.out.println("⚠ Invalid desired salary input, defaulting to 0");
            }

            // Authorization
            boolean isAuthorized = false;
            Toggle selectedAuth = isAuthorizedGroup.getSelectedToggle();
            if (selectedAuth != null) {
                isAuthorized = ((RadioButton) selectedAuth).getText().equalsIgnoreCase("Yes");
            }

            // Related employees
            boolean relatedEmployees = false;
            Toggle selectedRelEmp = relatedEmployeesGroup.getSelectedToggle();
            if (selectedRelEmp != null) {
                relatedEmployees = ((RadioButton) selectedRelEmp).getText().equalsIgnoreCase("Yes");
            }

            // Explanation if related
            String relativeExplain = relatedEmployees ? relativeExplainField.getText() : "";

            // Relocate
            String relocate = "";
            Toggle selectedRelocate = relocateGroup.getSelectedToggle();
            if (selectedRelocate != null) {
                relocate = ((RadioButton) selectedRelocate).getText();
            }

            String comments = commentsArea.getText();

            // === 2. Insert into database ===
            SQLManager sqlManager = new SQLManager();
            int id = sqlManager.insertApplication(fullName, currentAddress, phone, email,
                    highestEducation, gender, startDate, position, desiredSal,
                    isAuthorized, relatedEmployees, relativeExplain, relocate,
                    comments, "samSigned.jpg");

            // === 3. Notify user of success ===
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Application Submitted");
            alert.setHeaderText("Application ID: " + id);
            alert.setContentText("✅ Your application has been submitted successfully!");
            alert.showAndWait();

            System.out.println("✅ Application submitted successfully (ID: " + id + ")");

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Submission Error");
            alert.setHeaderText("Failed to submit application");
            alert.setContentText("❌ Please check your inputs and try again.");
            alert.showAndWait();
        }
    }
}

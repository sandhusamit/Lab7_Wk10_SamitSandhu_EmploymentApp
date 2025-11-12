package application;

import java.time.LocalDate;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // ===== Main Layout =====
        BorderPane mainPane = new BorderPane();

        // ===== Top Image and Title =====
        Image favSport = new Image(getClass().getResource("snowboard.jpg").toExternalForm());
        ImageView iv = new ImageView(favSport);
        iv.setFitWidth(180);
        iv.setPreserveRatio(true);

        Label title = new Label("Employment Application");
        HBox topBox = new HBox(15, iv, title);
        topBox.setAlignment(Pos.CENTER);
        topBox.setPadding(new Insets(10, 0, 5, 0));
        mainPane.setTop(topBox);

        // ===== Center Form =====
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(10));
        pane.setHgap(15);
        pane.setVgap(10);

        // === Name & Address ===
        Label fullNameLbl = new Label("Full Name");
        TextField fullNameTxt = new TextField();
        Label addyLbl = new Label("Current Address");
        TextField addyTxt = new TextField();
        VBox nameBoxLeft = new VBox(2, fullNameLbl, fullNameTxt);
        VBox nameBoxRight = new VBox(2, addyLbl, addyTxt);
        HBox nameRow = new HBox(15, nameBoxLeft, nameBoxRight);
        nameRow.setAlignment(Pos.CENTER_LEFT);
        pane.add(nameRow, 0, 0, 2, 1);

        // === Contact Info ===
        Label phoneLbl = new Label("Contact Number");
        TextField phoneTxt = new TextField();
        Label emailLbl = new Label("Email Address");
        TextField emailTxt = new TextField();
        VBox contactBox = new VBox(5, new HBox(10, phoneLbl, phoneTxt), new HBox(10, emailLbl, emailTxt));
        contactBox.setAlignment(Pos.CENTER_LEFT);
        pane.add(contactBox, 0, 1, 2, 1);

        // === Education & Gender ===
        Label highestEdLbl = new Label("Highest Educational Attainment");
        TextField highestEdTxt = new TextField();
        HBox highestEdBox = new HBox(10, highestEdLbl, highestEdTxt);

        Label genderLbl = new Label("Gender");
        ToggleGroup genderGroup = new ToggleGroup();
        RadioButton male = new RadioButton("Male");
        RadioButton female = new RadioButton("Female");
        RadioButton other = new RadioButton("Other");
        male.setToggleGroup(genderGroup);
        female.setToggleGroup(genderGroup);
        other.setToggleGroup(genderGroup);
        HBox genderBox = new HBox(10, male, female, other);
        VBox genderVBox = new VBox(2, genderLbl, genderBox);

        HBox edGenderRow = new HBox(15, highestEdBox, genderVBox);
        edGenderRow.setAlignment(Pos.CENTER_LEFT);
        pane.add(edGenderRow, 0, 2, 2, 1);

        // === Position & Start Date ===
        Label positionLbl = new Label("Position Desired");
        TextField positionTxt = new TextField();
        Label startDateLbl = new Label("Date Available");
        DatePicker startDatePicker = new DatePicker(LocalDate.now());
        HBox jobRow = new HBox(15, positionLbl, positionTxt, startDateLbl, startDatePicker);
        jobRow.setAlignment(Pos.CENTER_LEFT);
        pane.add(jobRow, 0, 3, 2, 1);

        // === Desired Salary ===
        Label salaryLbl = new Label("Desired Salary ($)");
        TextField salaryTxt = new TextField();
        pane.add(new HBox(10, salaryLbl, salaryTxt), 0, 4, 2, 1);

        // === Authorization ===
        Label authLbl = new Label("Authorized to work in Canada?");
        ToggleGroup authGroup = new ToggleGroup();
        RadioButton authYes = new RadioButton("Yes");
        RadioButton authNo = new RadioButton("No");
        authYes.setToggleGroup(authGroup);
        authNo.setToggleGroup(authGroup);
        pane.add(new VBox(2, authLbl, new HBox(10, authYes, authNo)), 0, 5, 2, 1);

        // === Related Employees ===
        Label relatedLbl = new Label("Do you have relatives working here?");
        ToggleGroup relatedGroup = new ToggleGroup();
        RadioButton relYes = new RadioButton("Yes");
        RadioButton relNo = new RadioButton("No");
        relYes.setToggleGroup(relatedGroup);
        relNo.setToggleGroup(relatedGroup);
        TextField relativeExplainTxt = new TextField();
        relativeExplainTxt.setPromptText("If yes, specify");
        HBox relatedBox = new HBox(10, relYes, relNo, relativeExplainTxt);
        pane.add(new VBox(2, relatedLbl, relatedBox), 0, 6, 2, 1);

        // === Relocate ===
        Label relocateLbl = new Label("Willing to Relocate?");
        ToggleGroup relocateGroup = new ToggleGroup();
        RadioButton relocateYes = new RadioButton("Yes");
        RadioButton relocateNo = new RadioButton("No");
        RadioButton relocateUnsure = new RadioButton("Not Sure");
        relocateYes.setToggleGroup(relocateGroup);
        relocateNo.setToggleGroup(relocateGroup);
        relocateUnsure.setToggleGroup(relocateGroup);
        HBox relocateBox = new HBox(10, relocateYes, relocateNo, relocateUnsure);
        pane.add(new VBox(2, relocateLbl, relocateBox), 0, 7, 2, 1);

        // === Comments ===
        Label commentsLbl = new Label("Comments");
        TextArea commentsArea = new TextArea();
        commentsArea.setPrefRowCount(3);
        pane.add(new VBox(2, commentsLbl, commentsArea), 0, 8, 2, 1);

        // === Buttons ===
        Button submitBtn = new Button("Send Application");
        Button viewBtn = new Button("Review Application");
        Button clearBtn = new Button("Clear Application");
        HBox btnBox = new HBox(15, submitBtn, viewBtn, clearBtn);
        btnBox.setAlignment(Pos.CENTER);
        btnBox.setPadding(new Insets(10, 0, 0, 0));
        pane.add(btnBox, 0, 9, 2, 1);

        mainPane.setCenter(pane);

        // ===== Bottom Date =====
        Label dateLbl = new Label("Today's Date: " + LocalDate.now());
        HBox bottomBox = new HBox(dateLbl);
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.setPadding(new Insets(5));
        mainPane.setBottom(bottomBox);

        // ===== Scene Setup =====
        Scene scene = new Scene(mainPane, 700, 750);
        primaryStage.setTitle("Employment Application Form");
        primaryStage.setScene(scene);
        primaryStage.show();

        // ===== Submit Button Handler =====
        submitBtn.setOnAction(e -> {
            SubmitHandler handler = new SubmitHandler(
                fullNameTxt,
                addyTxt,
                phoneTxt,
                emailTxt,
                highestEdTxt,
                genderGroup,
                startDatePicker,
                positionTxt,
                salaryTxt,
                authGroup,
                relatedGroup,
                relativeExplainTxt,
                relocateGroup,
                commentsArea
            );
            handler.handle(e);
        });

        // ===== Clear Button Handler =====
        clearBtn.setOnAction(e -> {
            fullNameTxt.clear();
            addyTxt.clear();
            phoneTxt.clear();
            emailTxt.clear();
            highestEdTxt.clear();
            genderGroup.selectToggle(null);
            positionTxt.clear();
            startDatePicker.setValue(LocalDate.now());
            salaryTxt.clear();
            authGroup.selectToggle(null);
            relatedGroup.selectToggle(null);
            relativeExplainTxt.clear();
            relocateGroup.selectToggle(null);
            commentsArea.clear();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}

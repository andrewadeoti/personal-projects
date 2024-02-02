package com.mycompany.javafxapplication1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class PrimaryController {

    //Console area playground/////////
    
//    @FXML
//    private Button consoleLsBtn;
//    
//    @FXML
//    private Button consoleTreeBtn;
//    
//    @FXML
//    private TextField consoleIn;
//    
    @FXML
    private TextField Source;
    
    @FXML
    private TextField Destination;
    
    @FXML
    private TextArea textOutput;
    
    //Console area playground/////////
    @FXML
    private Button registerBtn;
    @FXML
    private Button updateDetails_btn;
    @FXML
    private Button update_btn;

    @FXML
    private TextField userTextField;
    @FXML
    private TextField updateUserDetails_txt;
    @FXML
    private TextField updateUserrDetails_txt;
    @FXML
    private TextField update_txt;
    
    

    @FXML
    private PasswordField passPasswordField;

    @FXML
    private void registerBtnHandler(ActionEvent event) {
        Stage secondaryStage = new Stage();
        Stage primaryStage = (Stage) registerBtn.getScene().getWindow();
        DB myObj = new DB();
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("register.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 640, 480);
            secondaryStage.setScene(scene);
            secondaryStage.setTitle("Register a new User");
            secondaryStage.show();
            primaryStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        
        @FXML
    private void update_action(ActionEvent event) {
        Stage secondaryStage = new Stage();
        Stage primaryStage = (Stage) updateDetails_btn.getScene().getWindow();
        //DB myObj = new DB();
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("updateUser.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 640, 480);
            secondaryStage.setScene(scene);
//            secondaryStage.setTitle("Register a new User");
            secondaryStage.show();
            primaryStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // one page to another
    
            @FXML
    private void updatepassword_action(ActionEvent event) {
        Stage secondaryStage = new Stage();
        Stage primaryStage = (Stage) update_btn.getScene().getWindow();
        //DB myObj = new DB();
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("updateUser.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 640, 480);
            secondaryStage.setScene(scene);
         secondaryStage.setTitle("Register a new User");
            secondaryStage.show();
            primaryStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void dialogue(String headerMsg, String contentMsg) {
        Stage secondaryStage = new Stage();
        Group root = new Group();
        Scene scene = new Scene(root, 300, 300, Color.DARKGRAY);

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(headerMsg);
        alert.setContentText(contentMsg);

        Optional<ButtonType> result = alert.showAndWait();
    }

    @FXML
    private void switchToSecondary() {
        Stage secondaryStage = new Stage();
        Stage primaryStage = (Stage) registerBtn.getScene().getWindow();
        try {
            DB myObj = new DB();
            String[] credentials = {userTextField.getText(), passPasswordField.getText()};
            if(myObj.validateUser(userTextField.getText(), passPasswordField.getText())){
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("secondary.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root, 640, 480);
                secondaryStage.setScene(scene);
                SecondaryController controller = loader.getController();
                controller.initialise(credentials);
                secondaryStage.setTitle("Show Users");
                String msg="some data sent from Primary Controller";
                secondaryStage.setUserData(msg);
                secondaryStage.show();
                primaryStage.close();
            }
            else{
                dialogue("Invalid User Name / Password","Please try again!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    //console functions
    
    @FXML
    private void lsConsole(ActionEvent event) throws IOException, InterruptedException
    {
        var console = new ConsoleInput();
        String output = console.ls("");
        textOutput.setText("Output: "+output);
        Source.setText("");
        Destination.setText("");
    }
    
    @FXML
    private void treeConsole(ActionEvent event) throws IOException, InterruptedException
    {
        var console = new ConsoleInput();
        String output = console.tree();
        textOutput.setText("Output: "+output);
        Source.setText("");
        Destination.setText("");
    }
    
    
    //system function
    @FXML
    private void whoAmIConsole(ActionEvent event) throws IOException, InterruptedException
    {
        var console = new ConsoleInput();
        String output = console.whoami();
        textOutput.setText("Output: "+output);
    }
    
    @FXML
    private void psConsole(ActionEvent event) throws IOException, InterruptedException
    {
        var console = new ConsoleInput();
        String output = console.ps();
        textOutput.setText("Output: "+output);
    }
    
    
    
    //file funcitons

    
    
    
    @FXML
    private void createFileConsole(ActionEvent event) throws IOException, InterruptedException
    {
        var console = new ConsoleInput();
        String output = console.makeFile(Source.getText());  
        textOutput.setText("Output: "+output);
        Destination.setText("");
    }
    
    @FXML
    private void deleteFileConsole(ActionEvent event) throws IOException, InterruptedException
    {
        var console = new ConsoleInput();
        String output = console.deleteFile(Source.getText());
        textOutput.setText("Output: "+output);
        Destination.setText("");
    }
    
    @FXML
    private void retriveFileConsole(ActionEvent event) throws IOException, InterruptedException
    {
        var console = new ConsoleInput();
        String output = console.retriveFile(Source.getText());
        textOutput.setText(output);
        Destination.setText("");
    }
    
    @FXML
    private void updateFileConsole(ActionEvent event) throws IOException, InterruptedException
    {
        var console = new ConsoleInput();
        String output = console.updateFile(Source.getText(),textOutput.getText());
        textOutput.setText(output);
        Destination.setText("");
    }
    
    
    @FXML
    private void moveFileConsole(ActionEvent event) throws IOException, InterruptedException
    {
        var console = new ConsoleInput();
        String output = console.mv(Source.getText(),Destination.getText());
        textOutput.setText("Output: "+output);
    }
    
    @FXML
    private void copyFileConsole(ActionEvent event) throws IOException, InterruptedException
    {
        var console = new ConsoleInput();
        String output = console.cp(Source.getText(),Destination.getText());
        textOutput.setText("Output: "+output);
    }
    
    @FXML
    private void makeFolderConsole(ActionEvent event) throws IOException, InterruptedException
    {
        var console = new ConsoleInput();
        String output = console.mkdir(Source.getText());  
        textOutput.setText("Output: "+output);
        Destination.setText("");
    }
}

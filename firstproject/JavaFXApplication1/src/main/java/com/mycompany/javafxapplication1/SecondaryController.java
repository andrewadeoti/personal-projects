package com.mycompany.javafxapplication1;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.util.Optional;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

public class SecondaryController {
    
    ///////Console area playground/////////
    
   
    @FXML
    private TextField Source;
    
    @FXML
    private TextField Destination;
    
    @FXML
    private TextArea textOutput;
    
    ///////Console area playground/////////
    @FXML
    private Button updatepasswordBtn;
    
    @FXML
    private TextField userTextField;
    
    @FXML
    private TableView dataTableView;

    @FXML
    private Button secondaryButton;
    
    @FXML
    private Button refreshBtn;
    
    @FXML
    private TextField customTextField;
    
    @FXML
    private Text fileText;
    
    @FXML
    private Button selectBtn;
    
    @FXML
    private Button openConsoleBtn;
    
    
    private String[] userCredentials;
    
    
    
    @FXML
    private void selectBtnHandler(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) selectBtn.getScene().getWindow();
        primaryStage.setTitle("Select a File");

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.setInitialDirectory(new File ("/home/ntu-user/NetBeansProjects/JavaFxCloud-Storage/JavaFXApplication1"));
        
        File selectedFile = fileChooser.showOpenDialog(primaryStage);
        
        if(selectedFile!=null){
            fileText.setText((String)selectedFile.getCanonicalPath());
        }
        
    }
    
    
    @FXML
    private void RefreshBtnHandler(ActionEvent event){
        Stage primaryStage = (Stage) customTextField.getScene().getWindow();
        customTextField.setText((String)primaryStage.getUserData());
    }
        
    @FXML
    private void switchToPrimary(){
        Stage secondaryStage = new Stage();
        Stage primaryStage = (Stage) secondaryButton.getScene().getWindow();
        try {
            
        
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("primary.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 640, 480);
            secondaryStage.setScene(scene);
            secondaryStage.setTitle("Login");
            secondaryStage.show();
            primaryStage.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    @FXML
    private void openConsoleBtnHandler(ActionEvent event) {
        Stage secondaryStage = new Stage();
        Stage primaryStage = (Stage) openConsoleBtn.getScene().getWindow();
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("console.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 640, 480);
            secondaryStage.setScene(scene);
            secondaryStage.setTitle("Console");
            secondaryStage.show();
            primaryStage.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    @FXML
    private void openupdatepasswordBtnHandler(ActionEvent event) {
        Stage secondaryStage = new Stage();
        Stage primaryStage = (Stage) updatepasswordBtn.getScene().getWindow();
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("updatepassword.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 640, 480);
            secondaryStage.setScene(scene);
            secondaryStage.setTitle("updatepassword");
            secondaryStage.show();
            primaryStage.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    //file funcitons
    @FXML
    private void deleteCurrentUser(ActionEvent event) throws IOException, InterruptedException, SQLException, ClassNotFoundException
    {
        FXMLLoader loader = new FXMLLoader();
        DB myObj = new DB();
        try {
            myObj.deleteUser(userCredentials[0]);
            switchToPrimary();
        } catch (Exception e) {
            switchToPrimary();
        }
        
    }
    
        @FXML
    private void deleteCurrentpassword(ActionEvent event) throws IOException, InterruptedException, SQLException, ClassNotFoundException
    {
        FXMLLoader loader = new FXMLLoader();
        DB myObj = new DB();
        try {
            myObj.deleteUser(userCredentials[0]);
            switchToPrimary();
        } catch (Exception e) {
            switchToPrimary();
        }
        
    }
    
    
    
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

    public void initialise(String[] credentials) {
        userCredentials = credentials;
        userTextField.setText(credentials[0]);
        DB myObj = new DB();
        ObservableList<User> data;
        try {
            data = myObj.getDataFromTable();
            TableColumn user = new TableColumn("User");
        user.setCellValueFactory(
        new PropertyValueFactory<>("user"));

        TableColumn pass = new TableColumn("Pass");
        pass.setCellValueFactory(
            new PropertyValueFactory<>("pass"));
        dataTableView.setItems(data);
        dataTableView.getColumns().addAll(user, pass);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SecondaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

package lk.ijse.chatApp.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.chatApp.client.Client;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class ClientLoginController implements Initializable {
    public Label lblNameError;
    @FXML
    private AnchorPane clientLoginPane;

    @FXML
    private JFXTextField txtUserName;
    

    private Client client;
    private Image image;
    private ClientChatController clientChatController;

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        lblNameError.setVisible(false);
        if (Pattern.matches("^[a-zA-Z\\s]+", txtUserName.getText())) {

            Client client = new Client(txtUserName.getText()); //load client
            Thread thread = new Thread(client); //Runnable interface
            thread.start();
            txtUserName.clear();
            txtUserName.requestFocus();
            //txtUserName.setStyle("-fx-border-color: blue;-fx-border-radius: 10;");
        }else {
            lblNameError.setStyle("-fx-border-color: red;-fx-border-radius: 10;");

            lblNameError.setText("* Can't use Numbers as your name or empty name");

            lblNameError.setVisible(true);
            txtUserName.clear();
            txtUserName.requestFocus();
        }

    }

    @FXML
    void txtUserNameOnAction(ActionEvent event) throws IOException {
        btnLoginOnAction(event);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() ->  txtUserName.requestFocus());
        lblNameError.setVisible(false);
        //txtUserName.setStyle("-fx-border-color: blue;-fx-border-radius: 10;");
    }
}

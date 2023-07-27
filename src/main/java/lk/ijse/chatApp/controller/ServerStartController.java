package lk.ijse.chatApp.controller;

import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.chatApp.server.Server;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ServerStartController implements Initializable {
    @FXML
    private AnchorPane serverStartAnchorPane;

    public ImageView imgFlower;

    @FXML
    void btnServerStartOnAction(ActionEvent event) throws IOException {
        startServer();

        Stage stage = (Stage) serverStartAnchorPane.getScene().getWindow();

        Stage stage2 = new Stage();
        stage2.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/ClientLogin.fxml"))));
        //stage2.setTitle("Live Chat Login");
        stage.close();
        stage2.show();

        /*Notifications notification = NotificationController.notification("Server up & running", "Server Alert");
        notification.show();*/
    }

    private void startServer() throws IOException {
        Runnable server = Server.getServerSocket(); //dependency injection (Runnable interface)
        Thread thread = new Thread(server);
        thread.start();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setNode(imgFlower);
        rotateTransition.setDuration(Duration.millis(10000));
        rotateTransition.setCycleCount(TranslateTransition.INDEFINITE);
        rotateTransition.setByAngle(360);
        rotateTransition.play();
    }
}

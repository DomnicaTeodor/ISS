package com.example;

import com.example.Model.Admin;
import com.example.Service.Service;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginViewAdmin {
    public Button loginButton;
    public TextField username;
    public TextField password;

    Service service;

    public void setService(Service service) {
        this.service = service;
    }

    public void showAfterLogin(Admin admin) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AdminView.fxml"));

        AnchorPane root = loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("LOGIN");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        Scene scene = new Scene(root);
        dialogStage.setScene(scene);

        AdminController afterLoginView = loader.getController();
        afterLoginView.setService(service, admin);
        dialogStage.show();
    }

    public void login(ActionEvent actionEvent) {
        String usernameAdmin = username.getText();
        String passwordAdmin = password.getText();
        Admin result = service.login(usernameAdmin, passwordAdmin);
        if (result != null) {
            System.out.println("Login successful");
            try {
                showAfterLogin(result);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Login failed");
        }
    }
}

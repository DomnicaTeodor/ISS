package com.example;

import com.example.Repository.RepositoryAdmin;
import com.example.Repository.RepositoryBilet;
import com.example.Repository.RepositoryLoc;
import com.example.Repository.RepositoryRezervare;
import com.example.Service.Service;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.IOException;

public class HelloApplication extends Application {

    RepositoryAdmin adminRepository;
    RepositoryLoc locRepository;
    RepositoryRezervare rezervareRepository;
    RepositoryBilet biletRepository;
    Service service;

    @Override
    public void start(Stage stage) throws IOException {

        SessionFactory sessionFactory = instantiateSessionFactory();

        adminRepository = new RepositoryAdmin(sessionFactory);
        locRepository = new RepositoryLoc(sessionFactory);
        rezervareRepository = new RepositoryRezervare(sessionFactory);
        biletRepository = new RepositoryBilet(sessionFactory);
        service = new Service(adminRepository, locRepository, rezervareRepository, biletRepository);


        initView(stage);

        Stage stage2 = new Stage();

        initViewClient(stage2);
        stage.show();
        stage2.show();
    }

    private static SessionFactory instantiateSessionFactory() {
        SessionFactory sessionFactory = null;
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        }
        return sessionFactory;
    }

    private void initView(Stage primaryStage) throws IOException {
        FXMLLoader userLoader = new FXMLLoader();
        userLoader.setLocation(getClass().getResource("LoginViewAdmin.fxml"));
        primaryStage.setScene(new Scene(userLoader.load()));

        LoginViewAdmin loginView = userLoader.getController();
        loginView.setService(service);
    }

    private void initViewClient(Stage primaryStage) throws IOException {
        FXMLLoader userLoader = new FXMLLoader();
        userLoader.setLocation(getClass().getResource("ClientiView.fxml"));
        primaryStage.setScene(new Scene(userLoader.load()));

        ClientController loginView = userLoader.getController();
        loginView.setService(service);
    }

    public static void main(String[] args) {
        launch();
    }
}
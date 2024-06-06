package com.example;

import com.example.Model.Bilet;
import com.example.Model.Loc;
import com.example.Model.Rezervare;
import com.example.Observer.ChangeEventType;
import com.example.Observer.LocChangeEvent;
import com.example.Observer.Observer;
import com.example.Service.Service;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;

public class ClientController implements Observer<LocChangeEvent> {

    public GridPane gridPaneLocuri;
    public Button buton1;
    public Button buton2;
    public Button buton3;
    public Button buton4;
    public Button buton5;
    public Button buton6;
    public Button buton7;
    public Button buton8;
    public Button buton9;
    public Button buton10;
    public Button buton11;
    public Button buton12;
    public Button buton13;
    public Button buton14;
    public Button buton15;
    public Button buton16;
    public Button buton17;
    public Button buton18;

    public List<Button> buttons = new ArrayList<>();
    public TextField numeClient;
    public TextField prenumeClient;
    public TextField nrTelefon;
    public TextField email;
    public Label totalPret;

    Map<Button, Loc> buttonLocMap = new HashMap<>();

    List<Loc> reservedLoc = new ArrayList<>();

    Service service;
    public void setService(Service service) {
        this.service = service;
        populateButtonList();
        setPricesToButtonsInGridPane();
        disableReservedLoc();
        this.service.addObserver(this);
    }

    public void populateButtonList(){
        buttons.add(buton1);
        buttons.add(buton2);
        buttons.add(buton3);
        buttons.add(buton4);
        buttons.add(buton5);
        buttons.add(buton6);
        buttons.add(buton7);
        buttons.add(buton8);
        buttons.add(buton9);
        buttons.add(buton10);
        buttons.add(buton11);
        buttons.add(buton12);
        buttons.add(buton13);
        buttons.add(buton14);
        buttons.add(buton15);
        buttons.add(buton16);
        buttons.add(buton17);
        buttons.add(buton18);
    }

    public void setPricesToButtonsInGridPane(){
        List<Loc> locuri = StreamSupport.stream(service.getLocuri().spliterator(), false).toList();
        int i = 0;
        for(Button button : buttons) {
            button.setText(locuri.get(i).getPret().toString());
            buttonLocMap.put(button, locuri.get(i));
            i++;
        }
    }

    public void selectLoc(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        String text = button.getText();
        System.out.println("Locul cu pretul de " + text + " a fost selectat");
        button.setDisable(true);
    }

    public void disableReservedLoc(){
        reservedLoc = StreamSupport.stream(service.findAllReservedLoc().spliterator(), false).toList();
        for(Loc loc : reservedLoc){
            for(Button button : buttons){
                if(buttonLocMap.get(button).equals(loc)){
                    button.setDisable(true);
                }
            }
        }
    }

    public void makeReservation(ActionEvent actionEvent) {
        String numeClient = this.numeClient.getText();
        String prenumeClient = this.prenumeClient.getText();
        String nrTelefon = this.nrTelefon.getText();
        String email = this.email.getText();

        Rezervare rezervare = new Rezervare(numeClient, prenumeClient, nrTelefon, email);
        service.saveRezervare(rezervare);
        System.out.println("Rezervare efectuata cu succes");

        List<Bilet> rezervate = new ArrayList<>();
        Integer pretTotal = 0;
        // get all Loc for disabled buttons
        for(Button button : buttons){
            if(button.isDisabled()){
                Loc loc = buttonLocMap.get(button);
                if(reservedLoc.contains(loc)){
                    continue;
                }
                loc.setStare(1);
                service.updateLoc(loc);
                // save bilet
                Integer locId = buttonLocMap.get(button).getId();
                Integer rezervareId = service.getLastIdRezervare();
                Bilet bilet = new Bilet(locId, rezervareId);
                rezervate.add(bilet);
                pretTotal += loc.getPret();
            }
        }
        totalPret.setText(pretTotal.toString());
        service.saveBilet(rezervate);
        System.out.println("Bilete salvate cu succes");
    }

    @Override
    public void update(LocChangeEvent t) {
        if(t.getType().equals(ChangeEventType.ADD)){
            disableReservedLoc();
        }
        else if(t.getType().equals(ChangeEventType.UPDATE)) {
            setPricesToButtonsInGridPane();
        }
    }
}

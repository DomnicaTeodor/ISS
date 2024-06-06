package com.example;

import com.example.Model.Admin;
import com.example.Model.Loc;
import com.example.Observer.ChangeEventType;
import com.example.Observer.LocChangeEvent;
import com.example.Observer.Observer;
import com.example.Service.Service;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;


public class AdminController implements Observer<LocChangeEvent> {

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

    Map<Button, Loc> buttonLocMap = new HashMap<>();

    public List<Button> buttons = new ArrayList<>();
    public TextField text;
    Service service;
    Admin currentAdmin;
    public void setService(Service service, Admin admin) {
        this.service = service;
        this.currentAdmin = admin;
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

    public void disableReservedLoc(){
        List<Loc> locuri = StreamSupport.stream(service.findAllReservedLoc().spliterator(), false).toList();
        for(Loc loc : locuri){
            for(Button button : buttons){
                if(buttonLocMap.get(button).equals(loc)){
                    button.setDisable(true);
                }
            }
        }
    }

    public void changePret(ActionEvent actionEvent) {
        String pret = text.getText();
        if(pret.equals("")){
            return;
        }
        for(Button button : buttons){
            if(button.equals(actionEvent.getSource())){
                Loc loc = buttonLocMap.get(button);
                loc.setPret(Integer.parseInt(pret));
                service.updateLoc(loc);
                button.setText(pret);
                break;
            }
        }
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

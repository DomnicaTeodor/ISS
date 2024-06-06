package com.example.Service;

import com.example.Model.Admin;
import com.example.Model.Bilet;
import com.example.Model.Loc;
import com.example.Model.Rezervare;
import com.example.Observer.ChangeEventType;
import com.example.Observer.LocChangeEvent;
import com.example.Observer.Observable;
import com.example.Observer.Observer;
import com.example.Repository.RepositoryAdmin;
import com.example.Repository.RepositoryBilet;
import com.example.Repository.RepositoryLoc;
import com.example.Repository.RepositoryRezervare;

import java.util.ArrayList;
import java.util.List;

public class Service implements Observable<LocChangeEvent> {
    private RepositoryAdmin adminRepository;
    private RepositoryLoc locRepository;
    private RepositoryRezervare rezervareRepository;
    private RepositoryBilet biletRepository;
    private List<Observer<LocChangeEvent>> observableList = new ArrayList<>();

    public Service(RepositoryAdmin adminRepository, RepositoryLoc locRepository, RepositoryRezervare rezervareRepository, RepositoryBilet biletRepository) {
        this.adminRepository = adminRepository;
        this.locRepository = locRepository;
        this.rezervareRepository = rezervareRepository;
        this.biletRepository = biletRepository;
    }

    public Admin login(String username, String password) {
        return adminRepository.findByUsernameAndPassword(username, password);
    }

    public Iterable<Loc> getLocuri() {
        return locRepository.findAll();
    }

    public void updateLoc(Loc loc) {
        locRepository.update(loc);
        notify(new LocChangeEvent(ChangeEventType.UPDATE, loc));
    }

    public void saveRezervare(Rezervare rezervare) {
        rezervareRepository.add(rezervare);
    }

    public Iterable<Bilet> findAllBilet(){
        return biletRepository.findAll();
    }

    public void saveBilet(List<Bilet> bilete){
        for(Bilet b: bilete){
            biletRepository.add(b);
        }
        notify(new LocChangeEvent(ChangeEventType.ADD, null));
    }

    public Integer getLastIdRezervare(){
        return rezervareRepository.getLastId();
    }

    public Iterable<Loc> findAllReservedLoc(){
        return locRepository.findAllReserved();
    }

    @Override
    public void addObserver(Observer<LocChangeEvent> o) {
        observableList.add(o);
    }

    @Override
    public void removeObserver(Observer<LocChangeEvent> o) {
        observableList.remove(o);
    }

    @Override
    public void notify(LocChangeEvent t) {
        observableList.forEach(x -> x.update(t));
    }
}

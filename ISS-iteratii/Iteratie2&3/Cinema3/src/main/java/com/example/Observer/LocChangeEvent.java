package com.example.Observer;

import com.example.Model.Loc;

public class LocChangeEvent implements Event{
    private ChangeEventType type;
    private Loc newZbor;

    private Loc oldZbor;

    public LocChangeEvent(ChangeEventType type, Loc newZbor) {
        this.type = type;
        this.newZbor = newZbor;
    }

    public LocChangeEvent(ChangeEventType type, Loc newZbor, Loc oldZbor) {
        this.type = type;
        this.newZbor = newZbor;
        this.oldZbor = oldZbor;
    }

    public ChangeEventType getType() {
        return type;
    }

    public Loc getNewZbor() {
        return newZbor;
    }

    public Loc getOldZbor() {
        return oldZbor;
    }
}

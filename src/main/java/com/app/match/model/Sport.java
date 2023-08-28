package com.app.match.model;

public enum Sport {

    FOOTBALL(1),
    BASKETBALL(2);

    private int index;

    Sport(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}

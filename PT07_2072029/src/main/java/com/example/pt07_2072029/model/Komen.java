package com.example.pt07_2072029.model;

public class Komen {
    private String name;
    private String komen;

    @Override
    public String toString() {
        return name + " - " + komen;
    }

    public Komen(String name, String komen) {
        this.name = name;
        this.komen = komen;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKomen() {
        return komen;
    }

    public void setKomen(String komen) {
        this.komen = komen;
    }
}

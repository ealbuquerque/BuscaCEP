package com.br.buscacep.model;

import android.database.Cursor;

import org.json.JSONObject;

/**
 * Created by ezequ on 01/07/2017.
 */

public class Address {
    private int id;
    private String cep;
    private String street;
    private String neighborhood;
    private String city;
    private String state;

    public Address(JSONObject json) throws Exception {
        this.cep = json.getString("cep");
        this.street = json.getString("logradouro");
        this.neighborhood = json.getString("bairro");
        this.city = json.getString("localidade");
        this.state = json.getString("uf");
    }

    public Address(Cursor result) {
        this.setId(result.getInt(result.getColumnIndex("id")));
        this.setCep(result.getString(result.getColumnIndex("cep")));
        this.setStreet(result.getString(result.getColumnIndex("street")));
        this.setNeighborhood(result.getString(result.getColumnIndex("neighborhood")));
        this.setCity(result.getString(result.getColumnIndex("city")));
        this.setState(result.getString(result.getColumnIndex("state")));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "CEP: " + this.getCep() + "\n"
                + "Logradouro: " + this.getStreet() + "\n"
                + "Bairro: " + this.getNeighborhood() + "\n"
                + "Cidade: " + this.getCity() + "\n"
                + "Estado: " + this.getState();
    }
}
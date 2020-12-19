package fr.medialo.gsba.core;

import java.time.LocalDate;

/**
 *
 *   ExclFeeLine
 *
 */

public class Fee implements ExclFee {

    protected int id;
    protected int file_id;
    protected int statu_id;
    protected String name;
    protected double cost;
    protected LocalDate date;

    public Fee(){
        this.id = -1;
        this.file_id = -1;
        this.statu_id = 1;
        this.name = "";
        this.cost = 0.0;
        this.date = LocalDate.now();
    }

    public Fee(int id, int file_id, int statu_id, String name, double cost, String date) {
        this.id = id;
        this.file_id = file_id;
        this.statu_id = statu_id;
        this.name = name;
        this.cost = cost;
        this.date = setDateWithString(date);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFile_id() {
        return file_id;
    }

    public void setFile_id(int file_id) {
        this.file_id = file_id;
    }

    public int getStatu_id() {
        return statu_id;
    }

    public void setStatu_id(int statu_id) {
        this.statu_id = statu_id;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCostToString() {
        return this.getCost() + " €";
    }

    public LocalDate setDateWithString(String time){
        return LocalDate.parse(time);
    }

    public boolean getStatu_idBool(){
        return this.statu_id != 1;
    }

}


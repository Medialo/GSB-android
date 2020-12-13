package fr.medialo.gsba.core;

import androidx.annotation.NonNull;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class FeeFile {
    int id;



    LocalDate dateCreated;
    LocalDate dateModified;
    private List<FeeLine> feeList;
    private List<ExcludingFeeLine> excludingFeesList;


    public FeeFile() {

    }


    public FeeFile(LocalDate dateCreated,LocalDate dateModified, List<FeeLine> feeList, List<ExcludingFeeLine> excludingFeesList) {
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.feeList = feeList;
        this.excludingFeesList = excludingFeesList;
    }

    public FeeFile(int id,List<FeeLine> feeList, List<ExcludingFeeLine> excludingFeesList) {
        this.id = id;
        this.dateCreated = LocalDate.now();
        this.dateModified = LocalDate.now();
        this.feeList = feeList;
        this.excludingFeesList = excludingFeesList;
    }

    public FeeFile(List<FeeLine> feeList, List<ExcludingFeeLine> excludingFeesList) {
        this.dateCreated = LocalDate.now();
        this.dateModified = LocalDate.now();
        this.feeList = feeList;
        this.excludingFeesList = excludingFeesList;
    }


    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public String getDateCreatedToString() {
        if(this.dateCreated == null)
            return "Aucune date";
        return "Créé le : "+dateCreated.toString();
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDate getDateModified() {
        return dateModified;
    }

    public String getDateModifiedToString() {
        if(this.dateModified == null)
            return "Aucune date";
        return "Modifié le : "+dateModified.toString();
    }

    public void setDateModified(LocalDate dateModified) {
        this.dateModified = dateModified;
    }

    public List<FeeLine> getFeeList() {
        return feeList;
    }

    public void setFeeList(List<FeeLine> feeList) {
        this.feeList = feeList;
    }

    public List<ExcludingFeeLine> getExcludingFeesList() {
        return excludingFeesList;
    }

    public void setExcludingFeesList(List<ExcludingFeeLine> excludingFeesList) {
        this.excludingFeesList = excludingFeesList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDateCreatedWithString(String time){
        this.dateCreated = LocalDate.parse(time);
    }

    public void setDateModifiedWithString(String time){
        this.dateModified = LocalDate.parse(time);
    }

    @Deprecated
    private LocalDate converter(Date date){
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    @NonNull
    @Override
    public String toString() {
        return "tostring";
    }
}

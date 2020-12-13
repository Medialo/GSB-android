package fr.medialo.gsba.core.category;

import android.util.Pair;

import java.util.HashSet;
import java.util.Set;

public class Category {

    Set<SubCategory> listSubCat;
    private String name;

    public Category(String name) {
        this.listSubCat = new HashSet<>();
        this.name = name;
    }

    public void addSubCatgetory(SubCategory subCategory){
        this.listSubCat.add(subCategory);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

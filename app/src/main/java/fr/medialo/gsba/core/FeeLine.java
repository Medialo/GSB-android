package fr.medialo.gsba.core;

import java.time.LocalDate;
import java.util.HashMap;

import fr.medialo.gsba.core.category.SubCategory;

/**
 *
 *   FeeLine
 *
 *   Manage fee line with extends of fee (excl)
 *
 */
public class FeeLine extends Fee{

    int quantity;
    SubCategory subCategory;

    public FeeLine(){
        super(-1, -1,1, SubCategoryManager.map.get(1).getName(), SubCategoryManager.map.get(1).getCost(), LocalDate.now().toString());
        this.quantity = 1;
        this.subCategory = SubCategoryManager.map.get(1);

    }

    public FeeLine(int id, int file_id, int statu_id, String date, int quantity, SubCategory subCategory) {
        super(id, file_id, statu_id, subCategory.getName(), subCategory.getCost(), date);
        this.quantity = quantity;
        setSubCategory(subCategory);
    }

//    public FeeLine(int file_id, int quantity, SubCategory subCategory) {
//        super(0, file_id, 1, subCategory.getName(), subCategory.getCost(), LocalDate.now().toString());
//        this.quantity = quantity;
//        setSubCategory(subCategory);
//    }

    public FeeLine(int id, int file_id, int statu_id, String date, int quantity, int subCategory) {
      //  HashMap<Integer,SubCategory> map =  SubCategoryManager.map;
        super(id, file_id, statu_id, SubCategoryManager.map.get(subCategory).getName(),  SubCategoryManager.map.get(subCategory).getCost(), date);
        this.subCategory = SubCategoryManager.map.get(subCategory);
        this.quantity = quantity;

    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public double getCost(){
        return this.quantity*this.subCategory.getCost();
    }


    @Override
    @Deprecated
    /**
     * @Deprecated use setSubCategory instead of setCost
     */
    public void setCost(double cost) {
        super.setCost(cost);
    }

    @Override
    public String getCostToString() {
        return this.getCost() + " €";
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
        this.cost = subCategory.getCost();
        this.name = subCategory.getName();
    }

    public double getTotal(){
        return quantity * subCategory.getCost();
    }

    @Override
    public String getName() {
        return this.subCategory.getName();
    }

    @Override
    @Deprecated
    /**
     * @Deprecated use setSubCategory instead of setName
     */
    public void setName(String name) {
      //  super.setName(name);
    }
}

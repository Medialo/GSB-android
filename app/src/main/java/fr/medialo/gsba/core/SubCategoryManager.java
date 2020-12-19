package fr.medialo.gsba.core;

import java.util.HashMap;
import java.util.Map;

import fr.medialo.gsba.core.category.Category;
import fr.medialo.gsba.core.category.SubCategory;

public class SubCategoryManager {


    public static Map<Integer, SubCategory> map;

    public static void init(){
        synchronized (SubCategoryManager.class){
                Category category = new Category("Restauration");
                Category category2 = new Category("Voiture");
                SubCategory subCategory = new SubCategory(1,"Essence",12.5,category2);
                SubCategory subCategory2 = new SubCategory(2,"Gasoil",5,category2);

                SubCategory subCategory3 = new SubCategory(3,"Dejeuné",12,category);
                SubCategory subCategory4 = new SubCategory(4,"Repas",30,category);

                map = new HashMap<>();
                map.put(1,subCategory);
                map.put(2,subCategory2);
                map.put(3,subCategory3);
                map.put(4,subCategory4);


        }

    }



}

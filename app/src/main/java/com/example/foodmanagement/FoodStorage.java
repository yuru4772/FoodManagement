/*
 * Food storage class.
 * Authors: Ziying Zhang, Tianshu Pang, Peng Yan, Yushuo Ruan
 */
package com.example.foodmanagement;

import android.database.Cursor;

import java.util.ArrayList;

public class FoodStorage {

    //food storage contains many ingredients.
    IngredientList storedIngredientList;

    public FoodStorage(DatabaseHelper myDb){
        storedIngredientList = new IngredientList();
        //initialize FoodStorage
        //read ingredient data from either database or csv.
        Cursor res = myDb.getInventoryData();
        if(res.getCount() > 0)
        {
            while(res.moveToNext()){

                Integer ID = res.getInt(0);
                String type= res.getString(1);
                String material = res.getString(2);
                double amount= res.getFloat(3);
                String unit= res.getString(4);
                String storage= res.getString(5);
                String expired= res.getString(6);
                String tags= res.getString(7);

                storeIngredient(ID,type,material,amount,unit,storage,expired,tags);

            }
        }

    }

    //helper function that stores ingredient in to food storage arraylist.
    public void storeIngredient(Integer I, String type, String name, double amount, String unit, String storage, String exp, String tags){

        //construct a ingredient object.
        Ingredient newIngredient = new Ingredient(I, type, name, amount, unit, storage);

        //check expire date
        if(!exp.equals("null")){
            newIngredient.setExpiredDate(exp);
        }
        String[] tagArray = tags.split(",");
        for (String aTagArray : tagArray) {
            newIngredient.addTag(aTagArray);
        }

        storedIngredientList.add(newIngredient);

    }

    //ingredient arraylist getter.
    public IngredientList getIngredients(){
        return storedIngredientList;
    }

}

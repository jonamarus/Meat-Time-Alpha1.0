package com.example.android.meat_timealpha10.Models;

import com.example.android.meat_timealpha10.Models.type.MealType;

import java.util.Date;
import java.util.List;

public class Meal {

  private List<User> users;
  private List<MealRecipe> recipes;
  private List<MealIngredient> ingredients;
  private MealType type;
  private Date date;

  public List<User> getUsers() {
    return users;
  }

  public void setUsers(List<User> users) {
    this.users = users;
  }

  public List<MealRecipe> getRecipes() {
    return recipes;
  }

  public void setRecipes(List<MealRecipe> recipes) {
    this.recipes = recipes;
  }

  public List<MealIngredient> getIngredients() {
    return ingredients;
  }

  public void setIngredients(List<MealIngredient> ingredients) {
    this.ingredients = ingredients;
  }

  public MealType getType() {
    return type;
  }

  public void setType(MealType type) {
    this.type = type;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }
}


package com.example.android.meat_timealpha10.Models.type;

public enum MealType {

  DINNER,
  LUNCH,
  BREAKFAST;

  public static MealType mealType(String value) {
    MealType retval = null;
    for (MealType role : MealType.values()) {
      if (role.name().toLowerCase().equals(value.toLowerCase())) {
        retval = role;
        break;
      }
    }
    return retval;
  }
}

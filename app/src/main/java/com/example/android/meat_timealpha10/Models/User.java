package com.example.android.meat_timealpha10.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by faby on 11/10/17.
 */

public class User {

  @SerializedName("first_name")
  public String firstName;
  @SerializedName("last_name")
  public String lastName;
  public String email;
  @SerializedName("facebook_id")
  public String facebookId;


  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFacebookId() {
    return facebookId;
  }

  public void setFacebookId(String facebookId) {
    this.facebookId = facebookId;
  }

  @Override
  public String toString() {
    return "User{" +
      "firstName='" + firstName + '\'' +
      ", lastName='" + lastName + '\'' +
      ", email='" + email + '\'' +
      '}';
  }
}

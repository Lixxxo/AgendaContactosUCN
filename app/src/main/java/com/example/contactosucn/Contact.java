package com.example.contactosucn;

import androidx.annotation.NonNull;

public class Contact {

  private Integer id;

  private String name;
  private String charge;



  private String unit;
  private String email;
  private String phone;
  private String office;
  private String address;

  private Boolean favorite;


  public Contact(Integer id, String name, String charge, String unit, String email, String phone,
      String office, String address) {
    this.id = id;
    this.name = name;
    this.charge = charge;
    this.unit = unit;
    this.email = email;
    this.phone = phone;
    this.office = office;
    this.address = address;
    this.favorite = false;
  }

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getCharge() {
    return charge;
  }

  public String getUnit() {
    return unit;
  }

  public String getEmail() {
    return email;
  }

  public String getPhone() {
    return phone;
  }

  public String getOffice() {
    return office;
  }

  public String getAddress() {
    return address;
  }

  public Boolean getFavorite() {
    return favorite;
  }

  public void setFavorite(Boolean favorite) {
    this.favorite = favorite;
  }

  @NonNull
  @Override
  public String toString() {
    return "Contact{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", charge='" + charge + '\'' +
        ", unit='" + unit + '\'' +
        ", email='" + email + '\'' +
        ", phone='" + phone + '\'' +
        ", office='" + office + '\'' +
        ", address='" + address + '\'' +
        ", favorite=" + favorite +
        '}';
  }
}

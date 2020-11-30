package com.ys.domain;


public class Goods {

  private int id;
  private String name;
  private int amount;
  private double price;


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  @Override
  public String toString() {
    return "Goods{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", amount=" + amount +
            ", price=" + price +
            '}';
  }
}

package com.ys.domain;


public class Sale {

  private int id;
  private int gid;
  private int nums;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getGid() {
    return gid;
  }

  public void setGid(int gid) {
    this.gid = gid;
  }

  public int getNums() {
    return nums;
  }

  public void setNums(int nums) {
    this.nums = nums;
  }

  @Override
  public String toString() {
    return "Sale{" +
            "id=" + id +
            ", gid=" + gid +
            ", nums=" + nums +
            '}';
  }
}

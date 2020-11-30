package com.ys.vo;

public class StudentAndClassVo {
    private String sName;
    private String cName;

    @Override
    public String toString() {
        return "StudentAndClassVo{" +
                "sName='" + sName + '\'' +
                ", cName='" + cName + '\'' +
                '}';
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }
}

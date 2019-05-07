package com.example.models;

import java.util.Objects;

public class DummyModel {

    private int sNo;

    private String name;

    private int classNo;

    private char classSection;

    public int getsNo() {
        return sNo;
    }

    public String getName() {
        return name;
    }

    public int getClassNo() {
        return classNo;
    }

    public char getClassSection() {
        return classSection;
    }

    public void setsNo(int sNo) {
        this.sNo = sNo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setClassNo(int classNo) {
        this.classNo = classNo;
    }

    public void setClassSection(char classSection) {
        this.classSection = classSection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DummyModel that = (DummyModel) o;
        return sNo == that.sNo &&
                classNo == that.classNo &&
                classSection == that.classSection &&
                name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sNo, name, classNo, classSection);
    }

    public DummyModel(){

    }

    public DummyModel(int sNo, String name, int classNo, char classSection) {
        this.sNo = sNo;
        this.name = name;
        this.classNo = classNo;
        this.classSection = classSection;
    }

    @Override
    public String toString() {
        return "DummyModel{" +
                "sNo=" + sNo +
                ", name='" + name + '\'' +
                ", classNo=" + classNo +
                ", classSection=" + classSection +
                '}';
    }
}

package com.example.models;

import java.util.Date;
import java.util.Objects;

public class DummyModelVersion2 {

    private int sNo;

    private String name;

    private int classNo;

    private char classSection;

    private boolean isPassed;

    private Date admissionDate;

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
        DummyModelVersion2 that = (DummyModelVersion2) o;
        return sNo == that.sNo &&
                classNo == that.classNo &&
                classSection == that.classSection &&
                isPassed == that.isPassed &&
                name.equals(that.name) &&
                admissionDate.equals(that.admissionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sNo, name, classNo, classSection, isPassed, admissionDate);
    }

    public DummyModelVersion2(){

    }

    public DummyModelVersion2(int sNo, String name, int classNo, char classSection, boolean isPassed, Date admissionDate) {
        this.sNo = sNo;
        this.name = name;
        this.classNo = classNo;
        this.classSection = classSection;
        this.isPassed = isPassed;
        this.admissionDate = admissionDate;
    }

    @Override
    public String toString() {
        return "DummyModel{" +
                "sNo=" + sNo +
                ", name='" + name + '\'' +
                ", classNo=" + classNo +
                ", classSection=" + classSection +
                ", isPassed=" + isPassed +
                ", admissionDate=" + admissionDate +
                '}';
    }
}

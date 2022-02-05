package com.example.madproject.model;

public class Hallinfo {
    String hallname,halllocation,hallcapacity,hallcity,hallownername;

    public Hallinfo(String hallname, String halllocation, String hallcapacity, String hallcity,String hallownername) {
        this.hallname = hallname;
        this.halllocation = halllocation;
        this.hallcapacity = hallcapacity;
        this.hallcity = hallcity;
        this.hallownername = hallownername;
    }

    public String getHallownername() {
        return hallownername;
    }

    public void setHallownername(String hallownername) {
        this.hallownername = hallownername;
    }

    public Hallinfo() {
    }

    public String getHallname() {
        return hallname;
    }

    public void setHallname(String hallname) {
        this.hallname = hallname;
    }

    public String getHalllocation() {
        return halllocation;
    }

    public void setHalllocation(String halllocation) {
        this.halllocation = halllocation;
    }

    public String getHallcapacity() {
        return hallcapacity;
    }

    public void setHallcapacity(String hallcapacity) {
        this.hallcapacity = hallcapacity;
    }

    public String getHallcity() {
        return hallcity;
    }

    public void setHallcity(String hallcity) {
        this.hallcity = hallcity;
    }
}

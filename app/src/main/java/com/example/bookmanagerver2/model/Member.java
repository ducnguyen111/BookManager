package com.example.bookmanagerver2.model;

public class Member {
    private int id;
    private String name;
    private int nameSinh;

    public Member() {
    }

    public Member(int id, String name, int nameSinh) {
        this.id = id;
        this.name = name;
        this.nameSinh = nameSinh;
    }

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

    public int getNameSinh() {
        return nameSinh;
    }

    public void setNameSinh(int nameSinh) {
        this.nameSinh = nameSinh;
    }
}

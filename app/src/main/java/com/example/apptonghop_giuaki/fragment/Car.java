package com.example.apptonghop_giuaki.fragment;

import androidx.fragment.app.Fragment;

import java.io.Serializable;

public class Car extends Fragment {
    String name,describe,image;

    Car() {

    }
    public Car(String name, String describe, String image) {
        this.name = name;
        this.describe = describe;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

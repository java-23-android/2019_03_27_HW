package com.sheygam.java_23_27_03_19_hw;

import java.util.Objects;

public class Profile {
    String name = "";
    String lastName = "";
    String phone = "";
    String city = "";

    public Profile() {
    }

    public Profile(String name, String lastName, String phone, String city) {
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.city = city;
    }

    @Override
    public String toString() {
        return name +","+lastName+","+phone+","+city;
    }

    public static Profile fromString(String data){
        Objects.requireNonNull(data);
        String[] arr = data.split(",");
        try {
            return new Profile(arr[0], arr[1], arr[2], arr[3]);
        }catch (ArrayIndexOutOfBoundsException ex){
            return new Profile();
        }
    }
}

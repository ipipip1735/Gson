package entity;

import com.google.gson.annotations.Since;

/**
 * Created by Administrator on 2020/12/2.
 */
public class Cattle {
    @Since(1.2)
    int age;
    @Since(1)
    String name;
    @Since(1.1)
    static String gender = "female";

    public Cattle(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

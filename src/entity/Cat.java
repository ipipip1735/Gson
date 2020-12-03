package entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2020/12/2.
 */
public class Cat {
    @SerializedName("x")
    public String cat_name;
    public int age;

    public Cat(String name, int age) {
        this.cat_name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "entity.Cat{" +
                "cat_name='" + cat_name + '\'' +
                ", age=" + age +
                '}';
    }
}

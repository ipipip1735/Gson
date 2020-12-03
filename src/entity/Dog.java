package entity;

/**
 * Created by Administrator on 2020/12/2.
 */
public class Dog {

    String name;
    int age;
    static String gender = "female";
    transient float weight = 12.4f;

    public Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

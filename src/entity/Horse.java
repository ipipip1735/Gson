package entity;

/**
 * Created by Administrator on 2020/12/5.
 */
public class Horse {

    private String name;
    private int age;

    public Horse(String name, int age) {
        System.out.println("~~Horse.Horse2~~");
        System.out.println("name = " + name + ", age = " + age);
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Horse{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

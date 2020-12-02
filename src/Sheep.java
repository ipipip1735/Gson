import com.google.gson.annotations.Expose;

/**
 * Created by Administrator on 2020/12/2.
 */
public class Sheep {
    @Expose
    String name;
    @Expose
    float weight;
    int age;

    public Sheep(String name, int age, float weight) {
        this.name = name;
        this.age = age;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Sheep{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", age=" + age +
                '}';
    }
}

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2020/12/2.
 */
public class Cat {
    @SerializedName("x")
    String cat_name;
    int age;

    public Cat(String name, int age) {
        this.cat_name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "cat_name='" + cat_name + '\'' +
                ", age=" + age +
                '}';
    }
}

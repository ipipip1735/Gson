package entity;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Created by Administrator on 2020/12/3.
 */
public class Circle {
    @JsonAdapter(value = PointTypeAdatper.class, nullSafe = false)
    public Point point;
    public float radius;

    public Circle(Point point, float radius) {
        this.point = point;
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "point=" + point +
                ", radius=" + radius +
                '}';
    }

    class PointTypeAdatper extends TypeAdapter<Point> {

        @Override
        public void write(JsonWriter out, Point value) throws IOException {
            System.out.println("~~PointTypeAdatper.write~~");
            System.out.println("out = " + out + ", value = " + value);

            out.value("Point(" + value.x + "," + value.y + ")");

        }

        @Override
        public Point read(JsonReader in) throws IOException {
            System.out.println("~~PointTypeAdatper.read~~");
            System.out.println("in = " + in);

            String json = in.nextString();
            System.out.println(json);
            String[] parts = json.substring(6, json.length() - 1).split(",");
            float x = Float.valueOf(parts[0]);
            float y = Float.valueOf(parts[1]);
            return new Point(x, y);
        }
    }
}

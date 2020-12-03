package entity;

/**
 * Created by Administrator on 2020/12/2.
 */
public class Point {
    public float x, y;
//    public Integer integer;
//
//    public Point(float x, float y, Integer integer) {
//        this.x = x;
//        this.y = y;
//        this.integer = integer;
//    }

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "entity.Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

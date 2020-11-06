import com.google.gson.annotations.Expose;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2020/11/6 15:57.
 */
public class Zone {

    @Expose
    public String id;
    @Expose
    public Date createTime;
    @Expose
    public float speed;
    @Expose
    public String flyTaskName;
    @Expose
    public float flyHeight;
    @Expose
    public float lineOverlap;
    @Expose
    public float headingOverlap;
    @Expose
    public float rotatePitch;
    @Expose
    public String coordinate;
    @Expose
    public List<WayPoint> wayPointList;

    @Override
    public String toString() {
        return "Zone{" +
                "id='" + id + '\'' +
                ", createTime=" + createTime +
                ", speed=" + speed +
                ", flyTaskName='" + flyTaskName + '\'' +
                ", flyHeight=" + flyHeight +
                ", lineOverlap=" + lineOverlap +
                ", headingOverlap=" + headingOverlap +
                ", rotatePitch=" + rotatePitch +
                ", coordinate='" + coordinate + '\'' +
                ", wayPointList=" + wayPointList +
                '}';
    }
}

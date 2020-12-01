import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.time.Instant;
import java.util.Date;

/**
 * Created by Administrator on 2020/12/1.
 */
public class GsonBuilderTrial {
    public static void main(String[] args) {
        GsonBuilderTrial gsonBuilderTrial = new GsonBuilderTrial();
        gsonBuilderTrial.dateFormate();
    }

    private void dateFormate() {

        //方式一
//        Gson gson = new GsonBuilder()
//                .setDateFormat(DateFormat.FULL, DateFormat.FULL)
//                .create();
//
//        Date date = new Date();
//        String dataJson = gson.toJson(date);
//        System.out.println("dataJson = " + dataJson);
//
//        date = gson.fromJson(dataJson, Date.class);
//        System.out.println(date);



        //方式二
        Gson gson = new GsonBuilder()
//                .setDateFormat("")
                .create();

//        System.out.println(Timestamp.from(Instant.now()));
//        System.out.println(Instant.now().getEpochSecond());

        Timestamp timestamp = Timestamp.from(Instant.now());
        String dataJson = gson.toJson(timestamp);
        System.out.println("dataJson = " + dataJson);

        timestamp = gson.fromJson(dataJson, Timestamp.class);
        System.out.println(timestamp);


        //方式三
//        Gson gson = new GsonBuilder()
//                .setDateFormat("")
//                .create();

//        Timestamp timestamp = Timestamp.from(Instant.now());
//        String dataJson = gson.toJson(timestamp);
//        System.out.println("dataJson = " + dataJson);
//
//        timestamp = gson.fromJson(dataJson, Timestamp.class);
//        System.out.println(timestamp);




    }
}

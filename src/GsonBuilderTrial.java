import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.time.Instant;
import java.util.*;

/**
 * Created by Administrator on 2020/12/1.
 */
public class GsonBuilderTrial {
    public static void main(String[] args) {
        GsonBuilderTrial gsonBuilderTrial = new GsonBuilderTrial();
//        gsonBuilderTrial.dateFormate();
//        gsonBuilderTrial.disableHtmlEscaping();
//        gsonBuilderTrial.disableInnerClassSerialization();
//        gsonBuilderTrial.serializeNulls();
//        gsonBuilderTrial.setLongSerializationPolicy();
//        gsonBuilderTrial.setFieldNamingPolicy();
//        gsonBuilderTrial.setFieldNamingStrategy();
//        gsonBuilderTrial.excludeFieldsWithModifiers();
//        gsonBuilderTrial.excludeFieldsWithoutExposeAnnotation();
//        gsonBuilderTrial.setVersion();
//        gsonBuilderTrial.enableComplexMapKeySerialization();
//        gsonBuilderTrial.registerTypeAdapter();


        gsonBuilderTrial.annotationWithserializedName();


//        gsonBuilderTrial.writer();
//        gsonBuilderTrial.reader();



    }

    private void annotationWithserializedName() {

        Cat cat = new Cat("Tom", 2);
        Gson gson = new GsonBuilder().create();
        String dataJson = gson.toJson(cat);
        System.out.println("dataJson = " + dataJson);


        cat = gson.fromJson(dataJson, Cat.class);
        System.out.println(cat);

    }

    private void reader() {


        try (FileInputStream fileInputStream = new FileInputStream(new File("res/a.json"));
             JsonReader reader = new JsonReader(new InputStreamReader(fileInputStream, "UTF-8"))) {


            System.out.println(reader.hasNext());
            System.out.println(reader.getPath());

            reader.beginArray();
            System.out.println(reader.nextString());


            //获取JsonToken做判断
//            JsonToken jsonToken = reader.peek();
//            System.out.println("jsonToken = " + jsonToken);
//
//            switch (reader.peek()) {
//                case NAME:
//                    System.out.println(reader.nextName());break;
//                case STRING:
//                    System.out.println(reader.nextName());break;
//            }




        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void writer() {

        try (FileOutputStream fileOutputStream = new FileOutputStream(new File("res/a.json"));
             JsonWriter writer = new JsonWriter(new OutputStreamWriter(fileOutputStream, "UTF-8"))) {

            writer.beginArray();
            writer.value("aaa");
            writer.jsonValue("111");
            writer.beginObject();
            writer.name("one");
            writer.value(111L);
            writer.endObject();
            writer.endArray();

            System.out.println(writer.getSerializeNulls());


        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void registerTypeAdapter() {

        Point point = new Point(1.2f, 2.3f);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Point.class, new TypeAdapter<Point>() {
                    @Override
                    public void write(JsonWriter out, Point value) throws IOException {
                        System.out.println("~~TypeAdapter.write~~");
                        out.value("Point(" + value.x + "," + value.y + ")");
                    }

                    @Override
                    public Point read(JsonReader in) throws IOException {
                        System.out.println("~~TypeAdapter.read~~");

                        String json = in.nextString();
                        System.out.println(json);

                        String[] parts = json.substring(6, json.length() - 1).split(",");
                        float x = Float.valueOf(parts[0]);
                        float y = Float.valueOf(parts[1]);
                        return new Point(x, y);
                    }
                })
                .create();
        String dataJson = gson.toJson(point);
        System.out.println("dataJson = " + dataJson);

        point = gson.fromJson(dataJson, Point.class);
        System.out.println("point = " + point);


    }

    private void enableComplexMapKeySerialization() {

        Map<Cat, String> cats = new HashMap<>();
        cats.put(new Cat("Tom", 2), "one");
        cats.put(new Cat("Sam", 4), "two");

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Cat.class, new TypeAdapter<Cat>() {
                    @Override
                    public void write(JsonWriter out, Cat value) throws IOException {
                        out.value("(" + value.cat_name + ")");
                    }

                    @Override
                    public Cat read(JsonReader in) throws IOException {
                        return null;
                    }
                })
                .enableComplexMapKeySerialization()
                .create();
        String dataJson = gson.toJson(cats);
        System.out.println("dataJson = " + dataJson);

    }

    private void setVersion() {
        Cattle cattle = new Cattle("Luck", 2);

        Gson gson = new GsonBuilder()
                .setVersion(1.1)
                .create();
        String dataJson = gson.toJson(cattle);
        System.out.println("dataJson = " + dataJson);
    }

    private void excludeFieldsWithoutExposeAnnotation() {

        Sheep sheep = new Sheep("Luck", 2, 82.23f);

        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        String dataJson = gson.toJson(sheep);
        System.out.println("dataJson = " + dataJson);

    }

    private void excludeFieldsWithModifiers() {

        Dog dog = new Dog("Luck", 2);

        Gson gson = new GsonBuilder()
                .excludeFieldsWithModifiers(Modifier.TRANSIENT)
//                .excludeFieldsWithModifiers(Modifier.STATIC)
                .create();
        String dataJson = gson.toJson(dog);
        System.out.println("dataJson = " + dataJson);

    }

    private void setFieldNamingStrategy() {

        Cat cat = new Cat("Tom", 10);

        Gson gson = new GsonBuilder()
                .setFieldNamingStrategy(new FieldNamingStrategy() {
                    @Override
                    public String translateName(Field f) {
                        System.out.println("field is " + f.getName());
                        return f.getName() + "xxx";
                    }
                })
                .create();
        String dataJson = gson.toJson(cat);
        System.out.println("dataJson = " + dataJson);

    }

    private void setFieldNamingPolicy() {

        Cat cat = new Cat("Tom", 10);

        Gson gson = new GsonBuilder()
//                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .create();
        String dataJson = gson.toJson(cat);
        System.out.println("dataJson = " + dataJson);


    }

    private void setLongSerializationPolicy() {

        Long l = 12L;

        Gson gson = new GsonBuilder()
//                .setLongSerializationPolicy(LongSerializationPolicy.DEFAULT)
//                .setLongSerializationPolicy(LongSerializationPolicy.STRING)
                .create();
        String dataJson = gson.toJson(l);
        System.out.println("dataJson = " + dataJson);


    }

    private void serializeNulls() {

        Cat cat = new Cat(null, 10);

        Gson gson = new GsonBuilder()
//                .serializeNulls()
                .create();
        String dataJson = gson.toJson(cat);
        System.out.println("dataJson = " + dataJson);

    }

    private void disableInnerClassSerialization() {

        Person person = new Person("Bob", 12.5);

        Gson gson = new GsonBuilder()
                .disableInnerClassSerialization()
                .create();
        String dataJson = gson.toJson(person);
        System.out.println("dataJson = " + dataJson);

    }

    private void disableHtmlEscaping() {

        Gson gson = new GsonBuilder()
//                .disableHtmlEscaping()
                .create();


        String json = "<html></html>";

        String dataJson = gson.toJson(json);
        System.out.println("dataJson = " + dataJson);


    }

    private void dateFormate() {

//        System.out.println(Timestamp.from(Instant.now()));
//        System.out.println(Instant.now().getEpochSecond());


        //方式一
//        Gson gson = new GsonBuilder()
//                .setDateFormat("yyyy-MM-dd HH:mm:ss")
////                .setDateFormat(DateFormat.FULL, DateFormat.FULL)
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
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();

        Timestamp timestamp = Timestamp.from(Instant.now());
        String dataJson = gson.toJson(timestamp);
        System.out.println("dataJson = " + dataJson);

        timestamp = gson.fromJson(dataJson, Timestamp.class);
        System.out.println(timestamp.getTime());

    }

}

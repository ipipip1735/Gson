import com.google.gson.*;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.reflect.TypeToken;
import entity.ActorGson;
import entity.Dog;
import entity.Horse;
import entity.Movie;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2020/11/6 14:18.
 */
public class Deserialization {

    public static void main(String[] args) {

        Deserialization deserialization = new Deserialization();


//        deserialization.basicType();//解析原始类型
//        deserialization.arrayType();//解析数组
//        deserialization.collectionType();//解析容器
//        deserialization.dateType();//解析日期
//        deserialization.jsonToJava();//解析Java对象

//        deserialization.jsonToJavaWithDeserializer();//使用解析器
//        deserialization.collectionTypeDeserializer();//使用解析器解析容器

//        deserialization.jsonToJavaWithInstanceCreator();//使用实例构建器

//        deserialization.genericJson();//解析泛型


//        deserialization.getFeile();//获取JSON对象字段
        deserialization.getElement();//获取JSON数组元素
    }

    private void getElement() {

        Gson gson = new Gson();

        String json = gson.toJson(Arrays.asList("one", "two", "three"));
        System.out.println("json = " + json);


        JsonElement jsonElement = JsonParser.parseString(json);

        json = jsonElement.getAsJsonArray().get(0).toString();
        System.out.println("json = " + json);

        System.out.println("s = " + gson.fromJson(json, String.class));

    }

    private void getFeile() {

        Gson gson = new Gson();

        String json = gson.toJson(new Horse("one", 11));
        System.out.println("json = " + json);


        JsonElement jsonElement = JsonParser.parseString(json);
        json = jsonElement.getAsJsonObject().get("name").toString();
        System.out.println("json = " + json);
        String name = gson.fromJson(json, String.class);
        System.out.println("name = " + name);

        json = jsonElement.getAsJsonObject().get("age").toString();
        System.out.println("json = " + json);
        int age = gson.fromJson(json, int.class);
        System.out.println("age = " + age);



    }

    private void genericJson() {

        List<Horse> horses = new ArrayList<>();
        horses.add(new Horse("one", 11));
        horses.add(new Horse("two", 22));

        Type horsesType = new TypeToken<List<Horse>>() {}.getType();

        Gson gson = new Gson();
        String json = gson.toJson(horses, horsesType);
        System.out.println("json = " + json);


        horses = gson.fromJson(json, horsesType);
        System.out.println(horses);
        System.out.println(horses.get(0).name);





    }

    private void jsonToJavaWithInstanceCreator() {


        InstanceCreator<Horse> horseInstanceCreator = new InstanceCreator<Horse>() {
            @Override
            public Horse createInstance(Type type) {
                System.out.println("~~Deserialization.createInstance~~");
                System.out.println("type = " + type);
                return new Horse("Onyx", 2);
            }
        };

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Horse.class, horseInstanceCreator)
                .create();

//        String json = gson.toJson(new Horse("Opie", 2));
//        System.out.println("json = " + json);

        Horse horse = gson.fromJson("{\"name\":\"Opie\",\"age\":2}", Horse.class);
        System.out.println("horse = " + horse);

    }

    private void collectionTypeDeserializer() {

        //方式一：使用解析器
//        Gson gson = new GsonBuilder()
//                .registerTypeAdapter(List.class, new JsonDeserializer<List>(){
//                    @Override
//                    public List deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
//                        System.out.println("~~deserialize~~");
//                        System.out.println("json is " + json);
//                        System.out.println("typeOfT is " + typeOfT);
//                        System.out.println("context is " + context);
//
//                        JsonArray jsonArray = json.getAsJsonArray();
//                        for (JsonElement jsonElement : jsonArray) {
//                            System.out.println(jsonElement);
//                        }
//
//                        int i = jsonArray.get(0).getAsInt();
//                        String s = jsonArray.get(1).getAsString();
//
//
//                        Map<String , Integer> map = new HashMap<>();
//                        JsonObject jsonObject = jsonArray.get(2).getAsJsonObject();
//                        map.put("one", jsonObject.get("one").getAsInt());
//                        map.put("two", jsonObject.get("two").getAsInt());
//                        map.put("three", jsonObject.get("three").getAsInt());
//
//
//
//                        return Arrays.asList(i, s, map);
//                    }
//                })
//                .create();


        //方式二：直接解析
        Gson gson = new Gson();

        String json = "" +
                "[\n" +
                "\t12,\n" +
                "\t\"abcd\",\n" +
                "\t{\"one\": 111, \"two\": 22, \"three\": 3}\n" +
                "]\n";

        List list = gson.fromJson(json, List.class);

        System.out.println(list);


    }

    private void dateType() {

        //方式一
        Gson gson = new Gson();
        Date date = gson.fromJson("\"2020-09-21\"", Date.class);
        System.out.println(date);


        //方式二：使用解析器
//        Gson gson = new GsonBuilder()
//                .registerTypeAdapter(Date.class, new JsonDeserializer<Date>(){
//                    @Override
//                    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
//                        System.out.println("~~deserialize~~");
//                        System.out.println("json is " + json);
//                        System.out.println("typeOfT is " + typeOfT);
//                        System.out.println("context is " + context);
//
//                        String s = json.getAsString();
//                        System.out.println(s);
//
//                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                        Date date = null;
//                        try {
//                            date = sdf.parse(s);
//                        } catch (ParseException e) {
//                            e.printStackTrace();
//                        }
//
//                        return date;
//                    }
//                })
//                .create();
//
//        Date date = gson.fromJson("\"2020-09-15 15:05:35\"", Date.class);
//        System.out.println(date);


    }

    private void arrayType() {

        //解析JSON数组
//        Gson gson = new Gson();
//        String[] strings = gson.fromJson("[\"aa\", \"bb\", \"cc\"]", String[].class);
//        Arrays.stream(strings).forEach(System.out::println);


        //解析JSON二维数组
        double[][] coordinate = new double[2][3];
        Gson gson = new Gson();

        String json = "[[11.0,12.0],[14.0,15.0]]";
        coordinate = gson.fromJson(json, double[][].class);
        Arrays.stream(coordinate).forEach(System.out::println);

    }

    private void collectionType() {

        //解析List
//        Gson gson = new Gson();
//        List<String> strings = gson.fromJson("[\"aa\", \"bb\", \"cc\"]", List.class);
//        for (String s : strings) System.out.println("s is " + s);


        //解析Set
//        Gson gson = new Gson();
//        Set<String> strings = gson.fromJson("[\"aa\", \"bb\", \"cc\"]", Set.class);
//        for (String s : strings) System.out.println("s is " + s);


        //解析Map
        Gson gson = new Gson();
        Map<String, Integer> map = gson.fromJson("{\"one\": 111, \"two\": 22, \"three\": 3}", Map.class);
        System.out.println(map);


    }

    private void jsonToJavaWithDeserializer() {

        String json = "{\"imdbId\":\"tt0472043\",\"director\":\"Mel Gibson\",\"actors\":[{\"imdbId\":\"nm2199632\",\"dateOfBirth\":\"Sep 21, 1982, 12:00:00 AM\",\"filmography\":[\"Apocalypto\",\"Beatdown\",\"Wind Walkers\"]}]}";


        Gson gson = new GsonBuilder()
                .registerTypeAdapter(ActorGson.class, new ActorGsonDeserializer())
                .create();

        Movie movie = gson.fromJson(json, Movie.class);
        System.out.println(movie);

    }

    private void jsonToJava() {

        String json = "" +
                "{\n" +
                "   \"imdbId\":\"tt0472043\",\n" +
                "   \"director\":\"Mel Gibson\",\n" +
                "   \"actors\":[\n" +
                "      {\n" +
                "         \"imdbId\":\"nm2199632\",\n" +
                "         \"dateOfBirth\":\"Sep 21, 1982, 12:00:00 AM\",\n" +
                "         \"filmography\":[\n" +
                "            \"Apocalypto\",\n" +
                "            \"Beatdown\",\n" +
                "            \"Wind Walkers\"\n" +
                "         ]\n" +
                "      }\n" +
                "   ]\n" +
                "}";

        Movie movie = new Gson().fromJson(json, Movie.class);
        System.out.println(movie.toString());

    }

    private void basicType() {

        Gson gson = new Gson();

        System.out.println(gson.fromJson("1", int.class));
        System.out.println(gson.fromJson("1", Integer.class));
        System.out.println(gson.fromJson("1", Long.class));
        System.out.println(gson.fromJson("false", Boolean.class));
        System.out.println(gson.fromJson("\"abc\"", String.class));
    }


    private class DateGsonDeserializer implements JsonDeserializer<Date> {

        @Override
        public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return null;
        }
    }


    private class ActorGsonDeserializer implements JsonDeserializer<ActorGson> {

        private SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy, KK:mm:ss a", new DateFormatSymbols(Locale.US));


        @Override
        public ActorGson deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            System.out.println("~~deserialize~~");
            System.out.println("json is " + json);
            System.out.println("type is " + type);
            System.out.println("jsonDeserializationContext is " + jsonDeserializationContext);


            try {

                JsonObject jsonObject = json.getAsJsonObject();
                JsonElement jsonImdbId = jsonObject.get("imdbId");
                JsonElement jsonDateOfBirth = jsonObject.get("dateOfBirth");
                JsonArray jsonFilmography = jsonObject.getAsJsonArray("filmography");


                String imdbId = jsonImdbId.getAsString();

                Date dateOfBirth = sdf.parse(jsonDateOfBirth.getAsString());


                ArrayList<String> filmList = new ArrayList<String>();
                if (jsonFilmography != null) {
                    for (int i = 0; i < jsonFilmography.size(); i++) {
                        filmList.add(jsonFilmography.get(i).getAsString());
                    }
                }


                return new ActorGson(imdbId, dateOfBirth, filmList);

            } catch (ParseException e) {
                e.printStackTrace();
            }

            return null;
        }
    }


}

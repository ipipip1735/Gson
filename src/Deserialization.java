import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
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


//        deserialization.basicType();
        deserialization.arrayType();
//        deserialization.collectionType();
//        deserialization.dateType();
//        deserialization.jsonDeserializer();
//        deserialization.jsonToJava();
//        deserialization.jsonToJavaWithCustom();

//        deserialization.getZone();

    }

    private void jsonDeserializer() {

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

    private void getZone() {

        String json = "{\"id\": \"402881ee74d22f740174d3e77d4900c1\", \"createTime\": \"2020-10-27\", \"speed\": 5.0, \"flyTaskName\": \"0927-联调测试_街区01_网格2_2期\", \"flyHeight\": 100.0, \"lineOverlap\": 0.0, \"headingOverlap\": 0.8, \"rotatePitch\": 90.0, \"coordinate\": \"[[[114.32088898169646,30.57943507796134],[114.32614565154965,30.578803621520315],[114.32582582296264,30.576966657328242],[114.3199048937364,30.578237770943293],[114.32088898169646,30.57943507796134]]]\", \"wayPointList\": [{\"id\": \"402881ee74d22f740174d3e77d4a00c2\", \"order\": 1, \"latitude\": 30.57702304651146, \"longitude\": 114.3255631585092 }, {\"id\": \"402881ee74d22f740174d3e77d4a00c3\", \"order\": 2, \"latitude\": 30.578875819096854, \"longitude\": 114.32554463016572 }, {\"id\": \"402881ee74d22f740174d3e77d4a00c4\", \"order\": 3, \"latitude\": 30.578948016673394, \"longitude\": 114.32494360878181 }, {\"id\": \"402881ee74d22f740174d3e77d4a00c5\", \"order\": 4, \"latitude\": 30.57715219691913, \"longitude\": 114.32496156757799 }, {\"id\": \"402881ee74d22f740174d3e77d4a00c6\", \"order\": 5, \"latitude\": 30.5772813473268, \"longitude\": 114.32435997664678 }, {\"id\": \"402881ee74d22f740174d3e77d4a00c7\", \"order\": 6, \"latitude\": 30.579020214249933, \"longitude\": 114.3243425873979 }, {\"id\": \"402881ee74d22f740174d3e77d4a00c8\", \"order\": 7, \"latitude\": 30.579092411826473, \"longitude\": 114.32374156601398 }, {\"id\": \"402881ee74d22f740174d3e77d4a00c9\", \"order\": 8, \"latitude\": 30.577410497734473, \"longitude\": 114.32375838571555 }, {\"id\": \"402881ee74d22f740174d3e77d4a00ca\", \"order\": 9, \"latitude\": 30.57753964814215, \"longitude\": 114.32315679478434 }, {\"id\": \"402881ee74d22f740174d3e77d4a00cb\", \"order\": 10, \"latitude\": 30.579164609403016, \"longitude\": 114.32314054463006 }, {\"id\": \"402881ee74d22f740174d3e77d4a00cc\", \"order\": 11, \"latitude\": 30.579236806979555, \"longitude\": 114.32253952324612 }, {\"id\": \"402881ee74d22f740174d3e77d4a00cd\", \"order\": 12, \"latitude\": 30.577668798549816, \"longitude\": 114.32255520385311 }, {\"id\": \"402881ee74d22f740174d3e77d4a00ce\", \"order\": 13, \"latitude\": 30.57779794895749, \"longitude\": 114.32195361292189 }, {\"id\": \"402881ee74d22f740174d3e77d4a00cf\", \"order\": 14, \"latitude\": 30.579309004556094, \"longitude\": 114.3219385018622 }, {\"id\": \"402881ee74d22f740174d3e77d4a00d0\", \"order\": 15, \"latitude\": 30.579381202132637, \"longitude\": 114.3213374804783 }, {\"id\": \"402881ee74d22f740174d3e77d4a00d1\", \"order\": 16, \"latitude\": 30.57792709936516, \"longitude\": 114.32135202199069 }, {\"id\": \"402881ee74d22f740174d3e77d4a00d2\", \"order\": 17, \"latitude\": 30.57805624977283, \"longitude\": 114.32075043105948 }, {\"id\": \"402881ee74d22f740174d3e77d4a00d3\", \"order\": 18, \"latitude\": 30.57925195973024, \"longitude\": 114.3207384735613 }, {\"id\": \"402881ee74d22f740174d3e77d4a00d4\", \"order\": 19, \"latitude\": 30.57853037505251, \"longitude\": 114.32014539026454 }, {\"id\": \"402881ee74d22f740174d3e77d4a00d5\", \"order\": 20, \"latitude\": 30.5781854001805, \"longitude\": 114.32014884012825 }]}";

        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        Zone zone = gson.fromJson(json, Zone.class);

        System.out.println(zone);

    }

    private void jsonToJavaWithCustom() {

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



    private class WayPointListGsonDeserializer implements JsonDeserializer<WayPoint[]> {

        @Override
        public WayPoint[] deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {


            JsonArray jsonArray = json.getAsJsonArray();

            for (int i = 0; i < jsonArray.size(); i++) {

                System.out.println(jsonArray.get(i));

            }


//            JsonElement jsonId = jsonArray.get("id");
//
//            JsonElement jsonDateOfBirth = jsonArray.get("dateOfBirth");
//            JsonArray jsonFilmography = jsonArray.getAsJsonArray("filmography");


            WayPoint[] wayPointLists = new WayPoint[]{new WayPoint()};



            return wayPointLists;
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

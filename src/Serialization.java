import com.google.gson.*;
import entity.ActorGson;
import entity.Movie;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2020/11/6 10:28.
 */
public class Serialization {
    public static void main(String[] args) {


        Serialization gSonTrial = new Serialization();

//        gSonTrial.basicType();//基本类型
//        gSonTrial.arrayType();//数组
//        gSonTrial.collectionType();//容器


//        gSonTrial.javaToJson();
        gSonTrial.javaToJsonWithSerializer();

    }

    private void collectionType() {

        //List容器
//        Gson gson = new Gson();
//        List<String> strings = Arrays.asList("one", "two", "three");
//        System.out.println(gson.toJson(strings));


        //Set容器
        Gson gson = new Gson();
        Set<String> strings = new HashSet<>();
        strings.add("one");
        strings.add("two");
        strings.add("three");

        System.out.println(gson.toJson(strings));



        //Map容器
//        Gson gson = new Gson();
//        Map<String, Integer> map = new HashMap<>();
//        map.put("one", 111);
//        map.put("two", 222);
//        map.put("three", 333);
//
//        System.out.println(gson.toJson(map));





    }

    private void arrayType() {

        Gson gson = new Gson();
        int[] values = {1, 2, 4};
        System.out.println(gson.toJson(values));

    }

    private void javaToJsonWithSerializer() {

        try {

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            List<String> list = Arrays.asList("Apocalypto", "Beatdown", "Wind Walkers");
            Date date = sdf.parse("21-09-1982");
            ActorGson actorGson = new ActorGson("nm2199632", date, list);

            Movie movie = new Movie("nm2199632", "Mel Gibson", Arrays.asList(actorGson));

            Gson gson = new GsonBuilder()
//                    .setPrettyPrinting()
//                    .excludeFieldsWithoutExposeAnnotation()
//                    .serializeNulls()
                    .disableHtmlEscaping()
                    .registerTypeAdapter(ActorGson.class, new ActorGsonSerializer())
                    .create();


            String serializedMovie = gson.toJson(movie);
            System.out.println(serializedMovie);


        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    private void javaToJson() {

        try {

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            List<String> list = Arrays.asList("Apocalypto", "Beatdown", "Wind Walkers");
            Date date = sdf.parse("21-09-1982");
            ActorGson actorGson = new ActorGson("nm2199632", date, list);

            Movie movie = new Movie("tt0472043", "Mel Gibson", Arrays.asList(actorGson));
            String serializedMovie = new Gson().toJson(movie);


            System.out.println(serializedMovie);


        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void basicType() {

//        Gson gson = new Gson();
//        System.out.println(gson.toJson(1));
//        System.out.println(gson.toJson("abcd"));
//        System.out.println(gson.toJson(1L));
//        System.out.println(gson.toJson(1.0f));
//        System.out.println(gson.toJson(3.1415926d));

    }


    private class ActorGsonSerializer implements JsonSerializer<ActorGson> {
        private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        @Override
        public JsonElement serialize(ActorGson actor, Type type, JsonSerializationContext jsonSerializationContext) {
            System.out.println("~~serialize~~");
            System.out.println("actor is " + actor);
            System.out.println("type is " + type);
            System.out.println("jsonSerializationContext is " + jsonSerializationContext);


            JsonObject actorJsonObj = new JsonObject();

            actorJsonObj.addProperty("<strong>IMDB Code</strong>", actor.imdbId);

            actorJsonObj.addProperty("<strong>Date Of Birth</strong>",
                    actor.dateOfBirth != null ? sdf.format(actor.dateOfBirth) : null);

            actorJsonObj.addProperty("<strong>N° Film:</strong> ",
                    actor.filmography != null ? actor.filmography.size() : null);

            actorJsonObj.addProperty("filmography",
                    actor.filmography != null ? convertFilmography(actor.filmography) : null);

            return actorJsonObj;
        }

        private String convertFilmography(List<String> filmography) {
            return filmography.stream().collect(Collectors.joining("-"));
        }

    }

}

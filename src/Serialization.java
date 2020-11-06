import com.google.gson.*;
import com.google.gson.annotations.Expose;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2020/11/6 10:28.
 */
public class Serialization {
    public static void main(String[] args) {


        Serialization gSonTrial = new Serialization();

//        gSonTrial.basicType();


//        gSonTrial.javaToJson();
        gSonTrial.javaToJsonWithCustom();

    }

    private void javaToJsonWithCustom() {

        try {

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            List<String> list = Arrays.asList("Apocalypto", "Beatdown", "Wind Walkers");
            Date date = sdf.parse("21-09-1982");
            ActorGson actorGson = new ActorGson("nm2199632", date, list);

            Movie movie = new Movie("nm2199632", "Mel Gibson", Arrays.asList(actorGson));

            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .excludeFieldsWithoutExposeAnnotation()
                    .serializeNulls()
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
        Gson gson = new Gson();
        System.out.println(gson.toJson(1));
        System.out.println(gson.toJson("abcd"));
        System.out.println(gson.toJson(1L));
        int[] values = { 1 };
        System.out.println(gson.toJson(values));
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
                    actor.filmography  != null ? actor.filmography.size() : null);

            actorJsonObj.addProperty("filmography",
                    actor.filmography != null ? convertFilmography(actor.filmography) : null);

            return actorJsonObj;
        }

        private String convertFilmography(List<String> filmography) {
            return filmography.stream().collect(Collectors.joining("-"));
        }

    }

}

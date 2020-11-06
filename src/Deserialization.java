import com.google.gson.*;

import java.lang.reflect.Type;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Administrator on 2020/11/6 14:18.
 */
public class Deserialization {

    public static void main(String[] args) {

        Deserialization deserialization = new Deserialization();


//        deserialization.basicType();
//        deserialization.jsonToJava();
        deserialization.jsonToJavaWithCustom();

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


        String json = "{\"imdbId\":\"tt0472043\",\"director\":\"Mel Gibson\",\"actors\":[{\"imdbId\":\"nm2199632\",\"dateOfBirth\":\"Sep 21, 1982, 12:00:00 AM\",\"filmography\":[\"Apocalypto\",\"Beatdown\",\"Wind Walkers\"]}]}";

        Movie outputMovie = new Gson().fromJson(json, Movie.class);
        outputMovie.toString();


    }

    private void basicType() {

        Gson gson = new Gson();

        System.out.println(gson.fromJson("1", int.class));
        System.out.println(gson.fromJson("1", Integer.class));
        System.out.println(gson.fromJson("1", Long.class));
        System.out.println(gson.fromJson("false", Boolean.class));
        System.out.println(gson.fromJson("\"abc\"", String.class));
        System.out.println(gson.fromJson("[\"aa\"]", String.class));

        String[] anotherStr = gson.fromJson("[\"abc\", \"efg\"]", String[].class);
        Arrays.stream(anotherStr).forEach(System.out::println);

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

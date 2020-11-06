import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by Administrator on 2020/11/6 14:33.
 */
public class Movie {
    @Expose
    public String imdbId;

    @Expose
    public String director;

    @Expose
    public List<ActorGson> actors;

    public Movie(String imdbId, String director, List<ActorGson> actors) {
        this.imdbId = imdbId;
        this.director = director;
        this.actors = actors;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "imdbId='" + imdbId + '\'' +
                ", director='" + director + '\'' +
                ", actors=" + actors +
                '}';
    }
}

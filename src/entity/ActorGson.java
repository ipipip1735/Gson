package entity;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2020/11/6 14:32.
 */
public class ActorGson {

    public String imdbId;
    public Date dateOfBirth;
    public List<String> filmography;

    public ActorGson(String imdbId, Date dateOfBirth, List<String> filmography) {
        this.imdbId = imdbId;
        this.dateOfBirth = dateOfBirth;
        this.filmography = filmography;
    }

    @Override
    public String toString() {
        return "entity.ActorGson{" +
                "imdbId='" + imdbId + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", filmography=" + filmography +
                '}';
    }
}

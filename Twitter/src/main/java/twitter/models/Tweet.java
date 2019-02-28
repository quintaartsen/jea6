package twitter.models;


import lombok.Getter;
import lombok.Setter;

import javax.jws.soap.SOAPBinding;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
public class Tweet {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Getter
    @Setter
    @Column(length = 140, nullable = false)
    private String text;

    @Getter
    @Setter
    private List<String> trends = new ArrayList<>();

    @Getter
    @Setter
    @OneToMany
    private List<String> mentions = new ArrayList<>();

    @Getter
    @Setter
    @OneToMany
    private List<User> likes = new ArrayList<>();

    @Getter
    @Setter
    @OneToMany
    private List<User> reports = new ArrayList<>();

    @Getter
    @Setter
    @OneToOne
    private User owner;

    public Tweet(String message, User user) {
        Matcher trendsMatches = Pattern.compile("\\B#\\w\\w+").matcher(message);
        while (trendsMatches.find()) {
            trends.add(trendsMatches.group());
        }
        this.setTrends(trends);

        Matcher mentionMatches = Pattern.compile("\\B@\\w\\w+").matcher(message);
        while (mentionMatches.find()) {
            mentions.add(mentionMatches.group());
        }
        this.setMentions(mentions);

        this.text = message;
        this.owner = user;
    }

    public void addLike(User user)
    {
        if(!likes.contains(user))
        {
            likes.add(user);
        }
    }

    public void removeLike(User user)
    {
        if(likes.contains(user))
        {
            likes.remove(user);
        }
    }
}

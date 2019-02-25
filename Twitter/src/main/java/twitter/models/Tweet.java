package twitter.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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
    private List<String> trends;

    @Getter
    @Setter
    @OneToMany
    private List<String> mentions;

    @Getter
    @Setter
    @OneToMany
    private List<User> likes;

    @Getter
    @Setter
    @OneToMany
    private List<User> reports;

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
}

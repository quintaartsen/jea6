package twitter.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

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

    @Getter
    @Setter
    private Date createdAt;

    public Tweet()
    {

    }

    public Tweet(String message, User user)
    {
        this.text = message;
        this.owner = user;
    }

    public Tweet(UUID id, String text, Date createdAt, User owner, List<User> likes, List<User> reports) {
        this.id = id;
        this.text = text;
        this.createdAt = createdAt;
        this.owner = owner;
        this.likes = likes;
        this.reports = reports;
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

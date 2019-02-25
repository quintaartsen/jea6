package twitter.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class User {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Getter
    @Setter
    private Role role;

    @Getter
    @Setter
    private String userName;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String lastName;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    @Column(length = 160)
    private String biography;

    @Getter
    @Setter
    private String website;

    @Getter
    @Setter
    private String location;

    @Getter
    @Setter
    private Byte[] picture;

    @Getter
    @Setter
    @ManyToMany
    private List<User> followers;

    @Getter
    @Setter
    @ManyToMany
    private List<User> following;

    @Getter
    @Setter
    @OneToMany
    private List<Tweet> Tweets;

    public User(Role role, String email, String username, String password) {
        this.role = role;
        this.email = email;

        this.userName = username;
        this.password = password;

        this.location = "";
        this.website = "";
        this.biography = "";
    }

    public void addFollower(User follower) {
        followers.add(follower);
    }

    public void removeFollower(User follower) {
        followers.remove(follower);
    }

    public void addFollowing(User user) {
        following.add(user);
    }

    public void removeFollowing(User user) {
        following.remove(user);
    }
}

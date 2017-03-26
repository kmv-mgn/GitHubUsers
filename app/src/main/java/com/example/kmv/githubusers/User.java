package com.example.kmv.githubusers;

/**
 * Модель для данных с гитхаба
 */

public class User {
    private String login;
    private String name;
    private String avatar_url;
    private int followers;
    private int following;

    public String getLogin() {
        return login;
    }
    public String getName() {return name;}
    public int getFollowers() {return followers;}
    public int getFollowing() {return following;}
}

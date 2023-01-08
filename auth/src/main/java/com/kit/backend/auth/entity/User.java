package com.kit.backend.auth.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table (name = "users")
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private long id;
    @Column (name = "name")
    private String name;
    @Column (name = "avatar")
    private String avatar;
    @Column (name = "googleId")
    private String googleId;

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public User(long id, String name, String avatar, String googleId) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.googleId = googleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(name, user.name) && Objects.equals(avatar, user.avatar) && Objects.equals(googleId, user.googleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, avatar, googleId);
    }

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", avatar='" + avatar + '\'' +
            ", googleId='" + googleId + '\'' +
            '}';
    }
}

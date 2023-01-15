package com.kit.backend.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "review")
@Data
@AllArgsConstructor
public class UsersReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NonNull
    private int id;
    @Column(name = "user_id")
    @NonNull
    private long userId;
    @Column(name = "house_id")
    @NonNull
    private int houseId;
    @Column(name = "created_on")
    @NonNull
    private Date createdOn;
    @Column(name = "review_body")
    private String reviewBody;

    public UsersReview() {
    }


}

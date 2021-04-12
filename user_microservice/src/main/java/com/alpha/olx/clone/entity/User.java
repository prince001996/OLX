package com.alpha.olx.clone.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="USER_ID")
    private Long userId;

    @Column(name="USER_NAME", nullable = false, unique = true)
    private String userName;

    @Column(name="EMAIL", nullable = false, unique = true)
    private String userEmail;

    @Column(name="FIRST_NAME")
    private String firstName;

    @Column(name="LAST_NAME")
    private String lastName;

    @Column(name="PASSWORD")
    private String password;

    @Column(name="MOBILE", nullable = false,unique = true)
    private Long mobile;

    @Column(name="ROLE", nullable = false)
    private String role;


    private boolean isSeller;



    @Column(name = "PHOTO_LINK", unique = true)
    private String photoLink;

    @Column(name="GOOGLE_LINK", unique = true)
    private String googleLink;

    @Column(name="FACEBOOK_LINK", unique = true)
    private String facebookLink;

}

package com.QuantomSoft.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;


    @Column(unique = true)
    private String mobileNo;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

   // @Lob
   // @Column(name = "profile_picture",columnDefinition = "LONGBLOB")
    //@Basic(fetch = FetchType.LAZY)
    //@Nullable
//    private byte[] profilePicture;

    @OneToMany(mappedBy ="admin" ,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Form> form;

    @OneToMany(mappedBy ="admin" ,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Job> job;


    @OneToMany(mappedBy = "admin" ,cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonProperty("adminId")
    private List<News> newsList;

}

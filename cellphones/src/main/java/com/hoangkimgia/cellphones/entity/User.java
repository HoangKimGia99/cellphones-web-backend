package com.hoangkimgia.cellphones.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Blob;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    String id;
    @Size(min = 3,message = "Ten qua ngan")
    String name;
    LocalDate dob;
    String email;
    @Size(min=8,message = "password qua ngan")
    String password;
    String phone;
    String address;
    Boolean gender;
    @Lob
    Blob image;
    @CreationTimestamp
    LocalDate registerDate;
    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE,CascadeType.DETACH})
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"))
    private  Collection<Role> roles= new HashSet<>();
}

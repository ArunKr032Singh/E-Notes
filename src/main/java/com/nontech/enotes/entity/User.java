/**
 * Created By Arun Singh
 * Date:03-02-2025
 * Time:09:54
 * Project Name:Enotes-Api-Service
 */

package com.nontech.enotes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String mobNo;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Role> roles;

}

package com.demo.security.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "roles")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(unique = true, nullable = false)
    @EqualsAndHashCode.Include
    private String name;

    // mappedBy ở đây trỏ tới "roles" bên Users
    @ManyToMany(mappedBy = "roles")
    @ToString.Exclude        // tránh vòng lặp khi in log
    private Set<Users> users = new HashSet<>();
}

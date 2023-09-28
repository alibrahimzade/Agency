package com.example.agency.dao.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "services")
public class Address {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;

    String name;

    @OneToMany(mappedBy = "address",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    List<Project> projects;
}

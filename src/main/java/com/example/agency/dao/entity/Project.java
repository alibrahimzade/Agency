package com.example.agency.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "projects")
public class Project {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;

    String name;
    @ManyToOne
    @JoinColumn(name = "address_id")
    @JsonIgnore
    Address address;
}

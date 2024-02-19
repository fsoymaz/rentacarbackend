package com.tobeto.pair8.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tobeto.pair8.entities.abstracts.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cities")
public class City extends BaseEntity {

    @Column(name = "city_name")
    private String cityName;

    // Bir şehirin birçok ilçesi olabilir

    @OneToMany(mappedBy = "city")// cascade = CascadeType.ALL
    private List<District> districts;
/*
    @OneToMany(mappedBy = "city")
    @JsonIgnore
    private List<User> users;

     */
}
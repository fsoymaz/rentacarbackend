package com.tobeto.pair8.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tobeto.pair8.entities.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "districts")
public class District extends BaseEntity {

    @Column(name = "district_name")
    private String districtName;

    // Bir ilçenin bir şehri olabilir
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    /*
    @OneToMany(mappedBy = "district")
    @JsonIgnore
    private List<User> users;

     */
}
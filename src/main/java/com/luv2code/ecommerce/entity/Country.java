package com.luv2code.ecommerce.entity;

import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "country")
@Getter
@Setter

public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
   private Long id;
    @Column(name = "code")
   private  String code;
    @Column(name = "name")
    private  String name;

    @OneToMany(mappedBy = "country")
    @JsonIgnore
    private List<State> states;

}

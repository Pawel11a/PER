package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Objects;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    @Column(name="name", unique=true)
    private String name;

    public Country(String name) {
        this.name = name;
    }


    @OneToMany(mappedBy = "country", cascade = CascadeType.PERSIST)
    private Set<Customer> customers;

    @OneToMany(mappedBy = "country", cascade = CascadeType.PERSIST)
    private Set<Producer> producers;

    @OneToMany(mappedBy = "country", cascade = CascadeType.PERSIST)
    private Set<Shop> shops;

}

package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "guarantee_components")
public class GuaranteeComponents {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer guaranteeComponent;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product_id")
    private Product product;

    @ElementCollection
    @CollectionTable(
            name = "guaranties",
            joinColumns = @JoinColumn(name = "guarantee_components_id", referencedColumnName = "id")
    )
    @Column(name = "guarantee")
    @Enumerated(EnumType.STRING)
    private Set<EGuarantee> guaranties;

}

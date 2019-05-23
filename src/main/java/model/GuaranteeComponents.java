package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.enums.EGuarantee;
//import model.enums.EGuarantee;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "guarantee_components")
public class GuaranteeComponents {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;
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
    private List<EGuarantee> guaranties;

}

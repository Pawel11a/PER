package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.enums.EGuarantee;

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
    private Integer guarantee_Components;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product_id")
    private Product product;

//    @ElementCollection
//    @CollectionTable(
//            name = "guaranties",
//            joinColumns = @JoinColumn(name = "health_card_id", referencedColumnName = "id")
//    )
//    @Column(name = "guarantee")
//    private List<EGuarantee> guaranties;

}

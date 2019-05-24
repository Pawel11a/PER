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
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;
    private Integer age;
    private String name;
    private String surname;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.PERSIST)
    private Set<CustomerOrder> customerOrders;

    @ManyToOne(cascade={ CascadeType.PERSIST, CascadeType.MERGE}) //samo to CascadeType.PERSIST zapisuje do country tylko id dlaczego?
    @JoinColumn(name = "country_id")
    private Country country;

}

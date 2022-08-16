package com.example.JavaDevHomeWork8.manufacturers;

import com.example.JavaDevHomeWork8.products.Products;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Manufacturers {
    @Id
    @GeneratedValue
//    @GenericGenerator(name = "UUID",
//            strategy = "org.hibernate.id.UUIDGenerator",
//            parameters = {
//                    @org.hibernate.annotations.Parameter(
//                            name = "uuid_gen_strategy_class",
//                            value = "org.hibernate.id.uuidCustomVersionOneStrategy"
//                    )
//            })
//    @Column(name = "id", updatable = false,nullable = false)
    private UUID id;
    private String name;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "manufacturers_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Set<Products> products;
}

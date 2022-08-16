package com.example.JavaDevHomeWork8.products;

import com.example.JavaDevHomeWork8.manufacturers.Manufacturers;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Products {
    @Id
    @GeneratedValue
//    @GenericGenerator(name = "UUID",
//            strategy = "org.hibernate.id.UUIDGenerator",
//            parameters = {
//                @org.hibernate.annotations.Parameter(
//                        name = "uuid_gen_strategy_class",
//                        value = "org.hibernate.id.uuidCustomVersionOneStrategy"
//                )
//            })
//    @Column(name = "id", updatable = false,nullable = false)
    private UUID id;
    private String name;
    private Long price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "manufacturers_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Manufacturers manufacturer;
}

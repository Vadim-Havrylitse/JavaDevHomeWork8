package com.example.JavaDevHomeWork8.products.entity;

import com.example.JavaDevHomeWork8.manufacturers.entity.Manufacturers;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.StringJoiner;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Products {

    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    private UUID id;
    private String name;
    private Integer price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(name = "manufacturers_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Manufacturers manufacturer;

    @Override
    public String toString() {
        return new StringJoiner(", ", Products.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("price=" + price)
                .add("manufacturerName=" + manufacturer.getName())
                .toString();
    }

}

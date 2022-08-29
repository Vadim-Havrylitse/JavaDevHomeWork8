package com.example.JavaDevHomeWork8.manufacturers.entity;

import com.example.JavaDevHomeWork8.products.entity.Products;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Manufacturers {
    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    private UUID id;
    private String name;

    @OneToMany(fetch = FetchType.EAGER)
    @JsonManagedReference
    @JoinColumn(name = "manufacturers_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private List<Products> products;

    public boolean hasProducts(){
        return products != null;
    }
}

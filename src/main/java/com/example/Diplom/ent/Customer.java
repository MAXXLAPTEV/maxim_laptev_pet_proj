package com.example.Diplom.ent;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@Table(schema = "public", name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String surname;

    @Column(nullable = false, unique = true)
    private String email;

    // TODO: 17.03.2022 what is that? remove
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Book book;
}

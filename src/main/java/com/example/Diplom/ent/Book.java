package com.example.Diplom.ent;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(schema = "public", name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // TODO: 17.03.2022 make author as separated entity
    @Column(nullable = false)
    private String authorName;

    @Column(nullable = false)
    private String bookName;

    @Column(nullable = false)
    private int pages;

    @Column(nullable = false)
    private Float cost;

    @ManyToOne
    @JoinColumn(name = "busket_id")
    private Basket basket;

}
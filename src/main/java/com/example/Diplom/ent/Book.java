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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
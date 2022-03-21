package com.example.Diplom.ent;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(schema = "public", name = "buskets")
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
//
//    @OneToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "order_id")
//    private Order order;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "basket_id")
    private List<Book> bookList;

}




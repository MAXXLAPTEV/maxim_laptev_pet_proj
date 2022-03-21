package com.example.Diplom.dto.response.addbasket;

import com.example.Diplom.dto.response.addbook.BookView;
import com.example.Diplom.ent.Book;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class BasketView {
    private Long id;
    private List<BookView> books;

    public BasketView(Long id, List<Book> books) {
        this.id = id;
        this.books = books.stream().map(book -> new BookView(book.getId(), book.getBookName())).collect(Collectors.toList());
    }

    public BasketView(Long id) {

    }
}

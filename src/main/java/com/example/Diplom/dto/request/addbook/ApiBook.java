package com.example.Diplom.dto.request.addbook;


public class ApiBook {
    private String bookName;

    private int pages;

    private Float cost;

    private int dateOfPrinting;

    public String getBookName() {
        return bookName;
    }

    public int getPages() {
        return pages;
    }

    public Float getCost() {
        return cost;
    }

    public int getDateOfPrinting() {
        return dateOfPrinting;
    }
}

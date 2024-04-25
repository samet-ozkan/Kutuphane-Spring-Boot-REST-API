package com.sametozkan.kutuphane.config.googlebooks;

import lombok.Data;

import java.util.List;

@Data
public class BookResponse {

    private Integer totalItems;
    private List<Book> items;
}

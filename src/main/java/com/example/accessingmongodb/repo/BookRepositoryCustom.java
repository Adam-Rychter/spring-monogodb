package com.example.accessingmongodb.repo;

import com.example.accessingmongodb.entity.Book;


import java.util.List;

public interface BookRepositoryCustom {

    List<Book> query(BookRepositoryCustomImpl.DynamicQuery dynamicQuery);

}

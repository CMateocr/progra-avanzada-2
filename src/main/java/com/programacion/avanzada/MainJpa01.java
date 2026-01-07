package com.programacion.avanzada;

import com.programacion.avanzada.domain.dto.BookTitlePriceDto;
import com.programacion.avanzada.domain.entity.Author;
import com.programacion.avanzada.domain.entity.Book;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainJpa01 {
  public static void main(String[] args) {
    try(SeContainer container = SeContainerInitializer.newInstance().initialize()) {
      EntityManager em = container.select(EntityManager.class).get();

//      Book b1 = Book.builder().isbn("3").title("El diario de Ana Frank").version(1).price(BigDecimal.valueOf( 35 )).build();
//
//      em.getTransaction().begin();
//
//      em.persist( b1 );
//
//      em.getTransaction().commit();

      TypedQuery<Book> qry = em.createQuery("SELECT o FROM Book o WHERE o.price > :price", Book.class);

      qry.setParameter("price", 40);

      List<Book> books = qry.getResultList();

//      books.forEach(System.out::println);


        TypedQuery<Object[]> qry2 = em.createQuery("SELECT o.title, o.price FROM Book o WHERE o.price > :price", Object[].class).setParameter("price", 40);

        List<Object[]> names = qry2.getResultList();

//        System.out.println(names.stream().map(Arrays::toString).toList());

        // ! usando dto

        TypedQuery<BookTitlePriceDto> qry3 = em.createQuery("SELECT o.title, o.price FROM Book o WHERE o.price > :price", BookTitlePriceDto.class).setParameter("price", 40);

        List<BookTitlePriceDto> dtoList = qry3.getResultList();

//        System.out.println(dtoList);

        // ! constructor

        TypedQuery<BookTitlePriceDto> qry4 = em.createQuery("SELECT new com.programacion.avanzada.domain.dto.BookTitlePriceDto(o.title, o.price) FROM Book o WHERE o.price > :price", BookTitlePriceDto.class).setParameter("price", 40);

        List<BookTitlePriceDto> dtoList4 = qry4.getResultList();

//        System.out.println(dtoList4);

        // ! nombre, precio, vendido

        TypedQuery<Object[]> qry5 = em.createQuery("SELECT o.title, o.price, i.sold FROM Book o LEFT JOIN o.inventory i", Object[].class);

        List<String> listJoin = qry5.getResultStream().map(Arrays::toString).toList();

//        listJoin.forEach(System.out::println);

        em.getTransaction().begin();

        Author a = Author.builder().name("mateo").version(1).build();

        em.persist( a );

        em.getTransaction().commit();
    }

  }
}

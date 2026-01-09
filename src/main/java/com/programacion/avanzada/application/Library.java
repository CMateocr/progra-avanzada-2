package com.programacion.avanzada.application;

import com.programacion.avanzada.domain.entity.Author;
import com.programacion.avanzada.domain.entity.Book;
import com.programacion.avanzada.domain.entity.Inventory;
import com.programacion.avanzada.domain.entity.LineItem;
import com.programacion.avanzada.domain.repository.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import javax.sound.sampled.Line;
import java.util.List;

@ApplicationScoped
public class Library {

  private final IAuthorRepository authorRepo;
  private final IBookRepository bookRepo;
  private final IInventoryRepository inventoryRepo;
  private final ICustomerRepository customerRepo;
  private final IPurchaseOrderRepository orderRepo;
  private final ILineItemRepository lineItemRepo;

  @Inject
  public Library(IAuthorRepository authorRepo, IBookRepository bookRepo, IInventoryRepository inventoryRepo, ICustomerRepository customerRepo, IPurchaseOrderRepository orderRepo, ILineItemRepository lineItemRepo) {
    this.authorRepo = authorRepo;
    this.bookRepo = bookRepo;
    this.inventoryRepo = inventoryRepo;
    this.customerRepo = customerRepo;
    this.orderRepo = orderRepo;
    this.lineItemRepo = lineItemRepo;
  }

  public Book createEntireBook(Book book, List<LineItem> lineItems, List<Author> authors, Inventory inventory) {
    List<Author> authorsSaved = authors.stream().map(this.authorRepo::save).toList();

    book.setAuthors( authorsSaved );

    Book bookSaved = this.bookRepo.save( book );

    List<LineItem> lineItemsSaved = lineItems.stream().map( l -> {
      l.setBook(bookSaved);
      this.lineItemRepo.save( l );
      return l;
    } ).toList();

    inventory.setBook( book );

    Inventory inventorySaved = this.inventoryRepo.save( inventory );

    bookSaved.setAuthors( authorsSaved );

    bookSaved.setInventory( inventorySaved );

    bookSaved.setLineItem(lineItemsSaved);

    return bookSaved;
  }
}

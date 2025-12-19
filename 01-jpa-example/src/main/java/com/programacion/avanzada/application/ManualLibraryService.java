package com.programacion.avanzada.application;

import com.programacion.avanzada.domain.entity.Author;
import com.programacion.avanzada.domain.entity.Book;
import com.programacion.avanzada.domain.entity.Customer;
import com.programacion.avanzada.domain.entity.Inventory;
import com.programacion.avanzada.domain.entity.LineItem;
import com.programacion.avanzada.domain.entity.PurchaseOrder;
import com.programacion.avanzada.domain.entity.Status;
import com.programacion.avanzada.domain.repository.IAuthorRepository;
import com.programacion.avanzada.domain.repository.IBookRepository;
import com.programacion.avanzada.domain.repository.ICustomerRepository;
import com.programacion.avanzada.domain.repository.IInventoryRepository;
import com.programacion.avanzada.domain.repository.ILineItemRepository;
import com.programacion.avanzada.domain.repository.IPurchaseOrderRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ManualLibraryService {

  @Inject
  IAuthorRepository authorRepo;
  @Inject
  IBookRepository bookRepo;
  @Inject
  IInventoryRepository inventoryRepo;
  @Inject
  ICustomerRepository customerRepo;
  @Inject
  IPurchaseOrderRepository orderRepo;
  @Inject
  ILineItemRepository lineItemRepo;

  // CASO A: Crear Libro Completo (Author -> Book -> Inventory)
  public void createBookManual(String title, String authorName) {
    // 1. PRIMERO: Guardar Independientes (Author)
    // Necesitamos que el autor tenga ID antes de vincularlo.
    Author author = Author.builder().name(authorName).build();
    authorRepo.save(author);

    // 2. SEGUNDO: Guardar el Padre (Book)
    // Vinculamos el autor (que ya tiene ID) y guardamos el libro.
    Book book = Book.builder().isbn("999-MANUAL").title(title).build();
    book.getAuthors().add(author); // Vinculación en memoria

    bookRepo.save(book); // JPA guarda el libro y la relación en tabla intermedia

    // 3. TERCERO: Guardar el Hijo (Inventory)
    // El inventario necesita el libro YA guardado por el @MapsId
    Inventory inv = Inventory.builder()
            .book(book) // Asignamos el padre
            .sold(0).supplied(100)
            .build();

    inventoryRepo.save(inv);

    System.out.println("Todo guardado paso a paso.");
  }

  // CASO B: Crear Orden Completa (Customer -> Order -> LineItems)
  public void createOrderManual(Long customerId) {
    // 1. Validar Independiente
    Customer customer = customerRepo.findById(customerId).orElseThrow();

    // 2. Guardar Padre (Order)
    // Necesitamos el ID de la orden para dárselo a los items
    PurchaseOrder order = PurchaseOrder.builder()
            .customer(customer)
            .status(Status.PLACED)
            .build();

    orderRepo.save(order); // La orden obtiene su ID (ej: 50)

    // 3. Guardar Hijos (LineItems)
    // Ahora podemos crear los items apuntando a la orden ID 50
    Book libro = bookRepo.findById("999-MANUAL").orElseThrow();

    LineItem item1 = LineItem.builder()
            .idx(1L)
            .purchaseOrder(order) // Vinculamos al padre YA existente
            .book(libro)
            .quantity(2)
            .build();

    lineItemRepo.save(item1);
  }
}
package com.prog_avanzada;

import com.prog_avanzada.model.Book;
import com.prog_avanzada.model.PurchaseOrder;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainJpa02 {

    public static void main(String[] args) {
        try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("avanzada")) {
            EntityManager em = emf.createEntityManager();

            Book book1 = em.find(Book.class, "978-0307474728");

            System.out.println(book1.getAuthors());

            PurchaseOrder p1 = em.find(PurchaseOrder.class, 4);

            System.out.println(p1.getLineItems());

//      emf.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}

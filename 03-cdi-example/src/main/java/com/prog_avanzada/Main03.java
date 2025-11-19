package com.prog_avanzada;

import com.prog_avanzada.model.Book;
import io.helidon.config.Config;
import io.helidon.dbclient.DbClient;
import io.helidon.dbclient.DbRow;

import java.math.BigDecimal;
import java.util.stream.Stream;

public class Main03 {
  public static void main(String[] args) {

    Config config = Config.create();

    Config dbConfig = config.get("db");

    DbClient dbCliente = DbClient.builder()
            .config(dbConfig)
            .build();

    // ! INSERT
//    dbCliente.execute()
//            .createInsert("INSERT INTO books (isbn, title, price, version) VALUES (?, ?, ?, ?)")
//             .params("3", "libro 03", BigDecimal.valueOf(19.99), 1)
//            .execute();

    long rowsUpdated = dbCliente.execute()
            .createUpdate("UPDATE books SET title = ?, price = ?, version = ? WHERE isbn = ?")
            .params("libro 02", BigDecimal.valueOf(5.1), 2, "2")
            .execute();

    System.out.println(rowsUpdated);

    // ! GET ALL
    Stream<DbRow> rows = dbCliente.execute()
            .createQuery("SELECT * FROM books order by title")
            .execute();

    rows
            .map(row -> {
              Book book = new Book();

              book.setIsbn(row.column("isbn").get().toString());

              book.setTitle(row.column("title").get().toString());

              book.setPrice(row.column("price").as(BigDecimal.class).get());

              book.setVersion(row.column("version").as(Integer.class).get());

              return book;
            })
            .forEach(System.out::println);
  }
}
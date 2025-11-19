package com.prog_avanzada.services;

import com.prog_avanzada.model.Book;
import com.prog_avanzada.model.Customer;
import com.prog_avanzada.repository.ICustomerRepository;
import io.helidon.dbclient.DbClient;
import io.helidon.dbclient.DbRow;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@ApplicationScoped
public class CustomerRepository implements ICustomerRepository {

    private final DbClient dbClient;

    @Inject
    public CustomerRepository(DbClient dbClient) {
        this.dbClient = dbClient;
    }

    @Override
    public List<Customer> findAll() {
        Stream<DbRow> rows = this.dbClient.execute()
          .createQuery("SELECT * FROM customer")
          .execute();

        return rows.map(this::mapToCustomer).toList();
    }

    @Override
    public Optional<Customer> findById(Long id) {
      Stream<DbRow> rows = this.dbClient.execute().createQuery("SELECT * FROM customer WHERE id = ?")
        .params(id)
        .execute();

      return rows.map(this::mapToCustomer).findFirst();
//      return Optional.empty();
    }

    @Override
    public boolean save(Customer entity) {
      Long rowsAffected = this.dbClient.execute()
        .createInsert("INSERT INTO customer (name, email, version) VALUES (?, ?, ?)")
        .params(entity.getName(), entity.getEmail(), entity.getVersion())
        .execute();

      return rowsAffected > 0;
    }

    @Override
    public boolean update(Customer entity) {
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    private Customer mapToCustomer(DbRow row) {
        return Customer.builder()
                .email(row.column("email").get().toString())
                .id(row.column("id").as(Long.class).get())
                .name(row.column("name").get().toString())
                .version(row.column("version").as(Integer.class).get())
                .build();
    }
}

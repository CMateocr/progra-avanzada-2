package com.prog_avanzada.repository;

import com.prog_avanzada.model.Customer;
import io.helidon.dbclient.DbRow;

import java.util.Optional;

public interface ICustomerRepository extends RepositoryModel<Customer, Long> {
}

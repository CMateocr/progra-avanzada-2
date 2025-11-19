package com.prog_avanzada.config;

import io.helidon.config.Config;
import io.helidon.dbclient.DbClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class DBConnection {
  @Produces
  @ApplicationScoped
  public DbClient dbClient() {
    Config dbConfig = Config.create().get("db");

    return DbClient.builder()
            .config(dbConfig)
            .build();
  }
}
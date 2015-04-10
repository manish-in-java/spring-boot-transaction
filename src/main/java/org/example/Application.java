package org.example;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@SpringBootApplication
public class Application
{
  public static void main(String[] args)
  {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  @ConfigurationProperties(prefix = "datasource.admin")
  @Primary
  public DataSource adminDataSource()
  {
    return DataSourceBuilder.create().build();
  }

  @Bean
  @Primary
  public LocalContainerEntityManagerFactoryBean adminEntityManagerFactory(final EntityManagerFactoryBuilder builder)
  {
    return builder
        .dataSource(adminDataSource())
        .packages("org.example.domain.admin")
        .persistenceUnit("adminPersistenceUnit")
        .build();
  }

  @Bean
  @Primary
  public JpaTransactionManager adminTransactionManager(@Qualifier("adminEntityManagerFactory") final EntityManagerFactory factory)
  {
    return new JpaTransactionManager(factory);
  }

  @Bean
  @ConfigurationProperties(prefix = "datasource.local")
  public DataSource localDataSource()
  {
    return DataSourceBuilder.create().build();
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean localEntityManagerFactory(final EntityManagerFactoryBuilder builder)
  {
    return builder
        .dataSource(localDataSource())
        .packages("org.example.domain.local")
        .persistenceUnit("localPersistenceUnit")
        .build();
  }

  @Bean
  public JpaTransactionManager localTransactionManager(@Qualifier("localEntityManagerFactory") final EntityManagerFactory factory)
  {
    return new JpaTransactionManager(factory);
  }
}

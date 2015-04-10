package org.example;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "org.example.data.admin", entityManagerFactoryRef = "adminEntityManagerFactory", transactionManagerRef = "adminTransactionManager")
@EnableTransactionManagement
public class AdminConfiguration
{
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
}

package org.example;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "org.example.data.local", entityManagerFactoryRef = "localEntityManagerFactory", transactionManagerRef = "localTransactionManager")
@EnableTransactionManagement
public class LocalConfiguration
{
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

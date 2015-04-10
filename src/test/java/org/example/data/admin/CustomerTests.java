package org.example.data.admin;

import org.example.Application;
import org.example.domain.admin.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class CustomerTests
{
  @Autowired
  private CustomerRepository repository;

  @Test
  public void testCreate()
  {
    final Customer customer = new Customer();
    customer.setName("John Doe");

    Assert.notNull(repository.save(customer));
  }
}

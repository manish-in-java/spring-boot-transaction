package org.example.data.local;

import org.example.Application;
import org.example.domain.local.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ProductTests
{
  @Autowired
  private ProductRepository repository;

  @Test
  public void testCreate()
  {
    final Product product = new Product();
    product.setName("Apple iPad Air 2");

    Assert.notNull(repository.save(product));
  }
}

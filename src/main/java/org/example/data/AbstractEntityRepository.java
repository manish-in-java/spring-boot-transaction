package org.example.data;

import org.example.domain.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AbstractEntityRepository<T extends AbstractEntity> extends JpaRepository<T, Long>
{
}

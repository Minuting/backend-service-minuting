package net.huray.backend.minuting.repository;

import net.huray.backend.minuting.entity.SampleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

//@Repository
public interface SampleRepository extends JpaRepository<SampleEntity, Long>, QuerydslPredicateExecutor<SampleEntity> {

}

package net.huray.example.template.repository;

import net.huray.example.template.entity.SampleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

//@Repository
public interface SampleRepository extends JpaRepository<SampleEntity, Long>, QuerydslPredicateExecutor<SampleEntity> {

}

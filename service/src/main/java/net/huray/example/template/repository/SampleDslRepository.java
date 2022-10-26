package net.huray.example.template.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import net.huray.example.template.entity.SampleEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

import static net.huray.example.template.entity.QSampleEntity.sampleEntity;

@Repository
@RequiredArgsConstructor
public class SampleDslRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public List<SampleEntity> findAllByData(String data) {
        return jpaQueryFactory.selectFrom(sampleEntity)
                .where(dataEq(data))
                .fetch();
    }

    private BooleanExpression dataEq(String dataCon) {
        return dataCon != null ? sampleEntity.data.eq(dataCon) : null;
    }

}

package net.huray.backend.minuting.sample.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import net.huray.backend.minuting.entity.QSampleEntity;
import net.huray.backend.minuting.entity.SampleEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SampleDslRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public List<SampleEntity> findAllByData(String data) {
        return jpaQueryFactory.selectFrom(QSampleEntity.sampleEntity)
                .where(dataEq(data))
                .fetch();
    }

    private BooleanExpression dataEq(String dataCon) {
        return dataCon != null ? QSampleEntity.sampleEntity.data.eq(dataCon) : null;
    }

}

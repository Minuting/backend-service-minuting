package net.huray.backend.minuting.entity.common

import net.huray.backend.http.Constant
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@EntityListeners(AuditingEntityListener::class)
@MappedSuperclass
class BaseDateTimeEntity {

    @CreatedDate
    @Column(updatable = false)
    var createdAt: LocalDateTime = Constant.EMPTY_LOCAL_DATETIME

    @LastModifiedDate
    var updatedAt: LocalDateTime = Constant.EMPTY_LOCAL_DATETIME

}

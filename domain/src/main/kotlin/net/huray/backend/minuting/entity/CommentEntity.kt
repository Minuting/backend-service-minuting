package net.huray.backend.minuting.entity

import net.huray.backend.minuting.entity.common.BaseDateTimeEntity
import javax.persistence.*

@Entity(name = "comments")
class CommentEntity(
    contents: String,
    minutes: MinutesEntity
) : BaseDateTimeEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    var id: Long = 0L

    var contents: String = contents; protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "minutes_id")
    var minutes: MinutesEntity = minutes; protected set

    fun updateComment(contents: String) {
        this.contents = contents
    }

    fun updateMinutes(minutes: MinutesEntity) {
        this.minutes = minutes
    }

}
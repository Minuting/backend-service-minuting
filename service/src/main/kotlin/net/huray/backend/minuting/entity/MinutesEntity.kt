package net.huray.backend.minuting.entity

import net.huray.backend.minuting.entity.common.BaseDateTimeEntity
import javax.persistence.*

@Entity(name = "minutes")
class MinutesEntity(
    var title: String = "",
    var contents: String = ""
) : BaseDateTimeEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "minutes_id")
    var id: Long = 0L

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "space_id")
    lateinit var space: SpaceEntity

    @OneToMany(mappedBy = "minutes")
    var attendees: MutableList<MinutesAttendee> = mutableListOf()

    @OneToMany(mappedBy = "minutes")
    var comments: MutableList<CommentEntity> = mutableListOf()

    fun updateMinutes(title: String, contents: String) {
        this.title = title
        this.contents = contents
    }

    fun addComment(comment: CommentEntity) {
        this.comments.add(comment)
        comment.updateMinutes(this)
    }

}
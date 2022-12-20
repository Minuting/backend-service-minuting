package net.huray.backend.minuting.entity

import net.huray.backend.minuting.entity.common.BaseDateTimeEntity
import javax.persistence.*

@Entity(name = "minutes")
class MinutesEntity(
    title: String,
    contents: String,
//    space: SpaceEntity
) : BaseDateTimeEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L

    var title = title; protected set

    var contents = contents; protected set

    // TODO: 2022/12/21 - 스페이스 스펙 정확히 모르겠어서 일단 lateinit 으로 둠
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "space_id")
    lateinit var space: SpaceEntity// = space; protected set

    @OneToMany(mappedBy = "minutes")
    protected val mutableAttendeeList: MutableList<MinutesAttendee> = mutableListOf()
    val attendeeList: List<MinutesAttendee> get() = mutableAttendeeList.toList()

    fun addAttendee(attendee: MinutesAttendee) {
        mutableAttendeeList.add(attendee)
    }

    @OneToMany(mappedBy = "minutes")
    protected val mutableCommentList: MutableList<CommentEntity> = mutableListOf()
    val commentList: List<CommentEntity> get() = mutableCommentList.toList()

    fun addComment(comment: CommentEntity) {
        mutableCommentList.add(comment)
    }

    fun updateMinutes(title: String, contents: String) {
        this.title = title
        this.contents = contents
    }

}
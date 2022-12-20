package net.huray.backend.minuting.entity

import net.huray.backend.minuting.entity.common.BaseDateTimeEntity
import javax.persistence.*

@Entity(name = "templates")
class TemplateEntity(
    title: String,
    contents: String,
    writer: MemberEntity
) : BaseDateTimeEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "template_id")
    var id: Long = 0L

    var title: String = title; protected set

    var contents: String = contents; protected set

    /*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "member_id",
        nullable = false
    )

     */
    @Transient
    var writer: MemberEntity = writer; protected set

    fun updateTemplate(title: String, contents: String, writer: MemberEntity) {
        this.title = title
        this.contents = contents
        this.writer = writer
    }

}
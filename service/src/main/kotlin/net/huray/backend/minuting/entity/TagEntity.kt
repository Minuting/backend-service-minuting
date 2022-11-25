package net.huray.backend.minuting.entity

import net.huray.backend.minuting.enums.TagType
import javax.persistence.*

@Entity(name = "tags")
class TagEntity(
    id: Long = 0L
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = id

    var name: String = ""

    @Enumerated(EnumType.STRING)
    var type: TagType = TagType.MINUTES

    var color: String = ""

    var orderNum: Int = 0


    @OneToMany(
        mappedBy = "tag", fetch = FetchType.LAZY,
        cascade = [CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE],
        orphanRemoval = true
    )
    var spaceTags: MutableList<SpaceTagEntity> = mutableListOf()

}
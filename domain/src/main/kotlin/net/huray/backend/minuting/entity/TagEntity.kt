package net.huray.backend.minuting.entity

import net.huray.backend.minuting.enums.TagType
import javax.persistence.*

@Entity(name = "tags")
class TagEntity(
    name: String,
    type: TagType,
    color: String,
    orderNum: Int
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L

    var name: String = name; protected set

    @Enumerated(EnumType.STRING)
    var type: TagType = type; protected set

    var color: String = color; protected set

    var orderNum: Int = orderNum; protected set

    @OneToMany(
        mappedBy = "tag",
        cascade = [CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE],
        orphanRemoval = true
    )
    protected val mutableSpaceTagList: MutableList<SpaceTagEntity> = mutableListOf()
    val spaceTagList: List<SpaceTagEntity> get() = mutableSpaceTagList.toList()

    fun addAllSpaceTags(spaceTagList: MutableList<SpaceTagEntity>) {
        mutableSpaceTagList.addAll(spaceTagList)
    }

}
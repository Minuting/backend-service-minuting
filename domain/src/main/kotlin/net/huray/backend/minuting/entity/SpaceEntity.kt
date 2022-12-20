package net.huray.backend.minuting.entity

import net.huray.backend.minuting.entity.common.BaseDateTimeEntity
import javax.persistence.*

@Entity(name = "spaces")
class SpaceEntity(
    name: String,
    description: String,
    icon: String,
    isPublic: Boolean,
    owner: MemberEntity
) : BaseDateTimeEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L

    var name = name; protected set

    var description = description; protected set

    var icon = icon; protected set

    var isPublic = isPublic; protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", referencedColumnName = "uid")
    var owner: MemberEntity = owner; protected set

    @OneToMany(
        mappedBy = "space",
        cascade = [CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE],
        orphanRemoval = true
    )
    protected val mutablePermissionList: MutableList<PermissionEntity> = mutableListOf()
    val permissionList: List<PermissionEntity> get() = mutablePermissionList.toList()

    fun addAllPermissions(permissionList: List<PermissionEntity>) {
        this.mutablePermissionList.addAll(permissionList)
    }

    @OneToMany(
        mappedBy = "space",
        cascade = [CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE],
        orphanRemoval = true
    )
    protected val mutableSpaceTagList: MutableList<SpaceTagEntity> = mutableListOf()
    val spaceTagList: List<SpaceTagEntity> get() = mutableSpaceTagList.toList()

    fun addAllSpaceTags(spaceTagList: List<SpaceTagEntity>) {
        mutableSpaceTagList.addAll(spaceTagList)
    }

    fun updateSpace(name: String, description: String, icon: String, isPublic: Boolean) {
        this.name = name
        this.description = description
        this.icon = icon
        this.isPublic = isPublic
    }

}
package net.huray.backend.minuting.entity

import net.huray.backend.minuting.entity.common.BaseDateTimeEntity
import org.springframework.transaction.annotation.Transactional
import java.util.*
import javax.persistence.*

@Entity(name = "spaces")
class SpaceEntity(
    var name: String = "",
    var description: String = "",
    var icon: String = "",
    @Column(name = "owner_id")
    var ownerId: UUID = UUID.randomUUID(),
    var isPublic: Boolean = false
) : BaseDateTimeEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", referencedColumnName = "uid", insertable = false, updatable = false)
    var owner: MemberEntity? = null

    @OneToMany(
        mappedBy = "space",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE],
        orphanRemoval = true
    )
    var permissions: MutableList<PermissionEntity> = mutableListOf()
        set(value) {
            field.clear()
            field.addAll(value)
        }

    @OneToMany(mappedBy = "space", fetch = FetchType.LAZY)
    var spaceTags: MutableList<SpaceTagEntity> = mutableListOf()

    fun updateSpace(
        name: String,
        description: String,
        icon: String,
        isPublic: Boolean,
        permissions: MutableList<PermissionEntity>
    ) {
        this.name = name
        this.description = description
        this.icon = icon
        this.isPublic = isPublic
        this.permissions = permissions
    }

}
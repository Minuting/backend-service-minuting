package net.huray.backend.minuting.entity

import net.huray.backend.minuting.entity.common.BaseDateTimeEntity
import java.util.*
import javax.persistence.*

@Entity(name = "spaces")
class SpaceEntity (
        var name: String = "",
        var description: String = "",
        var icon: String = "",
        var isPublic: Boolean = false
):BaseDateTimeEntity(){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L

    @Column(name="owner_id", insertable = false, updatable = false)
    var ownerId: UUID = UUID.randomUUID();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", referencedColumnName = "uid")
    lateinit var owner: MemberEntity

    @OneToMany(mappedBy = "space", fetch = FetchType.LAZY)
    var permissions: MutableList<PermissionEntity> = mutableListOf()

    @OneToMany(mappedBy = "space", fetch = FetchType.LAZY)
    var spaceTags: MutableList<SpaceTagEntity> = mutableListOf()

}
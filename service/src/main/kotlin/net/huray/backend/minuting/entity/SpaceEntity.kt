package net.huray.backend.minuting.entity

import javax.persistence.*

@Entity(name = "spaces")
class SpaceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L

    var name: String = ""

    var description: String = ""

    var icon: String = ""

    @ManyToOne
    @JoinColumn(name = "owner_id")
    lateinit var owner: MemberEntity

    var isPublic: Boolean = false

    @OneToMany(mappedBy = "space", fetch = FetchType.LAZY)
    var permissions: MutableList<PermissionEntity> = mutableListOf()

    @OneToMany(mappedBy = "space", fetch = FetchType.LAZY)
    var spaceTags: MutableList<SpaceTagEntity> = mutableListOf()

}
package net.huray.backend.minuting.entity

import net.huray.backend.minuting.enums.PermissionType
import javax.persistence.*

@Entity(name = "permissions")
class PermissionEntity(
    type: PermissionType,
    member: MemberEntity,
    space: SpaceEntity
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L

    @Enumerated(EnumType.STRING)
    var type: PermissionType = type; protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "space_id")
    var space: SpaceEntity = space; protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    var member: MemberEntity = member; protected set

}
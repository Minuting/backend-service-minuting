package net.huray.backend.minuting.entity

import net.huray.backend.minuting.enums.PermissionType
import javax.persistence.*

@Entity(name = "permissions")
class PermissionEntity(
    member: MemberEntity,
    @Enumerated(EnumType.STRING)
    var type: PermissionType,
    space: SpaceEntity
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "space_id")
    var space: SpaceEntity = space

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    var member: MemberEntity = member

}
package net.huray.backend.minuting.entity

import net.huray.backend.minuting.enums.PermissionType
import java.util.UUID
import javax.persistence.*

@Entity(name = "permissions")
class PermissionEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L

    @Enumerated(EnumType.STRING)
    lateinit var type: PermissionType

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "space_id")
    lateinit var space: SpaceEntity

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    lateinit var member: MemberEntity

}
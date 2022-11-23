package net.huray.backend.minuting.entity

import net.huray.backend.minuting.enums.PermissionType
import net.huray.backend.minuting.service.SpaceService
import java.util.UUID
import javax.persistence.*

@Entity(name = "permissions")
class PermissionEntity(
    @Column(name = "member_id")
    var memberId: UUID,
    @Enumerated(EnumType.STRING)
    var type: PermissionType,
    @Column(name = "space_id")
    var spaceId: Long,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "space_id", insertable = false, updatable = false,)
    var space: SpaceEntity? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", insertable = false, updatable = false, nullable = false)
    lateinit var member: MemberEntity

}
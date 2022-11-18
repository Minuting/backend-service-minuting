package net.huray.backend.minuting.entity

import net.huray.backend.minuting.enums.PermissionType
import javax.persistence.*

@Entity(name = "permissions")
class PermissionEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L

    @Enumerated(EnumType.STRING)
    lateinit var type: PermissionType

    @ManyToOne
    @JoinColumn(name = "space_id")
    lateinit var space: SpaceEntity

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = true)
    lateinit var member: MemberEntity

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = true)
    lateinit var team: TeamEntity


}
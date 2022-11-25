package net.huray.backend.minuting.entity

import net.huray.backend.minuting.enums.MemberType
import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

@Entity(name = "members")
class MemberEntity(
        var name: String = ""
) {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    var uid: UUID = UUID.randomUUID()

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    lateinit var company: CompanyEntity

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", insertable = false, updatable = false)
    lateinit var team: TeamEntity

    @Enumerated(EnumType.STRING)
    var memberType: MemberType = MemberType.MEMBER

}
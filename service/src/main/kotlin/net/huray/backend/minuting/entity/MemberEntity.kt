package net.huray.backend.minuting.entity

import net.huray.backend.minuting.enums.MemberType
import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

@Entity(name = "members")
class MemberEntity() {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    var uid: UUID = UUID.randomUUID()

    lateinit var name: String

    @ManyToOne
    @JoinColumn(name = "company_id")
    lateinit var company: CompanyEntity

    @ManyToOne
    @JoinColumn(name = "team_id", insertable = false, updatable = false)
    lateinit var team: TeamEntity

    @Enumerated(EnumType.STRING)
    lateinit var memberType: MemberType

}
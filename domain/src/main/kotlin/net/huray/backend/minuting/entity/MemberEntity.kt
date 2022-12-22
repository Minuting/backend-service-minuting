package net.huray.backend.minuting.entity

import net.huray.backend.minuting.enums.MemberType
import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

@Entity(name = "members")
class MemberEntity(
    name: String = "",
    email: String = "",
    memberType: MemberType = MemberType.MEMBER,
//    company: CompanyEntity,
//    team: TeamEntity
) {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    var uid: UUID = UUID.randomUUID()

    var name = name; protected set

    var email = email; protected set

    @Enumerated(EnumType.STRING)
    var memberType = memberType; protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    lateinit var company: CompanyEntity// = company; protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", insertable = false, updatable = false)
    lateinit var team: TeamEntity// = team; protected set

    @OneToOne(mappedBy = "member")
    lateinit var account: AccountEntity// = account; protected set

    /*
    @OneToMany(
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL],
        mappedBy = "writer"
    )
    protected val mutableTemplateList: MutableList<TemplateEntity> = mutableListOf()
    val templateList: List<TemplateEntity> get() = mutableTemplateList.toList()

fun addTemplate(template: TemplateEntity) {
        mutableTemplateList.add(template)
    }
     */

}
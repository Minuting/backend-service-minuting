package net.huray.backend.minuting.entity

import javax.persistence.*

@Entity(name = "teams")
class TeamEntity(
    name: String,
    company: CompanyEntity
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L

    @Column(nullable = true)
    var pId: Long? = null

    var name = name; protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    var company: CompanyEntity = company; protected set

}
package net.huray.backend.minuting.entity

import javax.persistence.*

@Entity(name = "teams")
class TeamEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    lateinit var company: CompanyEntity

    var name: String = ""

    @Column(nullable = true)
    var pId: Long? = null

}
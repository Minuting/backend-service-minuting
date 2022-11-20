package net.huray.backend.minuting.entity

import javax.persistence.*

@Entity(name = "teams")
class TeamEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L

    @ManyToOne
    @JoinColumn(name = "company_id")
    lateinit var company: CompanyEntity

    lateinit var name: String

    var pId: Long = 0L

}
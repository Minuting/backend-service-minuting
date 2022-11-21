package net.huray.backend.minuting.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name = "companies")
class CompanyEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L

    var name: String = ""

    var ceo: String = ""

    var telNumber: String = ""

    var address: String = ""

}
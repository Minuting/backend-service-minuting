package net.huray.backend.minuting.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name = "company")
class CompanyEntity(
    name: String,
    ceo: String,
    telNumber: String,
    address: String
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L

    var name = name; protected set

    var ceo = ceo; protected set

    var telNumber = telNumber; protected set

    var address = address; protected set

}
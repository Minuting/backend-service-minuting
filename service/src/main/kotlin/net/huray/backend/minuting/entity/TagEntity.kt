package net.huray.backend.minuting.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name = "tags")
class TagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L

    var name: String = ""

    var type: String = ""

    var color: Long = 0L

    var orderNum: Int = 0

}
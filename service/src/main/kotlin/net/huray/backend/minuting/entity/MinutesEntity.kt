package net.huray.backend.minuting.entity

import net.huray.backend.minuting.entity.common.BaseDateTimeEntity
import javax.persistence.*

@Entity(name = "minutes")
class MinutesEntity(
    var title: String = "",
    var contents: String = ""
) : BaseDateTimeEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L

    @OneToMany(mappedBy = "minutes")
    var attendees: MutableList<MinutesAttendee> = mutableListOf()

    fun updateMinutes(title: String, contents: String) {
        this.title = title
        this.contents = contents
    }

}
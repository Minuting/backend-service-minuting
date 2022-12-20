package net.huray.backend.minuting.entity

import javax.persistence.*

@Entity(name = "minutes_attendee")
@IdClass(MinutesAttendeeId::class)
class MinutesAttendee {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "minutes_id")
    lateinit var minutes: MinutesEntity

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    lateinit var member: MemberEntity

}
package net.huray.backend.minuting.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "minutes_attendee")
@IdClass(MinutesAttendeeId.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MinutesAttendee {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "minutes_id")
    private MinutesEntity minutes;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

}

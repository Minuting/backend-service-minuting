package net.huray.backend.minuting.entity;

import java.io.Serializable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MinutesAttendeeId implements Serializable {
    private Minutes minutes;
    private Member member;
}
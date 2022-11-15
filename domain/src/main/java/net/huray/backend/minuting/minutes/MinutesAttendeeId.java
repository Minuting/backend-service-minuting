package net.huray.backend.minuting.minutes;

import java.io.Serializable;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.huray.backend.minuting.account.Member;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MinutesAttendeeId implements Serializable {
    private Minutes minutes;
    private Member member;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MinutesAttendeeId that = (MinutesAttendeeId) o;
        return Objects.equals(minutes, that.minutes) && Objects.equals(member, that.member);
    }

    @Override
    public int hashCode() {
        return Objects.hash(minutes, member);
    }
}
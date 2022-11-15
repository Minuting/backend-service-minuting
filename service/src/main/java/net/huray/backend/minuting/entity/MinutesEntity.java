package net.huray.backend.minuting.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.huray.backend.minuting.entity.common.BaseDateTimeEntity;

@Getter
@Entity(name = "minutes")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MinutesEntity extends BaseDateTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String title;

    private String contents;

    public MinutesEntity(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    @OneToMany(mappedBy = "minutes")
    private List<MinutesAttendee> attendees = new ArrayList<>();

}

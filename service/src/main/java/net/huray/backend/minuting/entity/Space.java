package net.huray.backend.minuting.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.huray.backend.minuting.entity.common.BaseDateTimeEntity;

@Getter
@Entity(name = "space")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Space extends BaseDateTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;


    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
}

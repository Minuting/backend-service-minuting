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
@Entity(name = "team_user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TeamMember extends BaseDateTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;


    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;



}

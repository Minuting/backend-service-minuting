package net.huray.backend.minuting.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.huray.backend.minuting.entity.common.BaseDateTimeEntity;


@Entity
@DiscriminatorValue("TeamPermission")
public class TeamPermission extends Permission{

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = true)
    private Team team;

}

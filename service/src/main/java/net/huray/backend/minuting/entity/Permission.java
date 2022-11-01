package net.huray.backend.minuting.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.huray.backend.minuting.entity.common.BaseDateTimeEntity;

@Getter
@Entity(name = "permission")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Permission extends BaseDateTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Enumerated
    private PermissionType type;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;


    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
}

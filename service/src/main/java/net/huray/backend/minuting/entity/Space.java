package net.huray.backend.minuting.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

    private String description;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Member owner;


    @OneToMany(mappedBy = "space")
    private List<TeamPermission> teamPermissions = new ArrayList<>();

    @OneToMany(mappedBy = "space")
    private List<MemberPermission> memberPermissions = new ArrayList<>();

}

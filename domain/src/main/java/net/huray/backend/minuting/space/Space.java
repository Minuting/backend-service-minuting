package net.huray.backend.minuting.space;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.huray.backend.minuting.account.Member;
import net.huray.backend.minuting.base.BaseDateTimeEntity;

@Getter
@Entity(name = "space")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Space extends BaseDateTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String icon;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Member owner;

    private Boolean isPublic;

    @OneToMany(mappedBy = "space", fetch = FetchType.LAZY)
    private List<Permission> permissions = new ArrayList<>();

    @OneToMany(mappedBy = "space", fetch = FetchType.LAZY)
    private List<SpaceTag> spaceTags = new ArrayList<>();
}

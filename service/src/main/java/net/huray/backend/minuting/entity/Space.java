package net.huray.backend.minuting.entity;

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

@Getter
@Entity(name = "space")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Space {

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

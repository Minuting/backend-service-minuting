package net.huray.backend.minuting.web.home.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import net.huray.backend.minuting.entity.Space;

@SuperBuilder
@Getter
@AllArgsConstructor
public class SpaceRes {

    private Long id;
    private String name;
    private String icon;
    private Boolean isPublic;

    public static SpaceRes from(Space space){
        return SpaceRes.builder()
            .id(space.getId())
            .icon(space.getIcon())
            .name(space.getName())
            .isPublic(space.getIsPublic())
            .build();
    }

}

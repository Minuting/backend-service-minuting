package net.huray.backend.minuting.web.home.data;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
import net.huray.backend.minuting.entity.Space;

@SuperBuilder
@Getter
public class PublicSpaceRes extends SpaceRes{

    private Boolean isJoined;

    public static PublicSpaceRes from(Space space, Boolean isJoined){
        return PublicSpaceRes.builder()
            .id(space.getId())
            .isPublic(space.getIsPublic())
            .icon(space.getIcon())
            .name(space.getName())
            .isJoined(isJoined)
            .build();
    }

}

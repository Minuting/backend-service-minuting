package net.huray.backend.minuting.web.home.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import net.huray.backend.minuting.account.Team;

@Builder
@Getter
@AllArgsConstructor
public class TeamRes {

    private Long id;
    private String name;
    public static TeamRes from(Team team){
        return TeamRes.builder()
            .id(team.getId())
            .name(team.getName())
            .build();
    }
}

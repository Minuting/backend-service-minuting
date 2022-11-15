package net.huray.backend.minuting.web.home.data;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import net.huray.backend.minuting.account.Member;
import net.huray.backend.minuting.account.MemberType;

@Builder
@Getter
@AllArgsConstructor
public class BaseInfoRes {

    private String name;
    private MemberType memberType;
    private CompanyRes company;
    private TeamRes team;
    private List<SpaceRes> spaceList = new ArrayList<>();

    public static BaseInfoRes of(Member member, List<SpaceRes> spaceList){
        return BaseInfoRes.builder()
            .name(member.getName())
            .memberType(member.getMemberType())
            .company(CompanyRes.from(member.getCompany()))
            .team(TeamRes.from(member.getTeam()))
            .spaceList(spaceList)
            .build();
    }

}

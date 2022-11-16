package net.huray.backend.minuting.service.space;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import net.huray.backend.minuting.entity.Member;
import net.huray.backend.minuting.service.user.UserComponent;
import net.huray.backend.minuting.entity.Permission;
import net.huray.backend.minuting.entity.Space;
import net.huray.backend.minuting.web.home.data.PublicSpaceRes;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpaceService {

    private final UserComponent userComponent;
    private final SpaceComponent spaceComponent;

    public List<PublicSpaceRes> listPublicSpace(UUID uid) {
        Member member = userComponent.get(uid);
        List<Space> joinedSpaceList = spaceComponent.listPermissionByMember(member).stream()
            .map(Permission::getSpace)
            .filter(Space::getIsPublic)
            .toList();

        return spaceComponent.listPublicSpace().stream()
            .map(e -> PublicSpaceRes.from(e, joinedSpaceList.stream()
                .anyMatch(joinedSpace -> joinedSpace.getId().equals(e.getId()))))
            .toList();
    }
}

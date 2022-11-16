package net.huray.backend.minuting.service.user;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import net.huray.backend.minuting.account.Member;
import net.huray.backend.minuting.service.space.SpaceComponent;
import net.huray.backend.minuting.web.home.data.BaseInfoRes;
import net.huray.backend.minuting.web.home.data.SpaceRes;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserComponent userComponent;
    private final SpaceComponent spaceComponent;

    public BaseInfoRes getBaseInfo(UUID uid) {
        Member member = userComponent.get(uid);
        return BaseInfoRes.of(member,
            spaceComponent.listPermissionByMember(member).stream()
            .map(e -> SpaceRes.from(e.getSpace()))
                .toList());
    }
}

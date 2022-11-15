package net.huray.backend.minuting.service.space;

import java.util.List;
import lombok.RequiredArgsConstructor;
import net.huray.backend.minuting.entity.Member;
import net.huray.backend.minuting.entity.Permission;
import net.huray.backend.minuting.repository.PermissionRepository;
import net.huray.backend.minuting.entity.Space;
import net.huray.backend.minuting.repository.SpaceRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SpaceComponent {

    private final SpaceRepository spaceRepository;
    private final PermissionRepository permissionRepository;

    public List<Permission> listPermissionByMember(Member member){
        return permissionRepository.findDistinctByMemberOrTeam(member, member.getTeam());
    }
    public List<Space> listPublicSpace(){
        return spaceRepository.findByIsPublicTrue();
    }
}

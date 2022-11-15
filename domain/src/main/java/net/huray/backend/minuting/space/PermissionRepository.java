package net.huray.backend.minuting.space;

import java.util.List;
import net.huray.backend.minuting.account.Member;
import net.huray.backend.minuting.account.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

    List<Permission> findDistinctByMemberOrTeam(Member member, Team team);
}

package net.huray.backend.minuting.repository;

import net.huray.backend.minuting.entity.Member;
import net.huray.backend.minuting.entity.Permission;
import net.huray.backend.minuting.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

    List<Permission> findDistinctByMemberOrTeam(Member member, Team team);
}

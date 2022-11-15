package net.huray.backend.minuting.repository;

import net.huray.backend.minuting.entity.Space;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpaceRepository extends JpaRepository<Space, Long> {

    List<Space> findByIsPublicTrue();
}

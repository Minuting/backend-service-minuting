package net.huray.backend.minuting.space;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpaceRepository extends JpaRepository<Space, Long> {

    List<Space> findByIsPublicTrue();
}

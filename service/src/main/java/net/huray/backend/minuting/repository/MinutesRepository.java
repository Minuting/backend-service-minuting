package net.huray.backend.minuting.repository;

import net.huray.backend.minuting.entity.MinutesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MinutesRepository extends JpaRepository<MinutesEntity, Long> {
}

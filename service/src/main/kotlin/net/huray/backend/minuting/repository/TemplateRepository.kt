package net.huray.backend.minuting.repository

import net.huray.backend.minuting.entity.TemplateEntity
import org.springframework.data.jpa.repository.JpaRepository

interface TemplateRepository : JpaRepository<TemplateEntity, Long>
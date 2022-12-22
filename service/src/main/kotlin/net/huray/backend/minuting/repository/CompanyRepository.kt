package net.huray.backend.minuting.repository

import net.huray.backend.minuting.entity.CompanyEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CompanyRepository : JpaRepository<CompanyEntity, Long> {
}
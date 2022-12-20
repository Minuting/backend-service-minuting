package net.huray.backend.minuting.controller

import net.huray.backend.http.res.ListResult
import net.huray.backend.minuting.contract.CompanyContract
import net.huray.backend.minuting.service.CompanyService
import org.springframework.web.bind.annotation.RestController

@RestController
class CompanyController(
    private val companyService: CompanyService
) : CompanyContract {

    override fun listTeam() = ListResult(companyService.listTeam())

}

package net.huray.backend.minuting.contract

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import net.huray.backend.http.res.ListResult
import net.huray.backend.minuting.contract.Endpoint.COMPANY
import net.huray.backend.minuting.dto.TagDto
import net.huray.backend.minuting.dto.TeamDto
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping

@Api(tags = ["회사 API"])
interface CompanyContract {
    @ApiOperation("팀 목록 API")
    @GetMapping(COMPANY, produces = [MediaType.APPLICATION_JSON_VALUE])
    fun listTeam(): ListResult<TeamDto.TeamSimpleDto>
}
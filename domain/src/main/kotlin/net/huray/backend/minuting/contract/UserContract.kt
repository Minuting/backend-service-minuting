package net.huray.backend.minuting.contract

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import net.huray.backend.http.res.ListResult
import net.huray.backend.minuting.dto.UserInfoDto
import net.huray.backend.minuting.contract.Endpoint.USER
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Api(tags = ["유저 API"])
interface UserContract {

    @ApiOperation("유저 이름 검색 Like")
    @GetMapping(USER)
    fun listByName(@RequestParam(name = "name", required = true) name: String): ListResult<UserInfoDto.UserInfoSimple>

}
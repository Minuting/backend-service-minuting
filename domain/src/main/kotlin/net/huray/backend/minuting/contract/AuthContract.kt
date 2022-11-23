package net.huray.backend.minuting.contract

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.GetMapping

@Api(tags = ["인증 API"])
interface AuthContract {

    @ApiOperation("인증 페이지로 이동")
    @GetMapping("${Endpoint.AUTH}/code")
    fun moveUserGoogleCode(): String

}
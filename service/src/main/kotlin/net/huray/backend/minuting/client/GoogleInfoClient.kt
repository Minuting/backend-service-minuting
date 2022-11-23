package net.huray.backend.minuting.client

import net.huray.backend.minuting.dto.GoogleDto.GoogleInfoResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader


@FeignClient(value = "googleInfoClient", url = "https://openidconnect.googleapis.com")
interface GoogleInfoClient {

    @GetMapping("/v1/userinfo")
    fun getInfo(@RequestHeader("Authorization") accessToken: String): GoogleInfoResponse

}
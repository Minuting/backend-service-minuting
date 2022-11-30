package net.huray.backend.minuting.client

import net.huray.backend.minuting.dto.GoogleDto.GoogleTokenRequest
import net.huray.backend.minuting.dto.GoogleDto.GoogleTokenResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(value = "googleAuthClient", url = "https://oauth2.googleapis.com")
interface GoogleAuthClient {

    @PostMapping("/token")
    fun getTokenByCode(@RequestBody request: GoogleTokenRequest): GoogleTokenResponse

}
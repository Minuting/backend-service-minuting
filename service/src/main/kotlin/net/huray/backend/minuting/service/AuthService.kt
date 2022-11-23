package net.huray.backend.minuting.service

import net.huray.backend.minuting.client.GoogleAuthClient
import net.huray.backend.minuting.client.GoogleInfoClient
import net.huray.backend.minuting.dto.GoogleDto.GoogleInfoResponse
import net.huray.backend.minuting.dto.GoogleDto.GoogleTokenRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.net.URLEncoder

@Service
class AuthService(
    @Value("\${oauth.google.client-id}") private val clientId: String,
    @Value("\${oauth.google.client-secret}") private val clientSecret: String,
    @Value("\${oauth.google.redirect-url}") private val redirectUrl: String,
    private val googleAuthClient: GoogleAuthClient,
    private val googleInfoClient: GoogleInfoClient
) {

    fun getUserGoogleCode(): String =
        "https://accounts.google.com/o/oauth2/v2/auth" +
                "?client_id=${clientId}" +
                "&scope=https://www.googleapis.com/auth/userinfo.email%20https://www.googleapis.com/auth/userinfo.profile" +
                "&response_type=code&access_type=offline" +
                "&redirect_uri=${URLEncoder.encode(redirectUrl, "UTF-8")}"

    fun getUserInfo(code: String): GoogleInfoResponse = // TODO 유저 Entity를 확정 후 쓰일 서비스 코드
        GoogleTokenRequest(code, clientId, clientSecret, redirectUrl, "authorization_code")
            .let { googleAuthClient.getTokenByCode(it) }
            .let { googleInfoClient.getInfo("Bearer ${it.accessToken}") }

}
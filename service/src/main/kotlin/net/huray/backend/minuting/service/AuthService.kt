package net.huray.backend.minuting.service

import net.huray.backend.http.exception.BaseException
import net.huray.backend.http.exception.code.ErrorCode
import net.huray.backend.http.exception.code.ErrorCode.INVALID_TOKEN
import net.huray.backend.minuting.client.GoogleAuthClient
import net.huray.backend.minuting.client.GoogleInfoClient
import net.huray.backend.minuting.dto.AuthDto.LoginRes
import net.huray.backend.minuting.dto.AuthDto.TokenRefreshReq
import net.huray.backend.minuting.dto.AuthDto.TokenRefreshRes
import net.huray.backend.minuting.dto.GoogleDto.GoogleTokenRequest
import net.huray.backend.minuting.entity.AccountEntity
import net.huray.backend.minuting.properties.GoogleProperties
import net.huray.backend.minuting.repository.AccountRepository
import net.huray.backend.minuting.support.JwtProvider
import org.springframework.stereotype.Service
import java.net.URLEncoder

@Service
class AuthService(
    private val googleAuthClient: GoogleAuthClient,
    private val googleInfoClient: GoogleInfoClient,
    private val accountRepository: AccountRepository,
    private val jwtProvider: JwtProvider,
    private val googleProperties: GoogleProperties
) {

    fun getUserGoogleCode(): String = with(googleProperties) {
        "https://accounts.google.com/o/oauth2/v2/auth" +
                "?client_id=${clientId}" +
                "&scope=https://www.googleapis.com/auth/userinfo.email%20https://www.googleapis.com/auth/userinfo.profile" +
                "&response_type=code&access_type=offline" +
                "&redirect_uri=${URLEncoder.encode(redirectUrl, "UTF-8")}"
    }

    fun login(code: String) = with(googleProperties) {
        GoogleTokenRequest(code, clientId, clientSecret, redirectUrl, "authorization_code")
            .let { googleAuthClient.getTokenByCode(it) }
            .run { googleInfoClient.getInfo("Bearer $accessToken") }
            .run { accountRepository.findByEmail(email) ?: accountRepository.save(AccountEntity(email, name)) }
            .run { LoginRes(jwtProvider.generateAccessToken(id), jwtProvider.generateRefreshToken(id)) }
    }

    fun tokenRefresh(req: TokenRefreshReq): TokenRefreshRes = with(jwtProvider) {
        req.run { getBody(refreshToken) }
            .takeIf { isRefresh(it) }
            ?.let { getId(it) }
            ?.takeIf { accountRepository.existsById(it) }
            ?.let { TokenRefreshRes(jwtProvider.generateAccessToken(it)) }
            ?: throw BaseException(INVALID_TOKEN)
    }

}
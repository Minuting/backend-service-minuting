package net.huray.backend.minuting.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

object GoogleDto {

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
    class GoogleTokenRequest(
        val code: String,
        val clientId: String,
        val clientSecret: String,
        val redirectUri: String,
        val grantType: String
    )

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
    class GoogleTokenResponse(
        val accessToken: String,
        val expiresIn: String,
        val tokenType: String,
        val scope: String,
        val refreshToken: String
    )

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
    class GoogleInfoResponse(
        val sub: String,
        val name: String,
        val givenName: String,
        val familyName: String,
        val picture: String,
        val email: String,
        val emailVerified: String,
        val locale: String
    )

}
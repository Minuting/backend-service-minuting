package net.huray.backend.minuting.support

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm.HS512
import net.huray.backend.http.exception.BaseException
import net.huray.backend.http.exception.code.ErrorCode.INVALID_TOKEN
import net.huray.backend.minuting.properties.JwtProperties
import net.huray.backend.minuting.support.JwtProvider.TokenType.ACCESS
import net.huray.backend.minuting.support.JwtProvider.TokenType.REFRESH
import org.springframework.stereotype.Component
import java.util.*


@Component
class JwtProvider(
    private val jwtProperties: JwtProperties
) {

    enum class TokenType { ACCESS, REFRESH }

    fun generateAccessToken(id: Long) = generateToken(id, ACCESS)
    fun generateRefreshToken(id: Long) = generateToken(id, REFRESH)

    private fun generateToken(id: Long, type: TokenType): String =
        Jwts.builder()
            .setExpiration(Date(System.currentTimeMillis() + jwtProperties.accessExp))
            .signWith(HS512, jwtProperties.secretKey)
            .setIssuedAt(Date())
            .setSubject(id.toString())
            .claim("type", type.name)
            .compact()

    fun getBody(token: String): Claims {
        return try {
            Jwts.parser().setSigningKey(jwtProperties.secretKey).parseClaimsJws(token).body
        } catch (e: Exception) {
            throw BaseException(INVALID_TOKEN, token)
        }
    }

    fun isAccess(body: Claims): Boolean = body.get("type", String::class.java) == ACCESS.name
    fun isRefresh(body: Claims): Boolean = body.get("type", String::class.java) == REFRESH.name

    fun getId(body: Claims): Long = body.subject.toLong()

}
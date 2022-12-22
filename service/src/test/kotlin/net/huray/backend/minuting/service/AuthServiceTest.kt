package net.huray.backend.minuting.service

import io.jsonwebtoken.impl.DefaultClaims
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import net.huray.backend.http.exception.BaseException
import net.huray.backend.http.exception.code.ErrorCode.INVALID_TOKEN
import net.huray.backend.minuting.client.GoogleAuthClient
import net.huray.backend.minuting.client.GoogleInfoClient
import net.huray.backend.minuting.dto.AuthDto.TokenRefreshReq
import net.huray.backend.minuting.dto.GoogleDto.GoogleInfoResponse
import net.huray.backend.minuting.dto.GoogleDto.GoogleTokenResponse
import net.huray.backend.minuting.entity.AccountEntity
import net.huray.backend.minuting.entity.MemberEntity
import net.huray.backend.minuting.properties.GoogleProperties
import net.huray.backend.minuting.repository.AccountRepository
import net.huray.backend.minuting.repository.MemberRepository
import net.huray.backend.minuting.support.JwtProvider

class AuthServiceTest : BehaviorSpec({

    val googleAuthClient: GoogleAuthClient = mockk()
    val googleInfoClient: GoogleInfoClient = mockk()
    val accountRepository: AccountRepository = mockk()
    val memberRepository: MemberRepository = mockk()
    val jwtProvider: JwtProvider = mockk()
    val googleProperties: GoogleProperties = mockk()

    val service = AuthService(
        googleAuthClient,
        googleInfoClient,
        accountRepository,
        memberRepository,
        jwtProvider,
        googleProperties
    )

    Given("GoogleProperties clientId is ASDF and redirectUrl is FDSA") {
        with(googleProperties) {
            every { clientId } returns "ASDF"
            every { redirectUrl } returns "FDSA"
        }

        When("Get google auth url") {
            val url = service.getUserGoogleCode()

            Then("Url is correct url") {
                url.shouldBe(
                    "https://accounts.google.com/o/oauth2/v2/auth" +
                            "?client_id=ASDF" +
                            "&scope=https://www.googleapis.com/auth/userinfo.email%20https://www.googleapis.com/auth/userinfo.profile" +
                            "&response_type=code&access_type=offline" +
                            "&redirect_uri=FDSA"
                )
            }
        }
    }

    Given("Correct google code") {
        with(googleProperties) {
            every { clientId } returns "clientId"
            every { clientSecret } returns "clientSecret"
            every { redirectUrl } returns "redirectUrl"
        }
        every { googleAuthClient.getTokenByCode(any()) } returns GoogleTokenResponse(
            "accessToken",
            "expiresIn",
            "tokenIn",
            "scope",
            "refreshToken"
        )
        every { googleInfoClient.getInfo("Bearer accessToken") } returns GoogleInfoResponse(
            "sub",
            "name",
            "givenName",
            "familyName",
            "picture",
            "email",
            "emailVerified",
            "locale"
        )
        every { accountRepository.findByEmail("email") } returns AccountEntity("email", MemberEntity("name"))
        with(jwtProvider) {
            every { generateAccessToken(0L) } returns "accessToken"
            every { generateRefreshToken(0L) } returns "refreshToken"
        }

        When("Login with code") {
            val result = service.login("code")

            Then("Return token") {
                with(result) {
                    accessToken.shouldBe("accessToken")
                    refreshToken.shouldBe("refreshToken")
                }
            }
        }
    }

    Given("Token type is refresh") {
        val request = TokenRefreshReq("refreshToken")
        with(jwtProvider) {
            val body = DefaultClaims()
            every { getBody("refreshToken") } returns body
            every { isRefresh(body) } returns true
            every { getId(body) } returns 0L
            every { generateAccessToken(0L) } returns "accessToken"
        }

        When("Token refresh with account") {
            every { accountRepository.existsById(0L) } returns true
            val response = service.tokenRefresh(request)

            Then("Token type is access") {
                response.accessToken.shouldBe("accessToken")
            }
        }

        When("Token refresh without account") {
            every { accountRepository.existsById(0L) } returns false
            val exception = shouldThrow<BaseException> {
                service.tokenRefresh(request)
            }

            Then("Throw BaseException(code = INVALID_TOKEN)") {
                with(exception) {
                    code.shouldBe(INVALID_TOKEN.code)
                    reason.shouldBe(INVALID_TOKEN.reason)
                }
            }
        }
    }

    Given("Token type is access") {
        val request = TokenRefreshReq("refreshToken")
        with(jwtProvider) {
            val body = DefaultClaims()
            every { getBody("refreshToken") } returns body
            every { isRefresh(body) } returns false
        }

        When("Token refresh") {
            val exception = shouldThrow<BaseException> {
                service.tokenRefresh(request)
            }

            Then("Throw BaseException(code = INVALID_TOKEN)") {
                with(exception) {
                    code.shouldBe(INVALID_TOKEN.code)
                    reason.shouldBe(INVALID_TOKEN.reason)
                }
            }
        }
    }

})
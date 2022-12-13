package net.huray.backend.minuting.integration

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import io.mockk.every
import net.huray.backend.http.exception.BaseException
import net.huray.backend.minuting.client.GoogleAuthClient
import net.huray.backend.minuting.client.GoogleInfoClient
import net.huray.backend.minuting.contract.Endpoint.AUTH
import net.huray.backend.minuting.dto.AuthDto.LoginRes
import net.huray.backend.minuting.dto.AuthDto.TokenRefreshReq
import net.huray.backend.minuting.dto.AuthDto.TokenRefreshRes
import net.huray.backend.minuting.dto.GoogleDto.GoogleInfoResponse
import net.huray.backend.minuting.dto.GoogleDto.GoogleTokenResponse
import net.huray.backend.minuting.entity.AccountEntity
import net.huray.backend.minuting.entity.MemberEntity
import net.huray.backend.minuting.repository.AccountRepository
import net.huray.backend.minuting.repository.MemberRepository
import net.huray.backend.minuting.support.JwtProvider
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class AuthApiTest(
    val accountRepository: AccountRepository,
    val memberRepository: MemberRepository,
    val jwtProvider: JwtProvider,
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper,
    @MockkBean val googleAuthClient: GoogleAuthClient,
    @MockkBean val googleInfoClient: GoogleInfoClient
): BehaviorSpec({

    afterContainer {
        accountRepository.deleteAll()
    }

    Given("Google clientId and redirectUrl is correct") {
        When("Request move user google code api") {
            val response = mockMvc.perform(get("$AUTH/code"))
                .andDo(print())

            Then("Return 302 status") {
                response.andExpect(status().`is`(302))
            }

            Then("Redirect correct url") {
                response.andExpect(redirectedUrl("https://accounts.google.com/o/oauth2/v2/auth" +
                        "?client_id=fake-client-id" +
                        "&scope=https://www.googleapis.com/auth/userinfo.email%20https://www.googleapis.com/auth/userinfo.profile" +
                        "&response_type=code" +
                        "&access_type=offline" +
                        "&redirect_uri=http%3A%2F%2Fwww.fake.com"))
            }
        }
    }

    Given("Google code is correct") {
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

        When("Request login api") {
            val response = mockMvc.perform(post("$AUTH/login")
                .param("code", "googleCode"))
                .andDo(print())

            Then("Return 200 status") {
                response.andExpect(status().`is`(200))
            }

            Then("Create account") {
                accountRepository.findByEmail("email")!!.memberEntity.name.shouldBe("name")
            }

            Then("Correct token") {
                val account = accountRepository.findByEmail("email")!!

                val loginRes = objectMapper.readValue(response.andReturn().response.contentAsString, LoginRes::class.java)
                with(jwtProvider) {
                    getBody(loginRes.accessToken).also {
                        getId(it).shouldBe(account.id)
                        isAccess(it).shouldBe(true)
                    }
                    getBody(loginRes.refreshToken).also {
                        getId(it).shouldBe(account.id)
                        isRefresh(it).shouldBe(true)
                    }
                }
            }
        }

    }

    Given("Google code is invalid and api return 401 status") {
        every { googleAuthClient.getTokenByCode(any()) } throws BaseException(401, "test")

        When("Request login api") {
            val response = mockMvc.perform(post("$AUTH/login")
                .param("code", "googleCode"))
                .andDo(print())

            Then("Return 401 status") {
                response.andExpect(status().`is`(401))
            }
        }
    }

    Given("Account is exists") {
        val member = memberRepository.save(MemberEntity("name"))
        val account = accountRepository.save(AccountEntity("email", member))

        When("Request token refresh api with refresh token") {
            val content = objectMapper.writeValueAsString(TokenRefreshReq(jwtProvider.generateRefreshToken(account.id)))

            val response = mockMvc.perform(post("$AUTH/token-refresh")
                .contentType(APPLICATION_JSON)
                .content(content))
                .andDo(print())

            Then("Return 200 status") {
                response.andExpect(status().`is`(200))
            }

            Then("Correct token") {
                val loginRes = objectMapper.readValue(response.andReturn().response.contentAsString, TokenRefreshRes::class.java)
                with(jwtProvider) {
                    getBody(loginRes.accessToken).also {
                        getId(it).shouldBe(account.id)
                        isAccess(it).shouldBe(true)
                    }
                }
            }
        }

        When("Request token refresh api with access token") {
            val content = objectMapper.writeValueAsString(TokenRefreshReq(jwtProvider.generateRefreshToken(account.id)))

            val response = mockMvc.perform(post("$AUTH/token-refresh")
                .contentType(APPLICATION_JSON)
                .content(content))
                .andDo(print())

            Then("Return 401 status") {
                response.andExpect(status().`is`(401))
            }
        }
    }

    Given("Account is not exists") {
        When("Request token refresh api with refresh token") {
            val content = objectMapper.writeValueAsString(TokenRefreshReq(jwtProvider.generateRefreshToken(-1L)))

            val response = mockMvc.perform(
                post("$AUTH/token-refresh")
                    .contentType(APPLICATION_JSON)
                    .content(content)
            )
                .andDo(print())

            Then("Return 401 status") {
                response.andExpect(status().`is`(401))
            }
        }
    }

}) {
    override fun extensions() = listOf(SpringExtension)
}
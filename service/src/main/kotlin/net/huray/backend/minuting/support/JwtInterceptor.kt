package net.huray.backend.minuting.support

import net.huray.backend.http.exception.BaseException
import net.huray.backend.http.exception.code.ErrorCode.INVALID_TOKEN
import net.huray.backend.minuting.repository.AccountRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpMethod
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtInterceptor(
    private val authenticationFacade: AuthenticationFacade,
    private val accountRepository: AccountRepository,
    private val jwtProvider: JwtProvider
) : HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if (!HttpMethod.OPTIONS.matches(request.method))
            request.getHeader("Authorization")
                ?.takeIf { it.startsWith("Bearer ") }
                ?.let { it.substring(7) }
                ?.let { jwtProvider.getBody(it) }
                ?.takeIf { jwtProvider.isAccess(it) }
                ?.let { jwtProvider.getId(it) }
                ?.let { accountRepository.findByIdOrNull(it) }
                ?.apply { authenticationFacade.setInfo(id, email, name) }
                ?: throw BaseException(INVALID_TOKEN)
        return super.preHandle(request, response, handler)
    }
}
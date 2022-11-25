package net.huray.backend.http.exception

/**
 * 요청을 수행할 권한이 없는 경우
 */
class ForbiddenException(pair: Pair<String, String>, vararg args: Any?) :
    BaseException(403, pair.first, pair.second, args)
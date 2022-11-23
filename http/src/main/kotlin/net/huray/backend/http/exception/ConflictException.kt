package net.huray.backend.http.exception

/**
 * 데이터에 이상이 있거나 중복되는 경우
 */
class ConflictException(pair: Pair<String, String>, vararg args: Any?) :
    BaseException(409, pair.first, pair.second, args)
package net.huray.backend.http.exception

/**
 * 요청한 데이터가 없는 경우.
 * 권한이 없어서 보여줄 수 없을 때에도 해당 데이터 존재 자체를 숨겨야 한다면 NotFound로 응답한다.
 */
class ForbiddenException(pair: Pair<String, String>, vararg args: Any?) :
    BaseException(403, pair.first, pair.second, args)
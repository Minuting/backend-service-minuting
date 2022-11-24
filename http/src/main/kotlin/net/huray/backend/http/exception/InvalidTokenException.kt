package net.huray.backend.http.exception

class InvalidTokenException(pair: Pair<String, String>, vararg args: Any?) :
        BaseException(401, pair.first, pair.second, args)
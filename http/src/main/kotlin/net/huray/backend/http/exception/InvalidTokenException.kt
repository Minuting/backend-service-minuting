package net.huray.backend.http.exception

class InvalidTokenException(pair: Pair<String, String>) :
        BaseException(401, pair.first, pair.second, null)
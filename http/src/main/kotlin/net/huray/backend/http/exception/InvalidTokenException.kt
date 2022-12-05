package net.huray.backend.http.exception

class InvalidTokenException(reason: String) :
        BaseException(401, reason)
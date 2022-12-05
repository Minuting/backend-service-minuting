package net.huray.backend.http.exception

class ForbiddenException(reason: String) :
    BaseException(403, reason)
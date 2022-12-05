package net.huray.backend.http.exception

class NotFoundException(reason: String) :
    BaseException(404, reason)
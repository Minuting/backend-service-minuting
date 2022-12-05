package net.huray.backend.http.exception

class ConflictException(reason: String) :
    BaseException(409, reason)
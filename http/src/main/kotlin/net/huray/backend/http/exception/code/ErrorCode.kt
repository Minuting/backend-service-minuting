package net.huray.backend.http.exception.code

enum class ErrorCode(
    val code: Int,
    val reason: String,
    val detail: String
) {
    INVALID_TOKEN(401, "Invalid Token", "(token: {VALUE})"),
    MINUTES_NOT_FOUND(404, "Minutes Not Found", "(id: {VALUE})"),
    SPACE_FORBIDDEN(403, "Space Forbidden", "(id: {VALUE}"),
    TEMPLATE_NOT_FOUND(404, "Template Not Found", "(id: {VALUE}}"),
    SPACE_NOT_FOUND(404, "Space Not Found", "(id: {VALUE})"),
    TAG_NOT_FOUND(404, "Tag Not Found", "(id: {VALUE})"),
    MEMBER_NOT_FOUND(404, "Member Not Found", "(id: {VALUE})")
}
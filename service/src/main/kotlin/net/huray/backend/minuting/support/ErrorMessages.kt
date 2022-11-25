package net.huray.backend.minuting.support

object ErrorMessages {
    val MINUTES_NOT_FOUND = "minutes.notFount" to "Minutes({0}) not found."

    val COMMENT_NOT_FOUND = "comment.notFound" to "Comment({0}) not found."

    val SPACE_NOT_FOUND = "space.notFound" to "Space({0}) not found."
    val SPACE_FORBIDDEN = "space.forbidden" to "Space({0}) forbidden."
    val INVALID_TOKEN = "json.web.token" to "Invalid Token({0})"
    val SPACE_ALREADY_JOINED = "space.conflict" to "Space({0}) already joined."
    
    val MEMBER_NOT_FOUND = "member.notFound" to "Member({0}) not found."

    val TAG_NOT_FOUND = "tag.notFound" to "Tag({0}) not found."
    
    val TEMPLATE_NOT_FOUND = "template.notFound" to "Template({0}) not found."
}
package net.huray.backend.minuting.support

object ErrorMessages {
    val MINUTES_NOT_FOUND = "minutes.notFount" to "Minutes({0}) not found."

    val COMMENT_NOT_FOUND = "comment.notFound" to "Comment({0}) not found."



    val SPACE_NOT_FOUND = "space.notFound" to "Space({0}) not found."
    val SPACE_FORBIDDEN = "space.forbidden" to "Space({0}) forbidden."
    val SPACE_ALREADY_JOINED = "space.conflict" to "Space({0}) already joined."
}
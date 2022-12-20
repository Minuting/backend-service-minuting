package net.huray.backend.minuting.contract

object Endpoint {
    const val AUTH = "/auth"
    const val USER = "/user"
    const val SPACES = "/spaces"
    const val MINUTES = "/minutes"
    const val MINUTES_COMMENTS = "${MINUTES}/{minutesId}/comments"
    const val TAGS = "/tags"
    const val COMPANY = "/company"
    const val TEMPLATE = "/templates"
}
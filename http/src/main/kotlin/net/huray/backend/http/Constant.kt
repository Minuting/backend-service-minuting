package net.huray.backend.http

import java.time.*
import java.util.*

object Constant {
    const val HEADER_TIMEZONE = "X-Huray-TimeZone"

    val EMPTY_DATE = Date(0)
    val EMPTY_SQL_DATE = java.sql.Date(0)
    val EMPTY_SQL_TIME = java.sql.Time(0)
    val EMPTY_SQL_TIMESTAMP = java.sql.Timestamp(1)
    val EMPTY_LOCAL_DATE = LocalDate.of(1970, 1, 1)!!
    val EMPTY_LOCAL_TIME = LocalTime.of(0, 0)!!

    /**
     * MySQL timestamp range is start from '1970-01-01 00:00:01'
     * see [MySQL Manual](https://dev.mysql.com/doc/refman/5.7/en/datetime.html)
     */
    val EMPTY_LOCAL_DATETIME = LocalDateTime.of(1970, 1, 1, 0, 0, 1)!!
    val EMPTY_ZONED_DATETIME = ZonedDateTime.of(EMPTY_LOCAL_DATETIME, ZoneId.systemDefault())!!
}
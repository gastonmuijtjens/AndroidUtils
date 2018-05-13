package com.gastonmuijtjens.androidutils.extensions

import java.text.SimpleDateFormat
import java.util.*

/**
 * Convenience extension methods to make the crappy Calendar API less crappy
 *
 * @author Gaston Muijtjens
 * @since 26-03-18
 */

var Calendar.milliSecond
    get() = get(Calendar.MILLISECOND)
    set(value) = set(Calendar.MILLISECOND, value)

var Calendar.second
    get() = get(Calendar.SECOND)
    set(value) = set(Calendar.SECOND, value)

var Calendar.minute
    get() = get(Calendar.MINUTE)
    set(value) = set(Calendar.MINUTE, value)

var Calendar.hour
    get() = get(Calendar.HOUR_OF_DAY)
    set(value) = set(Calendar.HOUR_OF_DAY, value)

var Calendar.day
    get() = get(Calendar.DAY_OF_MONTH)
    set(value) = set(Calendar.DAY_OF_MONTH, value)

var Calendar.month
    get() = get(Calendar.MONTH)
    set(value) = set(Calendar.MONTH, value)

var Calendar.year
    get() = get(Calendar.YEAR)
    set(value) = set(Calendar.YEAR, value)

fun Calendar.addMilliSeconds(milliSeconds: Int) {
    add(Calendar.MILLISECOND, milliSeconds)
}

fun Calendar.plusMilliSeconds(milliSeconds: Int): Calendar {
    val calendar = duplicate()
    calendar.addMilliSeconds(milliSeconds)
    return calendar
}

fun Calendar.addSeconds(seconds: Int) {
    add(Calendar.SECOND, seconds)
}

fun Calendar.plusSeconds(seconds: Int): Calendar {
    val calendar = duplicate()
    calendar.addSeconds(seconds)
    return calendar
}

fun Calendar.addMinutes(minutes: Int) {
    add(Calendar.MINUTE, minutes)
}

fun Calendar.plusMinutes(minutes: Int): Calendar {
    val calendar = duplicate()
    calendar.addMinutes(minutes)
    return calendar
}

fun Calendar.addHours(hours: Int) {
    add(Calendar.HOUR, hours)
}

fun Calendar.plusHours(hours: Int): Calendar {
    val calendar = duplicate()
    calendar.addHours(hours)
    return calendar
}

fun Calendar.addDays(days: Int) {
    add(Calendar.DAY_OF_MONTH, days)
}

fun Calendar.plusDays(days: Int): Calendar {
    val calendar = duplicate()
    calendar.addDays(days)
    return calendar
}

fun Calendar.addWeeks(weeks: Int) {
    add(Calendar.WEEK_OF_YEAR, weeks)
}

fun Calendar.plusWeeks(weeks: Int): Calendar {
    val calendar = duplicate()
    calendar.addWeeks(weeks)
    return calendar
}

fun Calendar.addMonths(months: Int) {
    add(Calendar.MONTH, months)
}

fun Calendar.plusMonths(months: Int): Calendar {
    val calendar = duplicate()
    calendar.addMonths(months)
    return calendar
}

fun Calendar.addYears(years: Int) {
    add(Calendar.YEAR, years)
}

fun Calendar.plusYears(years: Int): Calendar {
    val calendar = duplicate()
    calendar.addYears(years)
    return calendar
}

fun Calendar.isSameDay(otherDate: Calendar): Boolean {
    return day == otherDate.day &&
            month == otherDate.month &&
            year == otherDate.year
}

fun Calendar.isWeekBefore(otherDate: Calendar): Boolean {
    return isSameDay(otherDate.plusWeeks(-1))
}

fun Calendar.isMonthBefore(otherDate: Calendar): Boolean {
    return isSameDay(otherDate.plusMonths(-1))
}

fun Calendar.duplicate(): Calendar {
    val calendar = Calendar.getInstance()
    calendar.time = time
    return calendar
}

fun Calendar.getFormat(format: String): String {
    val formatter = SimpleDateFormat(format, Locale.US)
    formatter.timeZone = timeZone
    return formatter.format(time)
}

operator fun Calendar.minus(other: Calendar) {
    addYears(-other.year)
    addMonths(-other.month)
    addDays(-other.day)
    addHours(-other.hour)
    addMinutes(-other.minute)
    addSeconds(-other.second)
    addMilliSeconds(-other.milliSecond)
}

operator fun Calendar.plus(other: Calendar) {
    addYears(other.year)
    addMonths(other.month)
    addDays(other.day)
    addHours(other.hour)
    addMinutes(other.minute)
    addSeconds(other.second)
    addMilliSeconds(other.milliSecond)
}


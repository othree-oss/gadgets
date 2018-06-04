package io.othree.gadgets

import java.sql.Date
import java.text.SimpleDateFormat
import java.util.{Calendar, TimeZone, Date => UtilDate}

import scala.util.Try

class DateProvider {
  private val dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
  dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"))

  def now: Date = {
    val calendar = Calendar.getInstance
    calendar.setTimeZone(TimeZone.getTimeZone("UTC"))
    val now = calendar.getTimeInMillis
    val creationDate = new Date(now)
    creationDate
  }

  def addToNow(seconds: Long): Date = {
    val expiryDate = new Date(now.getTime + (seconds * 1000))
    expiryDate
  }

  def toIso8601(date: Date): String = {
    val formated = dateFormat.format(date)
    formated
  }

  def fromIso8601(date: String): Option[Date] = {
    val dateToTimestamp = Try[Date] {
      new Date(dateFormat.parse(date).getTime)
    }.toOption
    dateToTimestamp
  }

  def addToDate(date: Date, seconds: Long): Date = {
    val newDate = new Date(date.getTime + (seconds * 1000))

    newDate
  }

  def getNumberOfDays(date: Date, secondDate: Date): Int = {
    val number = ((date.getTime / 1000) - (secondDate.getTime / 1000)) / 86400

    if (number < 0) {
      -1 * number.toInt
    } else {
      number.toInt
    }
  }

  def toTimeZone(format: String, tz: String, date: Date): String = {
    val sdf = new SimpleDateFormat(format)
    sdf.setTimeZone(TimeZone.getTimeZone(tz))

    val d = new UtilDate(date.getTime)
    sdf.format(d)
  }
}

package io.othree.gadgets

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.{Calendar, Date, TimeZone}

import scala.util.Try

class TimestampProvider {
  private val dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
  dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"))

  def now: Timestamp = {
    val calendar = Calendar.getInstance
    calendar.setTimeZone(TimeZone.getTimeZone("UTC"))
    val now = calendar.getTimeInMillis
    val creationDate = new Timestamp(now)
    creationDate
  }

  def addToNow(seconds: Long): Timestamp = {
    val expiryDate = new Timestamp(now.getTime + (seconds * 1000))
    expiryDate
  }

  def toTimeZone(format: String, tz: String, date: Timestamp): String = {
    val sdf = new SimpleDateFormat(format)
    sdf.setTimeZone(TimeZone.getTimeZone(tz))

    val d = new Date(date.getTime)
    sdf.format(d)
  }

  def toIso8601(timestamp: Timestamp): String = {
    val date = new Date()
    date.setTime(timestamp.getTime)
    val formated = dateFormat.format(date)
    formated
  }

  def fromIso8601(date: String): Option[Timestamp] = {
    val dateToTimestamp = Try[Timestamp] {
      new Timestamp(dateFormat.parse(date).getTime)
    }.toOption
    dateToTimestamp
  }

  def addToDate(date: Timestamp, seconds: Long): Timestamp = {
    val newDate = new Timestamp(date.getTime + (seconds * 1000))

    newDate
  }

  def getNumberOfDays(date: Timestamp, secondDate: Timestamp): Int = {
    val number = ((date.getTime / 1000) - (secondDate.getTime / 1000)) / 86400

    if(number < 0)
      {
        -1*number.toInt
      } else {
      number.toInt
    }

  }
}

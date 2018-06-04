package io.othree.gadgets

import java.sql.Timestamp
import java.util.{Calendar, TimeZone}

import io.othree.aok.BaseTest
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TimestampProviderTest extends BaseTest {

  val dateProvider = new TimestampProvider

  "DateProvider" when {
    "getting the current time" must {
      "return the current timestamp" in {
        val now = dateProvider.now

        now shouldBe a[Timestamp]
      }
    }

    "adding to the current time" must {
      "return a timestamp with the seconds added" in {
        val later = dateProvider.addToNow(1000)

        later shouldBe a[Timestamp]
      }
    }

    "converting a valid String to Timestamp" must {
      "return a valid Timestamp" in {
        val date = dateProvider.fromIso8601("1984-07-16T06:00:00.000Z")
        date.get shouldBe a[Timestamp]
      }
    }
    "conerting an unvalid String to Timestamp" must {
      "return a None" in {
        val date = dateProvider.fromIso8601("NotAValidString")
        date shouldBe None
      }
    }

    "converting a Timestamp to String" must {
      "return a valid formatedString" in {
        val now = dateProvider.now
        val date = dateProvider.toIso8601(now)

        date shouldBe a[String]
      }
    }

    "adding to a date" must {
      "return a valid TimeStamp" in {

        val date = dateProvider.addToDate(Timestamp.valueOf("2018-02-26 00:00:00"), 86400)

        date shouldBe a[Timestamp]
      }
    }

    "getting the quantity of days between two dates" must {
      "return the number of days" in {
        val number = dateProvider.getNumberOfDays(Timestamp.valueOf("2018-02-26 00:00:00"), Timestamp.valueOf("2018-02-28 00:00:00"))

        number shouldBe 2
      }
    }

    "formatting a date to an specific format and timezone" must {
      "return the correct string" in {
        val calendar = Calendar.getInstance
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"))
        calendar.set(2018, 2, 26, 0, 0, 0)

        val formatted = dateProvider.toTimeZone("yyyy-MM-dd hh:mm:ss", "GMT-6", new Timestamp(calendar.getTimeInMillis))
        formatted shouldBe "2018-03-25 06:00:00"
      }
    }
  }
}

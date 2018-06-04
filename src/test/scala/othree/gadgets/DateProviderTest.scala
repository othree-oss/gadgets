package othree.gadgets

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import java.sql.Date
import java.util.{Calendar, TimeZone}

import io.othree.aok.BaseTest

@RunWith(classOf[JUnitRunner])
class DateProviderTest extends BaseTest {

  val dateProvider = new DateProvider

  "DateProvider" when {
    "getting the current time" must {
      "return the current date" in {
        val now = dateProvider.now

        now shouldBe a[Date]
      }
    }

    "adding to the current time" must {
      "return a date with the seconds added" in {
        val later = dateProvider.addToNow(1000)

        later shouldBe a[Date]
      }
    }

    "converting a valid String to date" must {
      "return a valid Date" in {
        val date = dateProvider.fromIso8601("1984-07-16T06:00:00.000Z")
        date.get shouldBe a[Date]
      }
    }
    "conerting an unvalid String to date" must {
      "return a None" in {
        val date = dateProvider.fromIso8601("NotAValidString")
        date shouldBe None
      }
    }

    "converting a Date to String" must {
      "return a valid formatedString" in {
        val now = dateProvider.now
        val date = dateProvider.toIso8601(now)

        date shouldBe a[String]
      }
    }

    "adding to a date" must {
      "return a valid Date" in {

        val date = dateProvider.addToDate(Date.valueOf("2018-02-26"), 86400)

        date shouldBe a[Date]
      }
    }

    "getting the quantity of days between two dates" must {
      "return the number of days" in {
        val number = dateProvider.getNumberOfDays(Date.valueOf("2018-02-26"), Date.valueOf("2018-02-28"))

        number shouldBe 2
      }
    }

    "formatting a date to an specific format and timezone" must {
      "return the correct string" in {
        val calendar = Calendar.getInstance
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"))
        calendar.set(2018, 2, 26, 0, 0, 0)

        val formatted = dateProvider.toTimeZone("yyyy-MM-dd", "GMT-6", new Date(calendar.getTimeInMillis))
        formatted shouldBe "2018-03-25"
      }
    }
  }

}

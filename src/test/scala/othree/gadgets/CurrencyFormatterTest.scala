package othree.gadgets

import io.othree.aok.BaseTest
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class CurrencyFormatterTest extends BaseTest {

  var currencyFormatter : CurrencyFormatter = _

  override protected def beforeAll(): Unit = {
    currencyFormatter = new CurrencyFormatter
  }

  "CurrencyFormatter" when {
    "formatting an amount" must {
      "return the correct formatted string" in {
        val amount = currencyFormatter.format("CRC", BigDecimal(12345.6789))

        amount shouldBe "CRC12,345.68"
      }
    }
  }
}

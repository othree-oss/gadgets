package othree.gadgets

import java.util.Currency

class CurrencyFormatter {

  def format(currency: String, amount: BigDecimal) : String = {
    val crc = Currency.getInstance(currency)

    val formatter = java.text.NumberFormat.getCurrencyInstance
    formatter.setCurrency(crc)

    formatter.format(amount)
  }
}
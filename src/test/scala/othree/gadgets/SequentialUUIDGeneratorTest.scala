package othree.gadgets

import java.util.UUID

import io.othree.aok.BaseTest
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class SequentialUUIDGeneratorTest extends BaseTest {
  val uuidGenerator = new SequentialUUIDGenerator()

  "SequentialUUIDGenerator" when {
    "asked to generate an UUID" must {
      "return a UUID" in {
        val uuid = uuidGenerator.generate()

        uuid shouldBe a[UUID]
      }
    }
  }
}

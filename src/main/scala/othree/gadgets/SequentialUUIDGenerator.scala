package othree.gadgets

import com.groupon.uuid.UUID

class SequentialUUIDGenerator extends UUIDGenerator {
  UUID.useSequentialIds()

  override def generate(): java.util.UUID = {
    new UUID().toJavaUUID
  }
}

import java.sql.Connection
import org.specs2.mutable.Specification
import org.specs2.specification.Example
import play.api.db.DB
import play.api.test.FakeApplication
import play.api.test.Helpers._

abstract class BaseSpec extends Specification {

  def testWithDb(testBlock: Connection => Example): Example = {
    implicit val app = FakeApplication(additionalConfiguration = inMemoryDatabase())
    running(app) {
      DB.withConnection { implicit c => testBlock(c) }
    }
  }

}

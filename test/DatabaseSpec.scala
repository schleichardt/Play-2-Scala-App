import org.specs2.mutable._

import org.specs2.specification.Example
import play.api.db.DB
import anorm._
import play.api.test._
import play.api.test.Helpers._
import java.sql.Connection

class DatabaseSpec extends Specification {
  "App" should {
    "be able to connect with a database" in {
      testWithDb { implicit c =>
        val result: Boolean = SQL("Select 1").execute()
        result === true
      }
    }
  }

  def testWithDb(testBlock: Connection => Example): Example = {
    implicit val app = FakeApplication(additionalConfiguration = inMemoryDatabase())
    running(app) {
      DB.withConnection { implicit c => testBlock(c) }
    }
  }
}
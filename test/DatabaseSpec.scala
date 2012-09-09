import org.specs2.mutable._

import play.api.db.DB
import anorm._
import play.api.test._
import play.api.test.Helpers._

class DatabaseSpec extends Specification {

  "App" should {
    "be able to connect with a database" in {
      implicit val app = FakeApplication(additionalConfiguration = inMemoryDatabase())
      running(app) {
        DB.withConnection { implicit c =>
          val result: Boolean = SQL("Select 1").execute()
          result === true
        }
      }
    }
  }
}
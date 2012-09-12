import anorm._
import models.Person
import play.api.db.DB
import play.api.test.FakeApplication
import play.api.test.Helpers._

class PersonSpec extends BaseSpec {
  "person data" should {
    "be able to get optained from the database" in {
      implicit val app = FakeApplication(additionalConfiguration = inMemoryDatabase())
      running(app) {
        println("App is running")
        DB.withConnection { implicit c =>
          val allPersons = Person.findAll()
          allPersons.size === 1
          allPersons.head.lastName === "Schleichardt"
        }
      }
    }
  }
}

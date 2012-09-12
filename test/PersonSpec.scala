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
        DB.withConnection { implicit c =>
          val allPersons = Person.findAll()
          allPersons.size === 1
          allPersons.head.lastName === "Schleichardt"
        }
      }
    }
  }

  "person data" should {
    "be stored in the database" in {
      implicit val app = FakeApplication(additionalConfiguration = inMemoryDatabase())
      running(app) {
        DB.withConnection { implicit c =>
          Person.findAll().size === 1
          Person.insert(Person(NotAssigned, "Vorname", "Nachname"))
          val personsStored = Person.findAll()
          personsStored.size === 2
          personsStored.find(_.lastName == "Nachname").get.firstName === "Vorname"
        }
      }
    }
  }
}

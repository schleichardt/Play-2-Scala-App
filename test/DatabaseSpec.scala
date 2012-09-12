import anorm._

class DatabaseSpec extends BaseSpec {
  "App" should {
    "be able to connect with a database" in {
      testWithDb { implicit c =>
        val result: Boolean = SQL("Select 1").execute()
        result === true
      }
    }
  }
}
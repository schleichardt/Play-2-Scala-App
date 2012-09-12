package models

import anorm.Pk
import play.api.db.DB
import java.util.{Date}

import play.api.db._
import play.api.Play.current

import anorm._
import anorm.SqlParser._

case class Person(id: Pk[Long], firstName: String, lastName: String)
//TODO members of case class in camel case, in database in lower case

object Person {
  val tableName = "person"

  val simple = {
    get[Pk[Long]]("id") ~
      get[String]("firstname") ~
      get[String]("lastname") map {
      case id ~ firstName ~ lastName => Person(id, firstName, lastName)
    }
  }

  def findAll(): Seq[Person] = {
    DB.withConnection {
      implicit connection =>
        SQL("select * from %s".format(tableName)).as(Person.simple *)
    }
  }

  def insert(person: Person) = {
    DB.withConnection {
      implicit connection =>
        SQL(
          """
          insert into %s(firstname, lastname) values (
            {firstname}, {lastname}
          )
          """.format(tableName)
        ).on(
          'firstname -> person.firstName,
          'lastname -> person.lastName
        ).executeUpdate()
    }
  }
}
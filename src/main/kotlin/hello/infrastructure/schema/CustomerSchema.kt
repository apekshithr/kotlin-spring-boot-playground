package hello.infrastructure.schema

import org.jetbrains.exposed.sql.*

object Customers : Table() {
    val id = integer("id").autoIncrement().primaryKey()
    val firstName = text("first_name")
    val lastName = text("last_name")
}

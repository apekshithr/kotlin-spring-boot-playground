package hello.infrastructure.repository

import hello.domain.entities.Customer
import hello.infrastructure.schema.Customers
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

interface CustomerRepository : CrudRepository<Customer, String>

@Repository
@Transactional
class DefaultCustomerRepository : CustomerRepository {

    override fun createTable() = SchemaUtils.create(Customers)

    override fun create(customer: Customer): Customer {
        Customers.insert(toRow(customer))
        return customer
    }


    override fun findAll() = Customers.selectAll().map { fromRow(it) }

    override fun deleteAll() = Customers.deleteAll()

    private fun toRow(u: Customer): Customers.(UpdateBuilder<*>) -> Unit = {
        it[firstName] = u.firstName
        it[lastName] = u.lastName
    }

    private fun fromRow(r: ResultRow) = Customer(r[Customers.firstName], r[Customers.lastName])
}

package hello.infrastructure.repository

import hello.domain.entities.Customer
import org.springframework.data.mongodb.repository.MongoRepository

interface CustomerRepository : MongoRepository<Customer, String> {
	fun findByLastName(lastName: String): Iterable<Customer>
	fun findByFirstName(firstName: String): Iterable<Customer>
}

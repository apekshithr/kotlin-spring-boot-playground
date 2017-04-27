package hello.application.controller

import hello.infrastructure.repository.CustomerRepository
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class CustomerController(val repository: CustomerRepository) {

	@GetMapping("/")
	fun findAll() = transaction { repository.findAll() }
}

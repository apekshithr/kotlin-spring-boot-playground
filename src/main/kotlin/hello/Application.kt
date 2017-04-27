package hello

import hello.domain.entities.Customer
import hello.infrastructure.repository.CustomerRepository
import org.jetbrains.exposed.spring.SpringTransactionManager
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

@SpringBootApplication
@EnableTransactionManagement
class Application {

	private val log = LoggerFactory.getLogger(Application::class.java)


	@Bean
	open fun transactionManager(dataSource: DataSource) = SpringTransactionManager(dataSource)

	@Bean // PersistenceExceptionTranslationPostProcessor with proxyTargetClass=false, see https://github.com/spring-projects/spring-boot/issues/1844
	open fun persistenceExceptionTranslationPostProcessor() = PersistenceExceptionTranslationPostProcessor()

	@Bean
	fun init(repository: CustomerRepository) = CommandLineRunner {
		Database.connect(
			"jdbc:postgresql://127.0.0.1:5432/customer",
			driver = "org.postgresql.Driver",
			user = "postgres",
			password = "postgres"
		)

		transaction {
			repository.createTable()
			repository.deleteAll()

			// save a couple of customers
			repository.create(Customer("Jack", "Bauer"))
			repository.create(Customer("Chloe", "O'Brian"))
			repository.create(Customer("Kim", "Bauer"))
			repository.create(Customer("David", "Palmer"))
			repository.create(Customer("Michelle", "Dessler"))
		}

		// fetch all customers
		log.info("Customers found with findAll():")
		log.info("-------------------------------")
		transaction {
			for (customer in repository.findAll()) {
				log.info(customer.toString())
			}
		}
		log.info("")
	}
}

fun main(args: Array<String>) {
	SpringApplication.run(Application::class.java, *args)
}

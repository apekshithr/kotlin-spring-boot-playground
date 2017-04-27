package hello.domain.entities

import org.springframework.data.annotation.PersistenceConstructor

data class Customer @PersistenceConstructor constructor(
        val firstName: String,
        val lastName: String
) {

    override fun toString(): String {
        return "Customer(firstName='$firstName', lastName='$lastName')"
    }
}

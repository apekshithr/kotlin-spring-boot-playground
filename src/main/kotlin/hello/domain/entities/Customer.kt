package hello.domain.entities

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.PersistenceConstructor
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection="customers")
data class Customer @PersistenceConstructor constructor(
	val firstName: String,
	val lastName: String,
	@Id val id: ObjectId? = null
	) {

	override fun toString(): String {
		return "Customer(id=$id, firstName='$firstName', lastName='$lastName')"
	}
}

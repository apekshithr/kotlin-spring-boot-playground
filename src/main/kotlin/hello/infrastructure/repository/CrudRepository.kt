package hello.infrastructure.repository

interface CrudRepository<T, K> {
    fun createTable()
    fun create(m: T): T
    fun findAll(): Iterable<T>
    fun deleteAll(): Int
}

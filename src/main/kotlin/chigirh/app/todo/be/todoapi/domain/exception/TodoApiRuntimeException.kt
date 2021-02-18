package chigirh.app.todo.be.todoapi.domain.exception

open class TodoApiRuntimeException(
    override val message: String
) : RuntimeException(message)
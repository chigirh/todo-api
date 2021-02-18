package chigirh.app.todo.be.todoapi.domain.exception

class NotFoundException(
    val target: String,
    override val message: String = "NOT FOUND"
) : TodoApiRuntimeException(message)
package chigirh.app.todo.be.todoapi.domain.exception

class ConflictException(
    val target: String,
    override val message: String = "CONFLICT"
) : TodoApiRuntimeException(message)
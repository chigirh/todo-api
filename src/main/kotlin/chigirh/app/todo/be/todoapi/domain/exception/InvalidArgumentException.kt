package chigirh.app.todo.be.todoapi.domain.exception

class InvalidArgumentException(
    val target: String,
    override val message: String = "INVALID ARGUMENT"
) : TodoApiRuntimeException(message)
package chigirh.app.todo.be.todoapi.domain.exception

class InternalException(
    val target: String,
    override val message: String = "INTERNAL"
) : TodoApiRuntimeException(message)
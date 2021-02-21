package chigirh.app.todo.be.todoapi.domain.exception

class NotImplementedException(
    val target: String,
    override val message: String = "NOT IMPLEMENTED"
) : TodoApiRuntimeException(message)
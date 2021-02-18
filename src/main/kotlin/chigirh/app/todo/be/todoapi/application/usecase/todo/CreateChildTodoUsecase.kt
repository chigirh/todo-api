package chigirh.app.todo.be.todoapi.application.usecase.todo

import chigirh.app.todo.be.todoapi.application.repository.todo.TodoRepository
import chigirh.app.todo.be.todoapi.application.usecase.Usecase
import chigirh.app.todo.be.todoapi.domain.exception.InvalidArgumentException
import chigirh.app.todo.be.todoapi.domain.model.todo.ChildTodoEntity
import chigirh.app.todo.be.todoapi.domain.model.vo.TodoId
import chigirh.app.todo.be.todoapi.domain.model.vo.UserId
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class CreateChildTodoUsecase(
    val todoRepository: TodoRepository
) : Usecase() {
    operator fun invoke(
        userId: UserId,
        todoName: String,
        limitDate: LocalDateTime?,
        parentTodoId: TodoId?
    ) = TodoId().run {
        ChildTodoEntity(
            todoId = this,
            todoName = todoName,
            isFinished = false,
            limitDate = limitDate,
            parentTodoId = parentTodoId ?: throw InvalidArgumentException("parentTodoId", "REQUIRED")
        ).apply { create(userId) }
            .run { todoRepository.insertBy(this) }
    }

}
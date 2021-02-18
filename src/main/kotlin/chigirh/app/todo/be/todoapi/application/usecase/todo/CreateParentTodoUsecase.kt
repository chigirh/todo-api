package chigirh.app.todo.be.todoapi.application.usecase.todo

import chigirh.app.todo.be.todoapi.application.repository.todo.TodoRepository
import chigirh.app.todo.be.todoapi.application.usecase.Usecase
import chigirh.app.todo.be.todoapi.domain.model.todo.ParentTodoEntity
import chigirh.app.todo.be.todoapi.domain.model.vo.TodoId
import chigirh.app.todo.be.todoapi.domain.model.vo.UserId
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class CreateParentTodoUsecase(
    val todoRepository: TodoRepository
) : Usecase() {
    operator fun invoke(
        userId: UserId,
        todoName: String,
        limitDate: LocalDateTime?
    ) = TodoId().run {
        ParentTodoEntity(
            todoId = this,
            todoName = todoName,
            isFinished = false,
            limitDate = limitDate,
        ).apply { create(userId) }
            .run { todoRepository.insertBy(this) }
    }

}
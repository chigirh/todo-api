package chigirh.app.todo.be.todoapi.application.usecase.todo

import chigirh.app.todo.be.todoapi.application.repository.todo.TodoRepository
import chigirh.app.todo.be.todoapi.application.usecase.Usecase
import chigirh.app.todo.be.todoapi.domain.model.todo.TodosEntity
import chigirh.app.todo.be.todoapi.domain.model.vo.UserId
import org.springframework.stereotype.Component

@Component
class ListTodoUsecase(
    val todoRepository: TodoRepository
) : Usecase() {
    operator fun invoke(
        userId: UserId,
        offset: Int,
        limit: Int
    ) = TodosEntity(
        total = todoRepository.findCount(userId),
        entitys = todoRepository.selectBy(userId, offset, limit)
    )

}
package chigirh.app.todo.be.todoapi.application.usecase.todo

import chigirh.app.todo.be.todoapi.application.repository.todo.TodoRepository
import chigirh.app.todo.be.todoapi.application.usecase.Usecase
import chigirh.app.todo.be.todoapi.domain.model.todo.ChildTodoEntity
import chigirh.app.todo.be.todoapi.domain.model.vo.UserId
import org.springframework.stereotype.Component

@Component
class UpdateChildTodoUsecase(
    val todoRepository: TodoRepository
) : Usecase() {
    operator fun invoke(
        userId: UserId,
        childTodoEntity: ChildTodoEntity
    ) = childTodoEntity.run {
        update(userId)
        todoRepository.updateBySelective(this)
    }

}
package chigirh.app.todo.be.todoapi.application.usecase.todo

import chigirh.app.todo.be.todoapi.application.repository.todo.TodoRepository
import chigirh.app.todo.be.todoapi.application.usecase.Usecase
import chigirh.app.todo.be.todoapi.domain.model.todo.ParentTodoEntity
import chigirh.app.todo.be.todoapi.domain.model.vo.UserId
import org.springframework.stereotype.Component

@Component
class DeleteParentTodoUsecase(
    val todoRepository: TodoRepository
) : Usecase() {
    operator fun invoke(
        userId: UserId,
        parentTodoEntity: ParentTodoEntity
    ) = parentTodoEntity.run {
        todoRepository.selectByForChildTodoList(this)
            .forEach(todoRepository::deleteBy)
        todoRepository.deleteBy(this)
    }

}
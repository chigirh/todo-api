package chigirh.app.todo.be.todoapi.application.usecase.todo

import chigirh.app.todo.be.todoapi.application.repository.todo.TodoRepository
import chigirh.app.todo.be.todoapi.application.usecase.Usecase
import chigirh.app.todo.be.todoapi.domain.exception.NotFoundException
import chigirh.app.todo.be.todoapi.domain.model.todo.TodoType
import chigirh.app.todo.be.todoapi.domain.model.todo.TodoType.PARENT
import chigirh.app.todo.be.todoapi.domain.model.vo.TodoId
import chigirh.app.todo.be.todoapi.domain.model.vo.UserId
import org.springframework.stereotype.Component

@Component
class FinishTodoUsecase(
    val todoRepository: TodoRepository
) : Usecase() {
    operator fun invoke(
        userId: UserId,
        todoId: TodoId,
        todoType: TodoType,
        isFinished: Boolean
    ) = if (todoType == PARENT)
        parentFinish(userId, todoId, isFinished)
    else
        childFinish(userId, todoId, isFinished)

    private fun parentFinish(
        userId: UserId,
        todoId: TodoId,
        isFinished: Boolean
    ) = todoRepository.selectByParentTodoId(todoId)?.run {
        if (isFinished) {
            //親Todoを完了にした場合は紐づく子Todoも全て完了にする。
            if (finish(userId)) {
                todoRepository.updateBy(this)
                todoRepository.selectByForChildTodoList(this).forEach {
                    if (it.finish(userId)) todoRepository.updateBy(it)
                }
            }
        } else {
            if (release(userId)) todoRepository.updateBy(this)
        }
    } ?: throw NotFoundException("ParentTodoId")

    fun childFinish(
        userId: UserId,
        todoId: TodoId,
        isFinished: Boolean
    ) = todoRepository.selectByChildTodoId(todoId)?.run {
        if (isFinished) {
            if (finish(userId)) todoRepository.updateBy(this);
        } else {
            //子Todoを未完了にした場合、親Todoが完了になっていた場合は未完了にする。
            if (release(userId)) {
                todoRepository.updateBy(this)
                todoRepository.selectByParentTodoId(parentTodoId)?.run {
                    if (release(userId)) todoRepository.updateBy(this)
                } ?: throw NotFoundException("ParentTodoId")//この例外は基本発生しない。
            }
        }
    } ?: throw NotFoundException("ChildTodoId")
}
package chigirh.app.todo.be.todoapi.web.converter

import chigirh.app.todo.be.todoapi.domain.model.todo.ParentTodoEntity
import chigirh.app.todo.be.todoapi.domain.model.vo.TodoId
import chigirh.app.todo.be.todoapi.domain.model.vo.Version
import chigirh.app.todo.be.todoapi.oas3.model.ParentTodo
import chigirh.app.todo.be.todoapi.oas3.model.TodoDetail
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.OffsetDateTime

@Component
class ParentTodoConverter(
    val childTodoConverter: ChildTodoConverter
) : Converter<ParentTodoEntity, ParentTodo, ParentTodo> {
    override fun toResponse(entity: ParentTodoEntity) = entity.let {
        ParentTodo(
            todoId = it.todoId.v,
            version = it.version.v,
            detail = TodoDetail(
                todoName = it.todoName,
                isFinished = it.isFinished,
                limitDate = it.limitDate?.let { OffsetDateTime.from(it) },
                finishDate = it.finishDate?.let { OffsetDateTime.from(it) },
            ),
            children = it.children.map(childTodoConverter::toResponse).toList()
        )
    }

    override fun toEntity(request: ParentTodo) = request.let {
        ParentTodoEntity(
            todoId = TodoId(it.todoId),
            todoName = it.detail.todoName,
            isFinished = it.detail.isFinished,
            limitDate = it.detail.limitDate?.let { LocalDateTime.from(it) },
            finishDate = it.detail.finishDate?.let { LocalDateTime.from(it) },
            version = Version(it.version)
        )
    }
}
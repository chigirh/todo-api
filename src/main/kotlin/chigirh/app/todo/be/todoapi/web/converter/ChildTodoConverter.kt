package chigirh.app.todo.be.todoapi.web.converter

import chigirh.app.todo.be.todoapi.domain.model.todo.ChildTodoEntity
import chigirh.app.todo.be.todoapi.domain.model.vo.TodoId
import chigirh.app.todo.be.todoapi.domain.model.vo.Version
import chigirh.app.todo.be.todoapi.oas3.model.ChildTodo
import chigirh.app.todo.be.todoapi.oas3.model.TodoDetail
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneOffset

@Component
class ChildTodoConverter : Converter<ChildTodoEntity, ChildTodo, ChildTodo> {
    override fun toResponse(entity: ChildTodoEntity) = ChildTodo(
        todoId = entity.todoId.v,
        version = entity.version.v,
        detail = TodoDetail(
            todoName = entity.todoName,
            isFinished = entity.isFinished,
            limitDate = entity.limitDate?.let { OffsetDateTime.of(it, ZoneOffset.UTC) },
            finishDate = entity.finishDate?.let { OffsetDateTime.of(it, ZoneOffset.UTC) },
        ),
        parentTodoId = entity.parentTodoId.v
    )

    override fun toEntity(request: ChildTodo) = request.let {
        ChildTodoEntity(
            todoId = TodoId(it.todoId),
            todoName = it.detail.todoName,
            isFinished = it.detail.isFinished,
            limitDate = it.detail.limitDate?.let { LocalDateTime.from(it) },
            finishDate = it.detail.finishDate?.let { LocalDateTime.from(it) },
            version = Version(it.version),
            parentTodoId = TodoId(it.parentTodoId)
        )
    }
}
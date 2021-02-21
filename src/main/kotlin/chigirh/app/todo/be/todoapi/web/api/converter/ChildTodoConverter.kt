package chigirh.app.todo.be.todoapi.web.api.converter

import chigirh.app.todo.be.todoapi.domain.model.todo.ChildTodoEntity
import chigirh.app.todo.be.todoapi.domain.model.vo.TodoId
import chigirh.app.todo.be.todoapi.domain.model.vo.Version
import chigirh.app.todo.be.todoapi.oas3.model.ChildTodo
import chigirh.app.todo.be.todoapi.oas3.model.TodoDetail
import chigirh.app.todo.be.todoapi.web.converter.EntityConverter
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneOffset
import org.springframework.stereotype.Component

@Component
class ChildTodoConverter : EntityConverter<ChildTodoEntity, ChildTodo, ChildTodo> {
    override fun toResponse(entity: ChildTodoEntity) = entity.let {
        ChildTodo(
            todoId = it.todoId.v,
            version = it.version.v,
            detail = TodoDetail(
                todoName = it.todoName,
                isFinished = it.isFinished,
                limitDate = it.limitDate?.let { OffsetDateTime.of(it, ZoneOffset.UTC) },
                finishDate = it.finishDate?.let { OffsetDateTime.of(it, ZoneOffset.UTC) },
            ),
            parentTodoId = it.parentTodoId.v
        )
    }

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
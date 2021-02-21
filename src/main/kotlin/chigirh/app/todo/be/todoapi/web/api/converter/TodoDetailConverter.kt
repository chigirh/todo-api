package chigirh.app.todo.be.todoapi.web.api.converter

import chigirh.app.todo.be.todoapi.domain.model.todo.TodoEntity
import chigirh.app.todo.be.todoapi.oas3.model.TodoDetail
import chigirh.app.todo.be.todoapi.web.converter.EntityConverter
import java.time.OffsetDateTime
import java.time.ZoneOffset
import org.springframework.stereotype.Component

@Component
class TodoDetailConverter : EntityConverter<TodoEntity, TodoDetail, TodoDetail> {
    override fun toResponse(entity: TodoEntity) = entity.let {
        TodoDetail(
            todoName = it.todoName,
            isFinished = it.isFinished,
            limitDate = it.limitDate?.let { OffsetDateTime.of(it, ZoneOffset.UTC) },
            finishDate = it.finishDate?.let { OffsetDateTime.of(it, ZoneOffset.UTC) },
        )
    }
}
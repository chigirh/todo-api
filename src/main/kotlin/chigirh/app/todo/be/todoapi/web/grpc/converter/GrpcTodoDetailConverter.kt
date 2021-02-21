package chigirh.app.todo.be.todoapi.web.grpc.converter

import chigirh.app.todo.be.sample.grpc.model.TodoDetail
import chigirh.app.todo.be.todoapi.domain.model.todo.TodoEntity
import chigirh.app.todo.be.todoapi.web.converter.EntityConverter
import org.springframework.stereotype.Component

@Component
class GrpcTodoDetailConverter(
    val grpcTimestampConverter: GrpcTimestampConverter
) : EntityConverter<TodoEntity, TodoDetail, TodoDetail> {
    override fun toResponse(entity: TodoEntity) = entity.let {
        TodoDetail.newBuilder()
            .setTodoName(it.todoName)
            .setIsFinished(it.isFinished)
            .setLimitDate(it.limitDate?.let { grpcTimestampConverter.toResponse(it) })
            .setFinishDate(it.finishDate?.let { grpcTimestampConverter.toResponse(it) })
            .build()
    }
}

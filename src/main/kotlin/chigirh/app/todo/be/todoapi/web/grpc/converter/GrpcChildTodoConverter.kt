package chigirh.app.todo.be.todoapi.web.grpc.converter

import chigirh.app.todo.be.sample.grpc.model.ChildTodo
import chigirh.app.todo.be.todoapi.domain.model.todo.ChildTodoEntity
import chigirh.app.todo.be.todoapi.domain.model.vo.TodoId
import chigirh.app.todo.be.todoapi.web.converter.EntityConverter
import org.springframework.stereotype.Component

@Component
class GrpcChildTodoConverter(
    val grpcTodoDetailConverter: GrpcTodoDetailConverter,
    val grpcVersionConverter: GrpcVersionConverter,
    val grpcTimestampConverter: GrpcTimestampConverter
) : EntityConverter<ChildTodoEntity, ChildTodo, ChildTodo> {
    override fun toResponse(entity: ChildTodoEntity) = entity.let {
        ChildTodo.newBuilder()
            .setTodoId(it.todoId.v)
            .setDetail(grpcTodoDetailConverter.toResponse(it))
            .setVersion(grpcVersionConverter.toResponse(it.version))
            .setParentTodoId(it.parentTodoId.v)
            .build()
    }

    override fun toEntity(request: ChildTodo) = request.let {
        ChildTodoEntity(
            todoId = TodoId(it.todoId),
            todoName = it.detail.todoName,
            isFinished = it.detail.isFinished,
            limitDate = it.detail.limitDate?.let { grpcTimestampConverter.toEntity(it) },
            finishDate = it.detail.finishDate?.let { grpcTimestampConverter.toEntity(it) },
            version = grpcVersionConverter.toEntity(it.version),
            parentTodoId = TodoId(it.parentTodoId)
        )
    }
}
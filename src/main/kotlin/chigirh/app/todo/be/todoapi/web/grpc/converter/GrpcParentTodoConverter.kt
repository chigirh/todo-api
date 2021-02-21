package chigirh.app.todo.be.todoapi.web.grpc.converter

import chigirh.app.todo.be.sample.grpc.model.ParentTodo
import chigirh.app.todo.be.todoapi.domain.model.todo.ParentTodoEntity
import chigirh.app.todo.be.todoapi.domain.model.vo.TodoId
import chigirh.app.todo.be.todoapi.web.converter.EntityConverter
import org.springframework.stereotype.Component

@Component
class GrpcParentTodoConverter(
    val grpcChildTodoConverter: GrpcChildTodoConverter,
    val grpcTodoDetailConverter: GrpcTodoDetailConverter,
    val grpcVersionConverter: GrpcVersionConverter,
    val grpcTimestampConverter: GrpcTimestampConverter
) : EntityConverter<ParentTodoEntity, ParentTodo, ParentTodo> {
    override fun toResponse(entity: ParentTodoEntity) = entity.let {
        ParentTodo.newBuilder()
            .setTodoId(it.todoId.v)
            .setDetail(grpcTodoDetailConverter.toResponse(it))
            .setVersion(grpcVersionConverter.toResponse(it.version))
            .addAllChildren(
                it.children.map(grpcChildTodoConverter::toResponse).toList()
            ).build()
    }

    override fun toEntity(request: ParentTodo) = request.let {
        ParentTodoEntity(
            todoId = TodoId(it.todoId),
            todoName = it.detail.todoName,
            isFinished = it.detail.isFinished,
            limitDate = it.detail.limitDate?.let { grpcTimestampConverter.toEntity(it) },
            finishDate = it.detail.finishDate?.let { grpcTimestampConverter.toEntity(it) },
            version = grpcVersionConverter.toEntity(it.version)
        )
    }
}
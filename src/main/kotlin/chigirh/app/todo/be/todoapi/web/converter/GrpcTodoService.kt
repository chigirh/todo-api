package chigirh.app.todo.be.todoapi.web.converter

import chigirh.app.todo.be.todoapi.application.usecase.todo.*
import chigirh.app.todo.be.todoapi.domain.model.vo.TodoId
import chigirh.app.todo.be.todoapi.grpc.model.PageableResponse
import chigirh.app.todo.be.todoapi.grpc.service.*
import chigirh.app.todo.be.todoapi.web.grpc.converter.GrpcChildTodoConverter
import chigirh.app.todo.be.todoapi.web.grpc.converter.GrpcParentTodoConverter
import chigirh.app.todo.be.todoapi.web.grpc.converter.GrpcTimestampConverter
import chigirh.app.todo.be.todoapi.web.grpc.converter.GrpcXUserIdConverter
import com.google.protobuf.Empty
import io.grpc.stub.StreamObserver
import chigirh.app.todo.be.sample.grpc.model.TodoType as GrpcTodoType
import chigirh.app.todo.be.todoapi.domain.model.todo.TodoType as TodoTypeEntity

class GrpcTodoService(
    val createParentTodoUsecase: CreateParentTodoUsecase,
    val createChildTodoUsecase: CreateChildTodoUsecase,
    val updateParentTodoUsecase: UpdateParentTodoUsecase,
    val updateChildTodoUsecase: UpdateChildTodoUsecase,
    val deleteParentTodoUsecase: DeleteParentTodoUsecase,
    val deleteChildTodoUsecase: DeleteChildTodoUsecase,
    val listTodoUsecase: ListTodoUsecase,
    val finishTodoUsecase: FinishTodoUsecase,
    val grpcParentTodoConverter: GrpcParentTodoConverter,
    val grpcChildTodoConverter: GrpcChildTodoConverter,
    val grpcXUserIdConverter: GrpcXUserIdConverter,
    val grpcTimestampConverter: GrpcTimestampConverter
) : TodoServiceGrpc.TodoServiceImplBase() {
    override fun createParentTodo(
        request: CreateParentTodoRequest,
        responseObserver: StreamObserver<Empty>
    ) {
        createParentTodoUsecase(
            userId = grpcXUserIdConverter.toEntity(request.xUserId),
            todoName = request.todoDetail.todoName,
            limitDate = grpcTimestampConverter.toEntity(request.todoDetail.limitDate)
        )
        responseObserver.onNext(EMPTY)
        responseObserver.onCompleted()
    }

    override fun createChildTodo(
        request: CreateChildTodoRequest,
        responseObserver: StreamObserver<Empty>
    ) {
        createChildTodoUsecase(
            userId = grpcXUserIdConverter.toEntity(request.xUserId),
            todoName = request.todoDetail.todoName,
            limitDate = grpcTimestampConverter.toEntity(request.todoDetail.limitDate),
            parentTodoId = TodoId(request.parentTodoId)
        )
        responseObserver.onNext(EMPTY)
        responseObserver.onCompleted()
    }

    override fun updateParentTodo(
        request: UpdateParentTodoRequest,
        responseObserver: StreamObserver<Empty>
    ) {
        updateParentTodoUsecase(
            grpcXUserIdConverter.toEntity(request.xUserId),
            grpcParentTodoConverter.toEntity(request.parentTodo)
        )
        responseObserver.onNext(EMPTY)
        responseObserver.onCompleted()
    }

    override fun updateChildTodo(
        request: UpdateChildTodoRequest,
        responseObserver: StreamObserver<Empty>
    ) {
        updateChildTodoUsecase(
            grpcXUserIdConverter.toEntity(request.xUserId),
            grpcChildTodoConverter.toEntity(request.childTodo)
        )
        responseObserver.onNext(EMPTY)
        responseObserver.onCompleted()
    }

    override fun deleteParentTodo(
        request: DeleteParentTodoRequest,
        responseObserver: StreamObserver<Empty>
    ) {
        deleteParentTodoUsecase(
            grpcXUserIdConverter.toEntity(request.xUserId),
            grpcParentTodoConverter.toEntity(request.parentTodo)
        )
        responseObserver.onNext(EMPTY)
        responseObserver.onCompleted()
    }

    override fun deleteChildTodo(
        request: DeleteChildTodoRequest,
        responseObserver: StreamObserver<Empty>
    ) {
        deleteChildTodoUsecase(
            grpcXUserIdConverter.toEntity(request.xUserId),
            grpcChildTodoConverter.toEntity(request.childTodo)
        )
        responseObserver.onNext(EMPTY)
        responseObserver.onCompleted()
    }

    override fun listTodo(
        request: ListTodoRequest,
        responseObserver: StreamObserver<ListTodoResponse>
    ) {
        val res = listTodoUsecase(
            grpcXUserIdConverter.toEntity(request.xUserId),
            request.pageable.offset,
            request.pageable.limit
        ).let {
            ListTodoResponse.newBuilder()
                .setPageable(
                    PageableResponse.newBuilder()
                        .setOffset(request.pageable.offset)
                        .setLimit(request.pageable.limit)
                        .setTotal(it.total)
                ).addAllTodos(
                    it.entitys.map(grpcParentTodoConverter::toResponse).toList()
                )
                .build()
        }
        responseObserver.onNext(res)
        responseObserver.onCompleted()
    }

    override fun finishTodo(
        request: FinishTodoRequest,
        responseObserver: StreamObserver<Empty>
    ) {
        finishTodoUsecase(
            userId = grpcXUserIdConverter.toEntity(request.xUserId),
            todoId = TodoId(request.todoId),
            todoType = if (request.todoType == GrpcTodoType.PARENT) TodoTypeEntity.PARENT
            else TodoTypeEntity.CHILD,
            isFinished = request.isFinished
        )
        responseObserver.onNext(EMPTY)
        responseObserver.onCompleted()
    }

    companion object {
        private val EMPTY = Empty.newBuilder().build()
    }
}
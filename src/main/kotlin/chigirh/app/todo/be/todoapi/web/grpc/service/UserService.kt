package chigirh.app.todo.be.todoapi.web.grpc.service

import chigirh.app.todo.be.todoapi.application.usecase.user.*
import chigirh.app.todo.be.todoapi.domain.constant.UserConstant
import chigirh.app.todo.be.todoapi.domain.exception.NotFoundException
import chigirh.app.todo.be.todoapi.domain.model.vo.UserId
import chigirh.app.todo.be.todoapi.grpc.model.PageableResponse
import chigirh.app.todo.be.todoapi.grpc.service.*
import chigirh.app.todo.be.todoapi.web.grpc.converter.GrpcUserConverter
import chigirh.app.todo.be.todoapi.web.grpc.converter.GrpcVersionConverter
import chigirh.app.todo.be.todoapi.web.grpc.converter.GrpcXUserIdConverter
import com.google.protobuf.Empty
import io.grpc.stub.StreamObserver
import org.lognet.springboot.grpc.GRpcService

/**
 * gRPC UserService.
 */
@GRpcService
class UserService(
    val grpcUserConverter: GrpcUserConverter,
    val grpcVersionConverter: GrpcVersionConverter,
    val grpcXUserIdConverter: GrpcXUserIdConverter,
    val createUserUsecase: CreateUserUsecase,
    val updateUserUsecase: UpdateUserUsecase,
    val deleteUserUsecase: DeleteUserUsecase,
    val listUserUsecase: ListUserUsecase,
    val getUserUsecase: GetUserUsecase
) : UserServiceGrpc.UserServiceImplBase() {
    override fun createUser(
        request: CreateUserRequest,
        responseObserver: StreamObserver<Empty>
    ) {
        createUserUsecase(
            request.userDetail.userName,
            UserConstant.DEFAULT_PASSWORD
        )
        responseObserver.onNext(EMPTY)
        responseObserver.onCompleted()
    }

    override fun updateUser(
        request: UpdateUserRequest,
        responseObserver: StreamObserver<Empty>
    ) {
        updateUserUsecase(
            grpcXUserIdConverter.toEntity(request.xUserId),
            grpcUserConverter.toEntity(request.user)
        )
        responseObserver.onNext(EMPTY)
        responseObserver.onCompleted()
    }

    override fun deleteUser(
        request: DeleteUserRequest,
        responseObserver: StreamObserver<Empty>
    ) {
        deleteUserUsecase(
            grpcXUserIdConverter.toEntity(request.xUserId),
            grpcUserConverter.toEntity(request.user)
        )
        responseObserver.onNext(EMPTY)
        responseObserver.onCompleted()
    }

    override fun getUser(
        request: GetUserRequest,
        responseObserver: StreamObserver<GetUserResponse>
    ) {
        val res = getUserUsecase(
            grpcXUserIdConverter.toEntity(request.xUserId),
            UserId(request.userId)
        )?.let {
            GetUserResponse.newBuilder()
                .setUser(grpcUserConverter.toResponse(it))
                .build()
        }
            ?: throw NotFoundException("userId", request.userId)
        responseObserver.onNext(res)
        responseObserver.onCompleted()
    }

    override fun listUser(
        request: ListUserRequest,
        responseObserver: StreamObserver<ListUserResponse>
    ) {
        val res = listUserUsecase(
            grpcXUserIdConverter.toEntity(request.xUserId),
            request.pageable.offset,
            request.pageable.limit
        ).let {
            ListUserResponse.newBuilder()
                .setPageable(
                    PageableResponse.newBuilder()
                        .setOffset(request.pageable.offset)
                        .setLimit(request.pageable.limit)
                        .setTotal(it.total)
                ).addAllUsers(
                    it.entitys.map(grpcUserConverter::toResponse).toList()
                )
                .build()
        }

        responseObserver.onNext(res)
        responseObserver.onCompleted()
    }

    companion object {
        private val EMPTY = Empty.newBuilder().build()
    }
}
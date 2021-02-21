package chigirh.app.todo.be.todoapi.web.grpc.converter

import chigirh.app.todo.be.sample.grpc.model.User
import chigirh.app.todo.be.sample.grpc.model.UserDetail
import chigirh.app.todo.be.todoapi.domain.constant.UserConstant
import chigirh.app.todo.be.todoapi.domain.model.user.UserEntity
import chigirh.app.todo.be.todoapi.domain.model.vo.UserId
import chigirh.app.todo.be.todoapi.domain.model.vo.Version
import chigirh.app.todo.be.todoapi.web.converter.EntityConverter
import org.springframework.stereotype.Component

@Component
class GrpcUserConverter(
    val grpcVersionConverter: GrpcVersionConverter
) : EntityConverter<UserEntity, User, User> {
    override fun toResponse(entity: UserEntity) = entity.let {
        User.newBuilder()
            .setUserId(it.userId.v)
            .setDetail(
                UserDetail.newBuilder()
                    .setUserName(it.userName)
            ).setVersion(grpcVersionConverter.toResponse(it.version))
            .build()
    }

    override fun toEntity(request: User) = request.let {
        UserEntity(
            userId = UserId(it.userId),
            password = UserConstant.DEFAULT_PASSWORD,
            userName = it.detail?.userName,
            version = it.version.let { Version(it.version) }
        )
    }
}
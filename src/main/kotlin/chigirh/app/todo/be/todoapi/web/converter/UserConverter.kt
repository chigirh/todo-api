package chigirh.app.todo.be.todoapi.web.converter

import chigirh.app.todo.be.todoapi.domain.constant.UserConstant
import chigirh.app.todo.be.todoapi.domain.model.user.UserEntity
import chigirh.app.todo.be.todoapi.domain.model.vo.UserId
import chigirh.app.todo.be.todoapi.domain.model.vo.Version
import chigirh.app.todo.be.todoapi.oas3.model.User
import chigirh.app.todo.be.todoapi.oas3.model.UserDetail
import org.springframework.stereotype.Component

@Component
class UserConverter : Converter<UserEntity, User, User> {
    override fun toResponse(entity: UserEntity) = entity.let {
        User(
            userId = it.userId.v,
            detail = UserDetail(
                userName = it.userName ?: ""
            ),
            version = it.version.v
        )
    }

    override fun toEntity(request: User) = request.let {
        UserEntity(
            userId = UserId(it.userId),
            password = UserConstant.DEFAULT_PASSWORD,
            userName = it.detail?.userName,
            version = it.version.let { Version(it) }
        )
    }
}
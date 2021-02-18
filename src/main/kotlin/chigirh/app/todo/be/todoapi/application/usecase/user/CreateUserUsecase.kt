package chigirh.app.todo.be.todoapi.application.usecase.user

import chigirh.app.todo.be.todoapi.application.repository.user.UserRepository
import chigirh.app.todo.be.todoapi.application.usecase.Usecase
import chigirh.app.todo.be.todoapi.domain.model.user.UserEntity
import chigirh.app.todo.be.todoapi.domain.model.vo.UserId
import org.springframework.stereotype.Component

@Component
class CreateUserUsecase(
    val userRepository: UserRepository
) : Usecase() {

    operator fun invoke(userName: String, password: String) {
        val userId = UserId("U${String.format("%05d", userRepository.findMaxId() + 1)}")
        val user = UserEntity(
            userId = userId,
            password = password,
            userName = userName
        )
        user.create(userId)
        userRepository.insertBy(user)
    }
}
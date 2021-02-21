package chigirh.app.todo.be.todoapi.web.grpc.converter

import chigirh.app.todo.be.todoapi.domain.model.vo.UserId
import chigirh.app.todo.be.todoapi.grpc.model.XUserId
import chigirh.app.todo.be.todoapi.web.converter.Converter
import org.springframework.stereotype.Component

@Component
class GrpcXUserIdConverter : Converter<UserId, XUserId, XUserId> {
    override fun toEntity(request: XUserId) =
        UserId(request.xUserId);
}
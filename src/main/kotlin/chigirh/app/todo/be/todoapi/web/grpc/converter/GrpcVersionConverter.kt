package chigirh.app.todo.be.todoapi.web.grpc.converter

import chigirh.app.todo.be.todoapi.web.converter.Converter
import org.springframework.stereotype.Component
import chigirh.app.todo.be.todoapi.domain.model.vo.Version as VersionEntity
import chigirh.app.todo.be.todoapi.grpc.model.Version as GrpcVersion

@Component
class GrpcVersionConverter : Converter<VersionEntity, GrpcVersion, GrpcVersion> {
    override fun toResponse(entity: VersionEntity) =
        GrpcVersion.newBuilder()
            .setVersion(entity.v)
            .build()

    override fun toEntity(request: GrpcVersion) =
        VersionEntity(request.version);
}
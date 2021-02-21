package chigirh.app.todo.be.todoapi.web.grpc.converter

import chigirh.app.todo.be.todoapi.web.converter.Converter
import com.google.protobuf.Timestamp
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import org.springframework.stereotype.Component

@Component
class GrpcTimestampConverter : Converter<LocalDateTime, Timestamp, Timestamp> {
    override fun toResponse(entity: LocalDateTime) = entity?.let {
        Timestamp.newBuilder()
            .setSeconds(
                it.atZone(
                    ZoneId.systemDefault()
                ).toInstant().toEpochMilli()
            ).build()
    }

    override fun toEntity(request: Timestamp) = request.let {
        Instant.ofEpochMilli(request.seconds)
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime()
    }
}
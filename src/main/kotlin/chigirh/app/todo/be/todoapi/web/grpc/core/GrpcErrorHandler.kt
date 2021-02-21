package chigirh.app.todo.be.todoapi.web.grpc.core

import chigirh.app.todo.be.todoapi.domain.exception.*
import io.grpc.*
import org.springframework.stereotype.Component

@Component
class GrpcErrorHandler : ServerInterceptor {
    override fun <ReqT : Any?, RespT : Any?> interceptCall(
        call: ServerCall<ReqT, RespT>?, headers: Metadata?,
        next: ServerCallHandler<ReqT, RespT>?
    ): ServerCall.Listener<ReqT> {
        return object :
            ForwardingServerCallListener.SimpleForwardingServerCallListener<ReqT>(
                next?.startCall(call, headers)!!
            ) {
            override fun onHalfClose() {
                try {
                    super.onHalfClose()
                } catch (ex: RuntimeException) {
                    handleException(call, headers, ex)
                    throw ex
                }
            }

            override fun onReady() {
                try {
                    super.onReady()
                } catch (ex: RuntimeException) {
                    handleException(call, headers, ex)
                    throw ex
                }

            }
        }
    }

    private fun <ReqT, RespT> handleException(call: ServerCall<ReqT, RespT>?, headers: Metadata?, ex: Exception) {
        ex.printStackTrace()
        when (ex) {
            is NotFoundException -> call?.close(
                Status.fromCode(Status.NOT_FOUND.code).withDescription(ex.message), headers
            )
            is ConflictException -> call?.close(
                Status.fromCode(Status.ABORTED.code).withDescription(ex.message), headers
            )
            is InternalException -> call?.close(
                Status.fromCode(Status.INTERNAL.code).withDescription(ex.message), headers
            )
            is InvalidArgumentException -> call?.close(
                Status.fromCode(Status.INVALID_ARGUMENT.code).withDescription(ex.message), headers
            )
            is NotImplementedException -> call?.close(
                Status.fromCode(Status.UNIMPLEMENTED.code).withDescription(ex.message), headers
            )
            else -> call?.close(Status.fromCode(Status.INTERNAL.code).withDescription(ex.message), headers)
        }
    }
}
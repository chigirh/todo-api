package chigirh.app.todo.be.todoapi.web.api.core

import chigirh.app.todo.be.todoapi.domain.exception.*
import chigirh.app.todo.be.todoapi.oas3.model.ErrorDetail
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class ApiErrorHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(exception: NotFoundException): ResponseEntity<chigirh.app.todo.be.todoapi.oas3.model.Error> {
        val errorResponse = chigirh.app.todo.be.todoapi.oas3.model.Error(
            errorCode = HttpStatus.NOT_FOUND.toString(),
            details = listOf(
                ErrorDetail(
                    target = exception.target,
                    description = exception.message
                )
            )
        )
        exception.printStackTrace()
        return ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(ConflictException::class)
    fun handleConflictException(exception: ConflictException): ResponseEntity<chigirh.app.todo.be.todoapi.oas3.model.Error> {
        val errorResponse = chigirh.app.todo.be.todoapi.oas3.model.Error(
            errorCode = HttpStatus.CONFLICT.toString(),
            details = listOf(
                ErrorDetail(
                    target = exception.target,
                    description = exception.message
                )
            )
        )
        exception.printStackTrace()
        return ResponseEntity(errorResponse, HttpStatus.CONFLICT)
    }

    @ExceptionHandler(InternalException::class)
    fun handleConflictException(exception: InternalException): ResponseEntity<chigirh.app.todo.be.todoapi.oas3.model.Error> {
        val errorResponse = chigirh.app.todo.be.todoapi.oas3.model.Error(
            errorCode = HttpStatus.INTERNAL_SERVER_ERROR.toString(),
            details = listOf(
                ErrorDetail(
                    target = exception.target,
                    description = exception.message
                )
            )
        )
        exception.printStackTrace()
        return ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(InvalidArgumentException::class)
    fun handleConflictException(exception: InvalidArgumentException): ResponseEntity<chigirh.app.todo.be.todoapi.oas3.model.Error> {
        val errorResponse = chigirh.app.todo.be.todoapi.oas3.model.Error(
            errorCode = HttpStatus.BAD_REQUEST.toString(),
            details = listOf(
                ErrorDetail(
                    target = exception.target,
                    description = exception.message
                )
            )
        )
        exception.printStackTrace()
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(Exception::class)
    fun handleConflictException(exception: Exception): ResponseEntity<chigirh.app.todo.be.todoapi.oas3.model.Error> {
        val errorResponse = chigirh.app.todo.be.todoapi.oas3.model.Error(
            errorCode = HttpStatus.INTERNAL_SERVER_ERROR.toString(),
            details = listOf(
                ErrorDetail(
                    target = "UNKNOWN ERROR",
                    description = ""
                )
            )
        )
        exception.printStackTrace()
        return ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(NotImplementedException::class)
    fun handleConflictException(exception: NotImplementedException): ResponseEntity<chigirh.app.todo.be.todoapi.oas3.model.Error> {
        val errorResponse = chigirh.app.todo.be.todoapi.oas3.model.Error(
            errorCode = HttpStatus.NOT_IMPLEMENTED.toString(),
            details = listOf(
                ErrorDetail(
                    target = "UNKNOWN ERROR",
                    description = ""
                )
            )
        )
        exception.printStackTrace()
        return ResponseEntity(errorResponse, HttpStatus.NOT_IMPLEMENTED)
    }

}
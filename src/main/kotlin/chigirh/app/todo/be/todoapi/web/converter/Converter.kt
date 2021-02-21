package chigirh.app.todo.be.todoapi.web.converter

import chigirh.app.todo.be.todoapi.domain.exception.NotImplementedException

/**
 * WEBとDOMAINオブジェクトの相互変換インタフェース.
 */
interface Converter<E, REQ, RES> {
    /**
     * ドメインオブジェクトをレスポンスに変換.
     */
    fun toResponse(entity: E): RES {
        throw NotImplementedException("Converter.toResponse(entity: E)")
    }

    /**
     * リクエストをドメインオブジェクトに変換.
     */
    fun toEntity(request: REQ): E {
        throw NotImplementedException("Converter.toEntity(request: REQ)")
    }
}
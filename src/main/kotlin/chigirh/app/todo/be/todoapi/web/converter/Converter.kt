package chigirh.app.todo.be.todoapi.web.converter

import chigirh.app.todo.be.todoapi.domain.model.common.EntityBase

/**
 * WEBとDOMAINオブジェクトの相互変換インタフェース.
 */
interface Converter<E : EntityBase, REQ, RES> {
    /**
     * ドメインオブジェクトをレスポンスに変換.
     */
    fun toResponse(entity: E): RES

    /**
     * リクエストをドメインオブジェクトに変換.
     */
    fun toEntity(request: REQ): E
}
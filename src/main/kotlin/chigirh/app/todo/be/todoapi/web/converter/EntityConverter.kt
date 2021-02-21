package chigirh.app.todo.be.todoapi.web.converter

import chigirh.app.todo.be.todoapi.domain.model.common.EntityBase

/**
 * WEBとDOMAINオブジェクトの相互変換インタフェース.
 */
interface EntityConverter<E : EntityBase, REQ, RES> : Converter<E, REQ, RES> {
}
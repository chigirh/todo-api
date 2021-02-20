package chigirh.app.todo.be.todoapi.web.api.helper

import chigirh.app.todo.be.todoapi.oas3.model.Pageable
import org.springframework.stereotype.Component

@Component
class PageableHelper {
    fun getNextPage(pageable: Pageable?, total: Int) = pageable?.let {
        Pageable(
            offset = (it.offset + it.limit),
            limit = it.limit,
            total = total
        )
    } ?: Pageable(offset = 0, limit = 10);

}
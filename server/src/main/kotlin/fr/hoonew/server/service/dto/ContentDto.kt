package fr.hoonew.server.service.dto

data class ContentDto (
    val id: Long? = null,
    var name: String? = null,
    var url: String? = null,
    var category : String? = null,
    var creatorId: Long? = null,
    var creatorName: String? = null
)
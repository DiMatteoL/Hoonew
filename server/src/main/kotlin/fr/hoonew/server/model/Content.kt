package fr.hoonew.server.model

import fr.hoonew.server.model.common.TemporalModel
import javax.persistence.*

@Entity
@Table(name = "content")
data class Content (
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    val id: Long? = null,
    @ManyToOne
    var creator: Creator? = null,
    var name: String? = null,
    var url: String? = null,
    var category : String? = null
) : TemporalModel()
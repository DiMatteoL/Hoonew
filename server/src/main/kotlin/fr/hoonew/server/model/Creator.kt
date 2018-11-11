package fr.hoonew.server.model

import fr.hoonew.server.model.common.TemporalModel
import javax.persistence.*

@Entity
@Table(name = "creator")
data class Creator (
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    val id: Long? = null,
    @OneToMany
    var content: List<Content>? = null,
    var name: String? = null
) : TemporalModel()
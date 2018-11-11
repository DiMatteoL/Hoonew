package fr.hoonew.server.model

import fr.hoonew.server.model.common.TemporalModel
import javax.persistence.*

@Entity
@Table(name = "content")
class Content : TemporalModel() {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private val id: Long? = null
}
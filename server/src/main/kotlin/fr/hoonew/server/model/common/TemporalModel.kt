package fr.hoonew.server.model.common

import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.annotation.CreatedDate
import java.io.Serializable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.util.*
import javax.persistence.*


@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
@JsonIgnoreProperties(value = ["createdAt", "updatedAt"], allowGetters = true)
abstract class TemporalModel : Serializable {
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private val createdAt: Date? = null

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    @LastModifiedDate
    private var updatedAt: Date? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is TemporalModel) return false

        if (createdAt != other.createdAt) return false
        if (updatedAt != other.updatedAt) return false

        return true
    }

    override fun hashCode(): Int {
        var result = createdAt?.hashCode() ?: 0
        result = 31 * result + (updatedAt?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "TemporalModel(createdAt=$createdAt, updatedAt=$updatedAt)"
    }
}
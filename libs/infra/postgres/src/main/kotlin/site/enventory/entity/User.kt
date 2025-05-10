package site.enventory.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import site.enventory.constant.Provider
import java.time.Instant
import java.util.UUID

@Table("users")
data class UserEntity(
    @Id val id: UUID? = null,

    val email: String,

    val provider: Provider,

    @Column("provider_id") val providerId: String,

    @CreatedDate @Column("created_at") val createdAt: Instant = Instant.now(),
    @LastModifiedDate @Column("updated_at") val updatedAt: Instant = Instant.now(),
)

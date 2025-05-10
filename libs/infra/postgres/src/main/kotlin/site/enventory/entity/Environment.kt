package site.enventory.entity

import com.github.f4b6a3.uuid.UuidCreator
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant
import java.util.UUID

@Table("environments")
data class EnvironmentEntity(
    @Id val id: UUID = UuidCreator.getTimeOrdered(),

    @Column("env_store_id") val envStoreId: UUID,

    val name: String,

    @CreatedDate @Column("created_at") val createdAt: Instant = Instant.now(),
    @LastModifiedDate @Column("updated_at") val updatedAt: Instant = Instant.now(),
)

package v1.event;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Data returned from the database
 */
@Entity
@Table(name = "event_by_user")
public class EventData {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "tenant")
    private Long tenant;
    @Column(name = "event_time")
    private LocalDateTime eventTime;
    @Column(name = "operation")
    private String operation;
    @Column(name = "user_role")
    private String userRole;
    @Column(name = "identifier")
    private Long identifier;
    @Column(name = "action")
    private String action;
    @Column(name = "entity")
    private String entity;
    @Column(name = "entity_id")
    private Long entityId;
    @Column(name = "session_id")
    private String sessionId;
    @Column(name = "details")
    private String details;

    public EventData() {
    }

    public EventData(Long id, Long userId, Long tenant, LocalDateTime eventTime, String operation, String userRole, Long identifier, String action, String entity, Long entityId, String sessionId, String details) {
        this.id = id;
        this.userId = userId;
        this.tenant = tenant;
        this.eventTime = eventTime;
        this.operation = operation;
        this.userRole = userRole;
        this.identifier = identifier;
        this.action = action;
        this.entity = entity;
        this.entityId = entityId;
        this.sessionId = sessionId;
        this.details = details;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTenant() {
        return tenant;
    }

    public void setTenant(Long tenant) {
        this.tenant = tenant;
    }

    public LocalDateTime getEventTime() {
        return eventTime;
    }

    public void setEventTime(LocalDateTime eventTime) {
        this.eventTime = eventTime;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public Long getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Long identifier) {
        this.identifier = identifier;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
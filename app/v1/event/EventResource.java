package v1.event;

import java.time.LocalDateTime;

/**
 * Resource for the API.  This is a presentation class for frontend work.
 */
public class EventResource {

    private Long id;
    private Long userId;
    private Long tenant;
    private LocalDateTime eventTime;
    private String operation;
    private String userRole;
    private Long identifier;
    private String action;
    private String entity;
    private Long entityId;
    private String sessionId;
    private String details;

    public EventResource() {
    }

    public EventResource(Long id, Long userId, Long tenant, LocalDateTime eventTime, String operation, String userRole, Long identifier, String action, String entity, Long entityId, String sessionId, String details) {
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

    @Override
    public String toString() {
        return "EventResource{" +
                "id=" + id +
                ", userId=" + userId +
                ", tenant=" + tenant +
                ", eventTime=" + eventTime +
                ", operation='" + operation + '\'' +
                ", userRole='" + userRole + '\'' +
                ", identifier=" + identifier +
                ", action='" + action + '\'' +
                ", entity='" + entity + '\'' +
                ", entityId=" + entityId +
                ", sessionId='" + sessionId + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}
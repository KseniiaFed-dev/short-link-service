package com.example.shortlink;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

class UserSessionManager {
    private final Map<UUID, Object> userSessions = new ConcurrentHashMap<>();
    public UUID getOrCreateUserSession() {
        UUID userUuid = UUID.randomUUID();
        userSessions.put(userUuid, new Object());
        System.out.println("Generated new user session " + userUuid);
        return userUuid;
    }
}
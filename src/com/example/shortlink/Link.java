package com.example.shortlink;

import java.time.LocalDateTime; import java.util.UUID;

class Link { private String longUrl; private String shortUrl; private UUID userUuid; private int clicks; private int maxClicks; private LocalDateTime creationTime; private int linkLifetimeDays; private boolean isActive = true;

    public Link(String longUrl, String shortUrl, UUID userUuid, int maxClicks, int linkLifetimeDays) {
        this.longUrl = longUrl;
        this.shortUrl = shortUrl;
        this.userUuid = userUuid;
        this.maxClicks = maxClicks;
        this.creationTime = LocalDateTime.now();
        this.linkLifetimeDays = linkLifetimeDays;
        this.clicks = 0;
    }


    public String getLongUrl() {
        return longUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }


    public UUID getUserUuid() {
        return userUuid;
    }
    public int getClicks() {
        return clicks;
    }
    public void setMaxClicks(int maxClicks) { this.maxClicks = maxClicks; }

    public void setLinkLifetimeDays(int linkLifetimeDays) { this.linkLifetimeDays = linkLifetimeDays; }

    public void incrementClicks() {
        if(maxClicks !=0 && clicks >= maxClicks) {
            isActive = false;
        }
        clicks++;
    }

    public boolean isActive() {
        if(linkLifetimeDays !=0){
            LocalDateTime expirationTime = creationTime.plusDays(linkLifetimeDays);
            if(LocalDateTime.now().isAfter(expirationTime)) {
                isActive = false;
            }
        }
        return isActive;
    }
}
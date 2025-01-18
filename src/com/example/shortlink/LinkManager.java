package com.example.shortlink;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LinkManager {
    private final Map<String, Link> links = new HashMap<>();
    private final AppConfig appConfig = new AppConfig();
    private final ShortLinkGenerator shortLinkGenerator = new ShortLinkGenerator();

    public String createLink(String longUrl, UUID userUuid, int maxClicks, int linkLifetimeDays) {
        if (linkLifetimeDays == 0) {
            linkLifetimeDays = appConfig.getMaxLinkLifetimeDays();
        }
        String shortUrl = shortLinkGenerator.generateShortLink();
        Link link = new Link(longUrl, shortUrl, userUuid, maxClicks, linkLifetimeDays);
        links.put(shortUrl, link);
        return shortUrl;
    }
    public String getLongUrl(String shortUrl) {
        Link link = links.get(shortUrl);
        if (link != null && link.isActive()) {
            link.incrementClicks();
            return link.getLongUrl();
        }
        return null;
    }
    public void updateLink(String shortUrl, int newMaxClicks, int newLinkLifetimeDays) {
        Link link = links.get(shortUrl);
        if (link != null) {
            if (newLinkLifetimeDays == 0) {
                newLinkLifetimeDays = appConfig.getMaxLinkLifetimeDays();
            }
            link.setMaxClicks(newMaxClicks);
            link.setLinkLifetimeDays(newLinkLifetimeDays);
        }
    }
}
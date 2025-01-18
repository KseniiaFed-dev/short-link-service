package com.example.shortlink;

import java.util.Random;

class ShortLinkGenerator {
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int LINK_LENGTH = 7;
    private final Random random = new Random();

    public String generateShortLink() {
        StringBuilder shortLink = new StringBuilder();
        for (int i = 0; i < LINK_LENGTH; i++) {
            shortLink.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return shortLink.toString();
    }
}

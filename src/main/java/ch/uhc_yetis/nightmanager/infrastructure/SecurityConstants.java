package ch.uhc_yetis.nightmanager.infrastructure;

public class SecurityConstants {
    public static final String SECRET = "f1cdf4631398925115be9f4df7e5d19d8bafea1d84803fa111b54a5c30e4a83f";
    public static final long EXPIRATION_TIME = 8_640_000_000l; // 100 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}

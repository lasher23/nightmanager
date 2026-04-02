package ch.uhc_yetis.nightmanager.infrastructure;

/**
 * Permission strings used throughout the application.
 *
 * Controllers use permission strings of the form "<entity>:<action>".
 * Roles are defined and seeded in {@link DefaultAdminInitializer}.
 */
public final class RoleConstants {

    // ── Category permissions ──────────────────────────────────────────────────
    public static final String CATEGORY_LIST   = "category:list";
    public static final String CATEGORY_GET    = "category:get";
    public static final String CATEGORY_CREATE = "category:create";

    // ── Chat permissions ──────────────────────────────────────────────────────
    public static final String CHAT_LIST   = "chat:list";
    public static final String CHAT_CREATE = "chat:create";

    // ── Game permissions ──────────────────────────────────────────────────────
    public static final String GAME_LIST   = "game:list";
    public static final String GAME_GET    = "game:get";
    public static final String GAME_UPDATE = "game:update";
    public static final String GAME_MANAGE = "game:manage";
    public static final String GAME_NOTIFY = "game:notify";

    // ── Generation permissions ────────────────────────────────────────────────
    public static final String GENERATION_CREATE = "generation:create";

    // ── Hall permissions ──────────────────────────────────────────────────────
    public static final String HALL_LIST = "hall:list";
    public static final String HALL_GET  = "hall:get";

    // ── Notification permissions ──────────────────────────────────────────────
    public static final String NOTIFICATION_LIST   = "notification:list";
    public static final String NOTIFICATION_CREATE = "notification:create";

    // ── Shot permissions ──────────────────────────────────────────────────────
    public static final String SHOT_LIST   = "shot:list";
    public static final String SHOT_UPDATE = "shot:update";

    // ── Team permissions ──────────────────────────────────────────────────────
    public static final String TEAM_LIST   = "team:list";
    public static final String TEAM_GET    = "team:get";
    public static final String TEAM_CREATE = "team:create";
    public static final String TEAM_UPDATE = "team:update";

    // ── User management permissions ───────────────────────────────────────────
    public static final String USER_LIST   = "user:list";
    public static final String USER_GET    = "user:get";
    public static final String USER_UPDATE = "user:update";

    // ── Role management permissions ───────────────────────────────────────────
    public static final String ROLE_LIST   = "role:list";
    public static final String ROLE_GET    = "role:get";
    public static final String ROLE_CREATE = "role:create";
    public static final String ROLE_UPDATE = "role:update";
    public static final String ROLE_DELETE = "role:delete";

    // ── OTP user permissions ──────────────────────────────────────────────────
    public static final String OTP_USER_LIST   = "otp_user:list";
    public static final String OTP_USER_GET    = "otp_user:get";
    public static final String OTP_USER_CREATE = "otp_user:create";
    public static final String OTP_USER_DELETE = "otp_user:delete";

    // ── Tournament permissions ────────────────────────────────────────────────
    public static final String TOURNAMENT_LIST   = "tournament:list";
    public static final String TOURNAMENT_GET    = "tournament:get";
    public static final String TOURNAMENT_CREATE = "tournament:create";
    public static final String TOURNAMENT_UPDATE = "tournament:update";
    public static final String TOURNAMENT_DELETE = "tournament:delete";

    private RoleConstants() {}
}


package ch.uhc_yetis.nightmanager.domain.model;

public enum TournamentState {
    /** Tournament is being set up, registrations not yet open. */
    DRAFT,
    /** Registrations are open. */
    REGISTRATION_OPEN,
    /** Registrations are closed, tournament is running. */
    IN_PROGRESS,
    /** Tournament has finished. */
    DONE
}

package ch.uhc_yetis.nightmanager.application.generation.yetiscup;

import ch.uhc_yetis.nightmanager.domain.model.Hall;
import ch.uhc_yetis.nightmanager.domain.model.Team;

import java.util.ArrayList;
import java.util.List;

public class TeamGenerationData {
    private List<Hall> hallsAlreadyPlayed;
    private boolean alreadyPlayedHome;
    private List<Team> playedAgainst = new ArrayList<>();

    public TeamGenerationData(List<Hall> hallsAlreadyPlayed, boolean alreadyPlayedHome, List<Team> playedAgainst) {
        this.hallsAlreadyPlayed = hallsAlreadyPlayed;
        this.alreadyPlayedHome = alreadyPlayedHome;
        this.playedAgainst = playedAgainst;
    }

    public boolean isAlreadyPlayedHome() {
        return this.alreadyPlayedHome;
    }

    public List<Hall> getHallsAlreadyPlayed() {
        return this.hallsAlreadyPlayed;
    }

    public List<Team> getPlayedAgainst() {
        return this.playedAgainst;
    }


}

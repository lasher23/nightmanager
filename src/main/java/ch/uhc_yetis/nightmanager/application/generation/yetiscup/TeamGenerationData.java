package ch.uhc_yetis.nightmanager.application.generation.yetiscup;

import ch.uhc_yetis.nightmanager.domain.model.Hall;
import ch.uhc_yetis.nightmanager.domain.model.Team;

import java.util.ArrayList;
import java.util.List;

public class TeamGenerationData {
    private List<Hall> hallsAlreadyPlayed;
    private List<Team> playedAgainst;

    public TeamGenerationData(List<Hall> hallsAlreadyPlayed, List<Team> playedAgainst) {
        this.hallsAlreadyPlayed = hallsAlreadyPlayed;
        this.playedAgainst = playedAgainst;
    }

    public List<Hall> getHallsAlreadyPlayed() {
        return this.hallsAlreadyPlayed;
    }

    public List<Team> getPlayedAgainst() {
        return this.playedAgainst;
    }


}

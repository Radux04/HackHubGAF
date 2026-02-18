package unicam.model.iscrizione.builder;

import unicam.model.hackathon.entity.Hackathon;
import unicam.model.iscrizione.Iscrizione;
import unicam.model.team.Team;
import unicam.model.utenti.user.User;

import java.util.List;

public class IscrizioneBuilder {

    Iscrizione i = new Iscrizione();

    public IscrizioneBuilder(){
        reset();
    }

    private IscrizioneBuilder reset() {
        i = new Iscrizione();
        return this;
    }

    public IscrizioneBuilder buildTeam(Team team){
        i.setTeam(team);
        return this;
    }

    public IscrizioneBuilder buildHackatho(Hackathon hackathon){
        i.setHt(hackathon);
        return this;
    }

    public Iscrizione build(){
        return this.i;
    }
}

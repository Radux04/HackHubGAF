package unicam.model.iscrizione.builder;

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

    public IscrizioneBuilder buildHTId(Long HTId){
        i.setHtId(HTId);
        return this;
    }

    public IscrizioneBuilder buildTeamId(Long teamId){
        i.setTeamId(teamId);
        return this;
    }

    public Iscrizione build(){
        return this.i;
    }
}

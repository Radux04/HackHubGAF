package unicam.builder;

import unicam.model.Hackathon;
import unicam.model.Iscrizione;
import unicam.model.Team;

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

package unicam.service;

import org.springframework.stereotype.Service;
import unicam.dto.hackathon.*;
import unicam.builder.HackathonBuilder;
import unicam.model.Hackathon;
import unicam.model.Segnalazione;
import unicam.model.Sottomissione;
import unicam.model.Team;
import unicam.repository.*;
import unicam.model.RichiestaSupporto;
import unicam.model.Staff;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HackathonService {
    private final HackathonRepository hackathonRepository;
    private final StaffRepository staffRepository;
    private final RichiestaSupportoRepository richiestaSupportoRepository;
    private final IscrizioneRepository iscrizioniRepository;
    private final TeamRepository teamRepository;
    private final SottomissioniRepository sottomissioniRepository;
    private final SegnalazioneRepository segnalazioneRepository;

    public HackathonService(HackathonRepository hackathonRepository
            ,StaffRepository staffRepository
            ,RichiestaSupportoRepository richiestaSupportoRepository
            ,IscrizioneRepository iscrizioniRepository
            ,TeamRepository teamRepository
            ,SottomissioniRepository sottomissioniRepository
            ,UserRepository userRepository
            ,SegnalazioneRepository segnalazioneRepository) {
        this.hackathonRepository = hackathonRepository;
        this.staffRepository = staffRepository;
        this.richiestaSupportoRepository = richiestaSupportoRepository;
        this.iscrizioniRepository = iscrizioniRepository;
        this.teamRepository = teamRepository;
        this.sottomissioniRepository = sottomissioniRepository;
        this.segnalazioneRepository = segnalazioneRepository;
    }

    public Hackathon CreaHackathon(HackathonRequest hackathonRequest) {

        DescrizioneHT descrizione = hackathonRequest.descrizione();
        PlacementHT placement = hackathonRequest.placement();
        StaffHT staff = hackathonRequest.staff();
        String nome = hackathonRequest.nome();
        Long idOrganizzatore = hackathonRequest.idOrganizzatore();

        Staff organizzatore = staffRepository.findById(idOrganizzatore)
                .orElseThrow(() -> new IllegalArgumentException("Organizzatore non trovato"));

        Staff giudice = staffRepository.findById(staff.idGiudice()).get();
        List<Staff> mentori = staff.mentori().stream().map(m -> staffRepository.findById(m).get()).toList();


        //se l'organizzatore è già occupato in un altro h
        if (organizzatore.isOccupato()) throw new IllegalArgumentException("Organizzatore occupato");
        //se l'organizzatore è libero
        else {
            //se il giudice è occupato
            if (giudice.isOccupato()) throw new IllegalArgumentException("Giudice occupato");
            //se il giudice è libero
            else {
                //cicla tutti i mentori
                for (Staff m : mentori) {
                    //se uno dei mentori è già occupato, lancia un'eccezione
                    if (m.isOccupato()) {
                        throw new IllegalArgumentException("un mentore è occupato");
                    }
                }

                //l'hackathon viene creato attraverso il builder
                HackathonBuilder hackathonBuilder = new HackathonBuilder();
                hackathonBuilder.buildName(nome)
                        .buildDataInizio(placement.dataInizio())
                        .buildDataFine(placement.dataFine())
                        .buildScadenzaIscrizioni(placement.scadenzaIscrizioni())
                        .buildGiudice(giudice)
                        .buildLuogo(placement.luogo())
                        .buildMaxSize(descrizione.maxSize())
                        .buildMentori(mentori)
                        .buildOrganizzatore(organizzatore)
                        .buildPremio(descrizione.premio())
                        .buildRegolamento(descrizione.regolamento());
                Hackathon hackathon = hackathonBuilder.build();

                hackathonRepository.save(hackathon);

                giudice.setOccupato(true);
                giudice.setHackathon(hackathon);
                for (Staff m : mentori) {
                    //setta lo stato del mentore a occupato
                    m.setOccupato(true);
                    //setta l'attributo hackathon del mentore come l'hackathon appena creato
                    m.setHackathon(hackathon);
                }

                organizzatore.setOccupato(true);
                organizzatore.setHackathon(hackathon);

                staffRepository.save(organizzatore);
                staffRepository.save(giudice);
                staffRepository.saveAll(mentori);

                return hackathon;
            }

        }
    }

    public RichiestaSupporto richiediSupporto(RichiestaSupportoDTO richiestaSupportoDTO) {
        Long hackathonId = iscrizioniRepository.findByTeamId(richiestaSupportoDTO.idTeam())
                .map(i -> i.getHt().getId())
                .orElse(null);

        Hackathon hackathon = hackathonRepository.findById(hackathonId).get();//.orElseThrow(() -> new IllegalArgumentException("Hackathon non trovato"));
        Team team = teamRepository.findById(richiestaSupportoDTO.idTeam()).get();//.orElseThrow(() -> new IllegalArgumentException("Team non trovato"));
        RichiestaSupporto richiesta = new RichiestaSupporto(team, richiestaSupportoDTO.descrizione(), hackathon);
        return richiestaSupportoRepository.save(richiesta);
    }


    public List<RichiestaSupporto> visualizzaRichiesteSupporto(Long idHackathon) {
        List<RichiestaSupporto> richieste = new ArrayList<>();
        Hackathon hackathon = hackathonRepository.findById(idHackathon).get();
        for (RichiestaSupporto richiestaSupporto : richiestaSupportoRepository.findByHackathon(hackathon)) {
            if(richiestaSupporto.getHackathon().getId().equals(idHackathon)) {
                richieste.add(richiestaSupporto);
            }
        }
        return richieste;
    }


    public void creaSottomissione(CreaSottomissioneDTO creaSottomissioneDTO) {
        if(hackathonRepository.findById(creaSottomissioneDTO.idHackaton()).get().getSottomissioni().size() == 10)
            throw new IllegalArgumentException("Limite massimo di sottomissioni raggiunto");

        Sottomissione sottomissione = new Sottomissione(creaSottomissioneDTO.titolo(), creaSottomissioneDTO.descrizione());
        sottomissioniRepository.save(sottomissione);
        hackathonRepository.findById(creaSottomissioneDTO.idHackaton()).get().getSottomissioni().add(sottomissione);
    }



    public Hackathon aggiungiMentore(AggiungiMentoreDTO aggiungiMentoreDTO) {
        staffRepository.findById(aggiungiMentoreDTO.idMentore()).get().setOccupato(true);
        hackathonRepository.findById(aggiungiMentoreDTO.idHackaton()).get().getMentori().add(staffRepository.findById(aggiungiMentoreDTO.idMentore()).get());
        return hackathonRepository.findById(aggiungiMentoreDTO.idHackaton()).get();
    }


    public boolean segnalaTeam(SegnalaTeamDTO  segnalaTeamDTO){

        Optional<Team> team = teamRepository.findById(segnalaTeamDTO.teamId());
        Optional<Hackathon> hackathon = hackathonRepository.findById(segnalaTeamDTO.hackathonId());
        Optional<Staff> mentore = staffRepository.findById(segnalaTeamDTO.mentoreId());

        //crea la segnalazione
        Segnalazione s = new Segnalazione(team.get(), hackathon.get(), mentore.get(), segnalaTeamDTO.descrizione());
        //salva la segnalazione nella repository
        segnalazioneRepository.save(s);

        return true;

    }
}
package unicam.service;

import org.springframework.stereotype.Service;
import unicam.dto.hackathon.*;
import unicam.model.hackathon.builder.HackathonBuilder;
import unicam.model.hackathon.entity.*;
import unicam.model.team.Team;
import unicam.repository.*;
import unicam.model.supporto.RichiestaSupporto;
import unicam.model.utenti.staff.Staff;

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
    private final UserRepository userRepository;

    public HackathonService(HackathonRepository hackathonRepository
            ,StaffRepository staffRepository
            ,RichiestaSupportoRepository richiestaSupportoRepository
            ,IscrizioneRepository iscrizioniRepository
            ,TeamRepository teamRepository
            ,SottomissioniRepository sottomissioniRepository
            ,UserRepository userRepository) {
        this.hackathonRepository = hackathonRepository;
        this.staffRepository = staffRepository;
        this.richiestaSupportoRepository = richiestaSupportoRepository;
        this.iscrizioniRepository = iscrizioniRepository;
        this.teamRepository = teamRepository;
        this.sottomissioniRepository = sottomissioniRepository;
        this.userRepository = userRepository;
    }

    public Hackathon CreaHackathon(HackathonRequest hackathonRequest) {

        DescrizioneHT descrizione = hackathonRequest.descrizione();
        PlacementHT placement = hackathonRequest.placement();
        StaffHT staff = hackathonRequest.staff();
        String nome = hackathonRequest.nome();
        Long idOrganizzatore = hackathonRequest.idOrganizzatore();

        Staff organizzatore = staffRepository.findById(idOrganizzatore).get();
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


                giudice.setOccupato(true);
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

                return hackathonRepository.save(hackathon);
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

    public boolean segnalaTeam(Long teamId, Long hackathonId, Long mentoreId, String descrizione){

        Optional<Team> team = teamRepository.findById(teamId);
        Optional<Hackathon> hackathon = hackathonRepository.findById(hackathonId);
        Optional<Staff> mentore = userRepository.findById(mentoreId);

        Segnalazione s = new Segnalazione(team.get(), hackathon.get(), mentore.get(), descrizione);

        return true;

    }
}
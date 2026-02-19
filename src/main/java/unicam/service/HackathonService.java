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

@Service
public class HackathonService {
    private final HackathonRepository hackathonRepository;
    private final StaffRepository staffRepository;
    private final RichiestaSupportoRepository richiestaSupportoRepository;
    private final IscrizioneRepository iscrizioniRepository;
    private final TeamRepository teamRepository;
    private final SottomissioniRepository sottomissioniRepository;

    public HackathonService(HackathonRepository hackathonRepository
            ,StaffRepository staffRepository
            ,RichiestaSupportoRepository richiestaSupportoRepository
            ,IscrizioneRepository iscrizioniRepository
            ,TeamRepository teamRepository
            ,SottomissioniRepository sottomissioniRepository) {
        this.hackathonRepository = hackathonRepository;
        this.staffRepository = staffRepository;
        this.richiestaSupportoRepository = richiestaSupportoRepository;
        this.iscrizioniRepository = iscrizioniRepository;
        this.teamRepository = teamRepository;
        this.sottomissioniRepository = sottomissioniRepository;
    }

    public Hackathon CreaHackathon(HackathonRequest hackathonRequest) {

        DescrizioneHT descrizione = hackathonRequest.getDescrizione();
        PlacementHT placement = hackathonRequest.getPlacement();
        StaffHT staff = hackathonRequest.getStaff();
        String nome = hackathonRequest.getNome();
        Long idOrganizzatore = hackathonRequest.getIdOrganizzatore();

        Staff organizzatore = staffRepository.findById(idOrganizzatore).get();
        Staff giudice = staffRepository.findById(staff.getIdGiudice()).get();
        List<Staff> mentori = staff.getMentori().stream().map(m -> staffRepository.findById(m).get()).toList();


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
                        .buildDataInizio(placement.getDataInizio())
                        .buildDataFine(placement.getDataFine())
                        .buildScadenzaIscrizioni(placement.getScadenzaIscrizioni())
                        .buildGiudice(giudice)
                        .buildLuogo(placement.getLuogo())
                        .buildMaxSize(descrizione.getMaxSize())
                        .buildMentori(mentori)
                        .buildOrganizzatore(organizzatore)
                        .buildPremio(descrizione.getPremio())
                        .buildRegolamento(descrizione.getRegolamento());
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
        Long hackathonId = iscrizioniRepository.findByTeamId(richiestaSupportoDTO.getIdTeam())
                .map(i -> i.getHt().getId())
                .orElse(null);

        Hackathon hackathon = hackathonRepository.findById(hackathonId).get();//.orElseThrow(() -> new IllegalArgumentException("Hackathon non trovato"));
        Team team = teamRepository.findById(richiestaSupportoDTO.getIdTeam()).get();//.orElseThrow(() -> new IllegalArgumentException("Team non trovato"));
        RichiestaSupporto richiesta = new RichiestaSupporto(team, richiestaSupportoDTO.getDescrizione(), hackathon);
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
        if(hackathonRepository.findById(creaSottomissioneDTO.getIdHackaton()).get().getSottomissioni().size() == 10)
            throw new IllegalArgumentException("Limite massimo di sottomissioni raggiunto");

        Sottomissione sottomissione = new Sottomissione(creaSottomissioneDTO.getTitolo(), creaSottomissioneDTO.getDescrizione());
        sottomissioniRepository.save(sottomissione);
        hackathonRepository.findById(creaSottomissioneDTO.getIdHackaton()).get().getSottomissioni().add(sottomissione);
    }



    public Hackathon aggiungiMentore(AggiungiMentoreDTO aggiungiMentoreDTO)
    {
        hackathonRepository.findById(aggiungiMentoreDTO.getIdHackaton()).get().getMentori().add(staffRepository.findById(aggiungiMentoreDTO.getIdMentore()).get());
        Hackathon hackathon = hackathonRepository.findById(aggiungiMentoreDTO.getIdHackaton()).get();
        return hackathon;


    }
}
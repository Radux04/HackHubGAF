# HackHub

Backend REST per la gestione di hackathon: creazione degli eventi, team e inviti, iscrizioni, sottomissioni e consegne, richieste di supporto ai mentori, segnalazioni e integrazione con Google Calendar.

Progetto di gruppo sviluppato all'Università di Camerino (UNICAM).

## Stack

- Java 21, Spring Boot 3.2.5 (Web, Data JPA)
- Database H2 su file, con console web su `/h2-console`
- Lombok
- Google Calendar (OAuth 2.0) e ical4j
- Maven

## Struttura

| Package | Contenuto |
|---|---|
| `model` | Entità di dominio: hackathon, team, utenti e staff, iscrizioni, inviti, sottomissioni, consegne, segnalazioni, richieste di supporto |
| `repository` | Repository Spring Data JPA |
| `service` | Logica applicativa, inclusa l'integrazione con Google Calendar dietro una facade (`CalendarFacade`) |
| `controller` | Endpoint REST |
| `dto` | Oggetti di richiesta e risposta |
| `builder` | Builder per hackathon, team e iscrizioni |

Il file `ProgettoIDSRFG.vpp` contiene i diagrammi UML di progetto (Visual Paradigm).

## Endpoint principali

| Risorsa | Endpoint |
|---|---|
| Hackathon | `POST /hackathon/crea`, `POST /hackathon/creaSottomissione`, `POST /hackathon/aggiungiMentore`, `POST /hackathon/segnalaTeam`, `POST /hackathon/richiesteSupporto`, `GET /hackathon/{id}/richiesteSupporto` |
| Team | `POST /team/creaTeam`, `POST /team/invita`, `DELETE /team/{membroId}`, `PUT /team/coordinatore/{membroId}` |
| Iscrizioni | `POST /iscrizioneTeam`, `DELETE /iscrizioneTeam` |
| Consegne | `POST /consegna`, `DELETE /consegna/{idConsegna}` |
| Utenti | `POST /user/risposta` |
| Google Calendar | `GET /google/auth`, `GET /google/callback`, `POST /google/calls` |

## Come eseguire

Prerequisiti: JDK 21 e Maven.

1. Crea il file `.env` partendo dall'esempio e inserisci le tue credenziali OAuth di Google Cloud:

   ```bash
   cp .env.example .env
   ```

2. Avvia l'applicazione:

   ```bash
   mvn spring-boot:run
   ```

L'API risponde su `http://localhost:8080`.

## Team

- Alessandro Raducu ([@Radux04](https://github.com/Radux04))
- Giovanni Cardinali
- Francesco Torchia

Dalla cronologia dei commit, Alessandro ha lavorato soprattutto su servizi e controller di hackathon, team e iscrizioni e sull'integrazione con Google Calendar. Giovanni soprattutto su controller di team, utenti e consegne e sulle segnalazioni. Francesco sulla gestione dei mentori.

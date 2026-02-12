package unicam.repository;

import unicam.model.inviti.Invito;

public interface InvitiRepository {
    Invito save(Invito invito);
    Invito findById(long id);
}

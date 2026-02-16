package unicam.repository;

import unicam.model.hackathon.entity.Sottomissione;

import java.util.HashMap;
import java.util.Map;

public class InMemorySottomissioniRepo implements SottomissioniRepository {
    private Map<Integer, Sottomissione> sottomissioniByid;


    public InMemorySottomissioniRepo() {
        sottomissioniByid = new HashMap<>();
    }

    @Override
    public Sottomissione findbyid(int id) {
        return sottomissioniByid.get(id);
    }

}

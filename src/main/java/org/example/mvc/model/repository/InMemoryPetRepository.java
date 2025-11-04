package org.example.mvc.model.repository;

import java.util.ArrayList;
import java.util.List;
import org.example.mvc.model.entity.Pet;

public class InMemoryPetRepository implements PetRepository {

  private final List<Pet> petList = new ArrayList<>();

  public InMemoryPetRepository() {
    petList.add(new Pet("누렁이", 5, "강아지"));
    petList.add(new Pet("야옹이", 2, "고양이"));
    petList.add(new Pet("짹짹이", 1, "새"));
    petList.add(new Pet("개굴이", 3, "개구리"));
    petList.add(new Pet("어흥이", 10, "호랑이"));
  }

  @Override
  public List<Pet> findAll() {
    return petList;
  }

  @Override
  public void save(Pet pet) {
    petList.add(pet);
  }
}

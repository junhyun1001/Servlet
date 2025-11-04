package org.example.mvc.Service;

import java.util.List;
import org.example.mvc.model.entity.Pet;
import org.example.mvc.model.repository.InMemoryPetRepository;
import org.example.mvc.model.repository.PetRepository;
import org.example.mvc.model.repository.SupabasePetRepository;

public class PetServiceImpl implements PetService {

//  private final PetRepository repository = new InMemoryPetRepository();
  private final PetRepository repository = new SupabasePetRepository();

  @Override
  public List<Pet> readAll() {
    return repository.findAll();
  }

  @Override
  public void create(String name, int age, String category) {
    Pet pet = new Pet(name, age, category);
    repository.save(pet);
  }
}

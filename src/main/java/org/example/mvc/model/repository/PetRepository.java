package org.example.mvc.model.repository;

import java.util.List;
import org.example.mvc.model.entity.Pet;

public interface PetRepository {

  List<Pet> findAll();

  void save(Pet pet);

}

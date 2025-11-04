package org.example.mvc.Service;

import java.util.List;
import org.example.mvc.model.entity.Pet;

public interface PetService {

  List<Pet> readAll();

  void create(String name, int age, String category);
}

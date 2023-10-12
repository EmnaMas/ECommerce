

package com.example.ECommerce.Repositories;

import com.example.ECommerce.Models.Produit;
import org.springframework.data.repository.CrudRepository;

public interface ProduitRepository extends CrudRepository<Produit, Long> {
    // Vous pouvez ajouter des méthodes de recherche personnalisées si nécessaire
}


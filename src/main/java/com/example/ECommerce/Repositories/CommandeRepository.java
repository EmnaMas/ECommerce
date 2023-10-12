package com.example.ECommerce.Repositories;

import com.example.ECommerce.Models.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeRepository extends JpaRepository<Commande, Long> {
    // Vous pouvez ajouter des méthodes de requête personnalisées si nécessaire
}

package com.example.ECommerce.Services;

import com.example.ECommerce.Models.Client;
import com.example.ECommerce.Models.Commande;
import com.example.ECommerce.Models.Produit;

import java.util.List;

public interface CommandeService {
    Commande passerCommande(Client client, List<Long> produitIds);
    // Ajoutez d'autres méthodes de service si nécessaire
}


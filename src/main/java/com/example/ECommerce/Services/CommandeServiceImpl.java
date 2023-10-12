package com.example.ECommerce.Services;

import com.example.ECommerce.Models.Client;
import com.example.ECommerce.Models.Commande;
import com.example.ECommerce.Models.Produit;
import com.example.ECommerce.Repositories.ClientRepository;
import com.example.ECommerce.Repositories.CommandeRepository;
import com.example.ECommerce.Repositories.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommandeServiceImpl implements CommandeService {

    private final CommandeRepository commandeRepository;
    private final ProduitRepository produitRepository;

    @Autowired
    public CommandeServiceImpl(
            CommandeRepository commandeRepository,
            ProduitRepository produitRepository
    ) {
        this.commandeRepository = commandeRepository;
        this.produitRepository = produitRepository;
    }


    @Override
    public Commande passerCommande(Client client, List<Long> produitIds) {
        try {
            // 2. Rechercher les produits par leurs IDs
            List<Produit> produits = new ArrayList<>();
            for (Produit produit : produitRepository.findAllById(produitIds)) {
                produits.add(produit);
            }

            // 3. Créer une nouvelle commande
            Commande nouvelleCommande = new Commande();
            nouvelleCommande.setClient(client);
            nouvelleCommande.setProduits(produits);
            nouvelleCommande.setRef("Référence de commande générée");
            nouvelleCommande.setDate(new Date());
            nouvelleCommande.setEtat("En cours"); // Vous pouvez définir l'état initial

            // 4. Enregistrer la nouvelle commande dans la base de données
            Commande commandeEnregistree = commandeRepository.save(nouvelleCommande);

            // 5. Vous pouvez ajouter d'autres logiques ici, telles que la mise à jour du stock de produits, etc.

            return commandeEnregistree;
        } catch (Exception e) {
            // Gérer d'autres exceptions non anticipées
            // Vous pouvez renvoyer une réponse d'erreur appropriée ici
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Une erreur s'est produite lors de la création de la commande", e);
        }
    }



}

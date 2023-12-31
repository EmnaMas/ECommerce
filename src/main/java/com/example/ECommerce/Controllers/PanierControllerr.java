package com.example.ECommerce.Controllers;


import com.example.ECommerce.Models.Client;
import com.example.ECommerce.Models.Commande;
import com.example.ECommerce.Services.ClientService;
import com.example.ECommerce.Services.CommandeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/panier")
public class PanierControllerr {


    private final CommandeService commandeService;
    private final ClientService clientService;

    @Autowired
    public PanierControllerr(CommandeService commandeService, ClientService clientService) {
        this.commandeService = commandeService;
        this.clientService = clientService;
    }



    @PostMapping("/ajouter-produit")
    public ResponseEntity<String> ajouterProduitAuPanier(
            @RequestParam Long produitId,
            @RequestParam int quantite,
            HttpSession session
    ) {
        // Récupérer le panier depuis la session
        Map<Long, Integer> panier = (Map<Long, Integer>) session.getAttribute("panier");

        // Si le panier n'existe pas en session, créez-le
        if (panier == null) {
            panier = new HashMap<>();
        }

        // Ajouter le produit au panier
        panier.put(produitId, panier.getOrDefault(produitId, 0) + quantite);

        // Mettre à jour le panier en session
        session.setAttribute("panier", panier);

        return ResponseEntity.ok("Produit ajouté au panier avec succès.");
    }



    @GetMapping("/afficher-panier")
    public ResponseEntity<Map<Long, Integer>> afficherPanier(HttpSession session) {
        // Récupérer le panier depuis la session
        Map<Long, Integer> panier = (Map<Long, Integer>) session.getAttribute("panier");

        if (panier == null) {
            return ResponseEntity.ok(new HashMap<>()); // Retourner un panier vide si le panier n'existe pas
        }

        return ResponseEntity.ok(panier);
    }












    @PostMapping("/passer-commande")
    public ResponseEntity<String> passerCommande(HttpSession session, @RequestParam Long clientId) {
        // Récupérez le panier depuis la session
        Map<Long, Integer> panier = (Map<Long, Integer>) session.getAttribute("panier");

        if (panier == null || panier.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le panier est vide. Ajoutez des produits au panier avant de passer une commande.");
        }

        // Récupérez le client en fonction de clientId (vous devez implémenter cette logique)
        Client client = clientService.getClientById(clientId);

        if (client == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le client avec l'ID " + clientId + " n'a pas été trouvé.");

        }

        // Convertissez le contenu du panier en listes de produits et quantités
        List<Long> produitIds = new ArrayList<>(panier.keySet());
        List<Integer> quantiteCommandeeParProduit = new ArrayList<>(panier.values());

        // Ici, vous pouvez implémenter la logique pour créer une commande à partir du contenu du panier
        Commande commande = commandeService.passerCommande(client, produitIds, quantiteCommandeeParProduit);

        // Une fois la commande passée, videz le panier
        panier.clear();
        session.setAttribute("panier", panier);

        return ResponseEntity.ok("Commande passée avec succès. Numéro de commande : " + commande.getId());
    }















}

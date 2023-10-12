package com.example.ECommerce.Controllers;

import com.example.ECommerce.Models.Client;
import com.example.ECommerce.Models.Commande;
import com.example.ECommerce.Services.ClientService;
import com.example.ECommerce.Services.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/commandes")
public class CommandeController {

    private final CommandeService commandeService;
    private final ClientService clientService;

    @Autowired
    public CommandeController(CommandeService commandeService, ClientService clientService) {
        this.commandeService = commandeService;
        this.clientService = clientService;
    }

    @PostMapping("/passer-commande")
    public Commande passerCommande(
            @RequestParam Long clientId,
            @RequestParam List<Long> produitIds,
            @RequestParam List<Integer> quantiteCommandeeParProduit
    ) {
        // Utilisez le service client pour rechercher le client par ID
        Client client = clientService.getClientById(clientId);

        return commandeService.passerCommande(client, produitIds, quantiteCommandeeParProduit);
    }



}

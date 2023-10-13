/*
package com.example.ECommerce.Controllers;

import com.example.ECommerce.Models.Panier;
import com.example.ECommerce.Models.Produit;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ajouter-au-panier")
public class PanierController {

    @PostMapping
    public String ajouterAuPanier(
            @RequestParam Long produitId,
            @RequestParam Integer quantite,
            HttpSession session
    ) {
        // Récupérer le panier de la session ou créer un nouveau panier
        Panier panier = (Panier) session.getAttribute("panier");
        if (panier == null) {
            panier = new Panier();
        }

        // Vous pouvez obtenir le produit à ajouter directement ici
        Produit produit = new Produit(); // Créez le produit selon vos besoins

        // Ajouter le produit au panier avec la quantité spécifiée
        panier.ajouterProduit(produit, quantite);

        // Mettre à jour le panier dans la session
        session.setAttribute("panier", panier);

        return "Produit ajouté au panier";
    }

    @GetMapping("/afficher-panier")
    public ResponseEntity<Panier> afficherPanier(HttpSession session) {
        // Récupérer le panier de la session
        Panier panier = (Panier) session.getAttribute("panier");

        if (panier == null) {
            panier = new Panier();
        }

        return ResponseEntity.ok(panier);
    }

}
*/
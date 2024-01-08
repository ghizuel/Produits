package com.votreentreprise;

import java.util.ArrayList;
import java.util.List;

class ProduitService {
    private List<Produit> produits = new ArrayList<>();

    // Opération CRUD - Create
    public void ajouterProduit(Produit produit) {
        // Vérification de l'unicité du produit
        if (!produitExiste(produit.getId()) && !nomProduitExiste(produit.getNom())) {
            // Validation des données
            if (produit.getPrix() >= 0 && produit.getQuantite() >= 0) {
                produits.add(produit);
                System.out.println("Produit ajouté avec succès.");
            } else {
                System.out.println("Erreur : Le prix et la quantité doivent être positifs.");
            }
        } else {
            System.out.println("Erreur : Un produit avec le même ID ou nom existe déjà.");
        }
    }

    // Opération CRUD - Read
    public Produit consulterProduit(Long id) {
        for (Produit produit : produits) {
            if (produit.getId().equals(id)) {
                return produit;
            }
        }
        System.out.println("Aucun produit trouvé avec l'ID " + id);
        return null;
    }

    // Opération CRUD - Update
    public void mettreAJourProduit(Produit produit) {
        if (produitExiste(produit.getId())) {
            // Validation des données
            if (produit.getPrix() >= 0 && produit.getQuantite() >= 0) {
                // Mise à jour du produit
                int index = trouverIndexProduit(produit.getId());
                produits.set(index, produit);
                System.out.println("Produit mis à jour avec succès.");
            } else {
                System.out.println("Erreur : Le prix et la quantité doivent être positifs.");
            }
        } else {
            System.out.println("Erreur : Aucun produit trouvé avec cet ID pour la mise à jour.");
        }
    }

    // Opération CRUD - Delete
    public void supprimerProduit(Long id) {
        if (produitExiste(id)) {
            produits.removeIf(produit -> produit.getId().equals(id));
            System.out.println("Produit supprimé avec succès.");
        } else {
            System.out.println("Erreur : Aucun produit trouvé avec cet ID pour la suppression.");
        }
    }

    // Vérifier l'existence d'un produit par ID
    private boolean produitExiste(Long id) {
        return produits.stream().anyMatch(produit -> produit.getId().equals(id));
    }

    // Vérifier l'existence d'un produit par nom
    private boolean nomProduitExiste(String nom) {
        return produits.stream().anyMatch(produit -> produit.getNom().equals(nom));
    }

    private int trouverIndexProduit(Long id) {
        for (int i = 0; i < produits.size(); i++) {
            if (produits.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }
}

import junit.framework.TestCase;

public class Produitservicetest extends TestCase {

    private ProduitService produitService;

    @Override
    protected void setUp() {
        produitService = new ProduitService();
    }

    public void testAjouterProduitAvecSucces() {
        Produit produit = new Produit(1L, "Produit1", 10.0, 5);
        produitService.ajouterProduit(produit);

        Produit produitAjoute = produitService.consulterProduit(1L);
        assertNotNull(produitAjoute);
        assertEquals(produit, produitAjoute);
    }

    public void testAjouterProduitDuplicateID() {
        Produit produit1 = new Produit(1L, "Produit1", 10.0, 5);
        Produit produit2 = new Produit(1L, "Produit2", 15.0, 8);

        produitService.ajouterProduit(produit1);
        produitService.ajouterProduit(produit2);

        Produit produitAjoute = produitService.consulterProduit(1L);
        assertNotNull(produitAjoute);
        assertEquals(produit1, produitAjoute);
    }

    public void testAjouterProduitDuplicateName() {
        Produit produit1 = new Produit(1L, "Produit1", 10.0, 5);
        Produit produit2 = new Produit(2L, "Produit1", 15.0, 8);

        produitService.ajouterProduit(produit1);
        produitService.ajouterProduit(produit2);

        Produit produitAjoute = produitService.consulterProduit(1L);
        assertNotNull(produitAjoute);
        assertEquals(produit1, produitAjoute);
    }

    public void testAjouterProduitNegativePrice() {
        Produit produit = new Produit(1L, "Produit1", -5.0, 5);
        produitService.ajouterProduit(produit);

        Produit produitAjoute = produitService.consulterProduit(1L);
        assertNull(produitAjoute);
    }

    public void testAjouterProduitNegativeQuantity() {
        Produit produit = new Produit(1L, "Produit1", 10.0, -5);
        produitService.ajouterProduit(produit);

        Produit produitAjoute = produitService.consulterProduit(1L);
        assertNull(produitAjoute);
    }

   
}

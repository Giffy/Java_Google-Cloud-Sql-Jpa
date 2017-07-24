package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dao.DBAdmin;
import model.Admin;
import model.Gallery;
import model.Item;


public class TestCreateItem {
DBAdmin dbAdmin; 

	@Before
	public void init(){
		dbAdmin = new DBAdmin(); 	
		dbAdmin.connect();
		dbAdmin.deleteAll(Admin.class); 
		dbAdmin.close();
	}
	
	
	@Test 
	public void testgetGalleries(){
		
		/* ------  START MOCK -------  */
		Admin admin1 = getMockAdmin ("Javier");
		Gallery gallery1 =getMockGallery("paisajes", "Fotos de paisajes");
		Gallery gallery2 =getMockGallery("viajes", "Fotos de viajes");
		Item item1 = getMockItem("Cuadro", "cuadro redondo", 100.4f);
				
		gallery1.setAdmin(admin1);
		gallery2.setAdmin(admin1);
		item1.setGallery(gallery1);
				
		dbAdmin.connect();			
		dbAdmin.getEntityManager().getTransaction().begin();		
			dbAdmin.getEntityManager().persist(admin1);
			admin1.getGallery().add(gallery1); 
			admin1.getGallery().add(gallery2);	
			gallery1.getItem().add(item1);
		dbAdmin.getEntityManager().getTransaction().commit();		 
		dbAdmin.close(); 	
		/* ------  END MOCK -------  */
		
		int id = gallery1.getId();
		
		Item item2 = getMockItem("Foto", "cuadro redondo", 100.4f);
		dbAdmin.createItem(gallery1, item2);
		
		dbAdmin.connect();			
		dbAdmin.getEntityManager().getTransaction().begin();		
			Gallery toCheck = dbAdmin.getEntityManager().find(Gallery.class, id);
			int size= toCheck.getItem().size();
		dbAdmin.getEntityManager().getTransaction().commit();		 
		dbAdmin.close(); 
		
		Assert.assertEquals( 2, size);      							
		
	}

	@Test (expected= RuntimeException.class)
	public void testgetGalleries2(){
		/* ------  START MOCK -------  */
		Admin admin1 = getMockAdmin ("Javier");
		Gallery gallery1 =getMockGallery("paisajes", "Fotos de paisajes");
		Gallery gallery2 =getMockGallery("viajes", "Fotos de viajes");
		Item item1 = getMockItem("Cuadro", "cuadro redondo", 100.4f);
				
		gallery1.setAdmin(admin1);
		gallery2.setAdmin(admin1);
		item1.setGallery(gallery1);
				
		dbAdmin.connect();			
		dbAdmin.getEntityManager().getTransaction().begin();		
			dbAdmin.getEntityManager().persist(admin1);
			admin1.getGallery().add(gallery1); 
			admin1.getGallery().add(gallery2);	
			gallery1.getItem().add(item1);
		dbAdmin.getEntityManager().getTransaction().commit();		 
		dbAdmin.close(); 	
		/* ------  END MOCK -------  */
		
		dbAdmin.createItem(gallery1, item1);
	}	
	
	
	
	
	
	
	
	/*    MOCK creation    */
	
	public Admin getMockAdmin(String name) {
		 Admin admin = new Admin();
		  admin.setName(name);		  
		 return admin;
	}
	
	public Gallery getMockGallery(String name, String description) {
		 Gallery gallery = new Gallery();
		  gallery.setName(name);
		  gallery.setDescription(description);
		 return gallery;
	}
	
	public Item getMockItem(String name, String description, float price) {
		 Item item = new Item();
		  item.setName(name);
		  item.setDescription(description);
		  item.setPrice(price);
		 return item;
	}
	
}
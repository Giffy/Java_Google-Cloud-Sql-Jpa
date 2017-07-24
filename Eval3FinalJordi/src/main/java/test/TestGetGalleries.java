package test;

import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dao.DBAdmin;
import model.Admin;
import model.Gallery;
import model.Item;


public class TestGetGalleries {
DBAdmin dbAdmin; 

	@Before
	public void init(){
		dbAdmin = new DBAdmin(); 	
		dbAdmin.connect();
		// dbAdmin.deleteAll(Admin.class);
		dbAdmin.close();
	}
	
	
	@Test
	public void testgetGalleries(){
			
		Admin admin1 = getMockAdmin ("Pedro");
		Gallery gallery1 =getMockGallery("paisajes", "Fotos de paisajes");
		Gallery gallery2 =getMockGallery("viajes", "Fotos de viajes");
		gallery1.setAdmin(admin1);
		gallery2.setAdmin(admin1);
		
		
		dbAdmin.connect();			
		dbAdmin.getEntityManager().getTransaction().begin();		
			dbAdmin.getEntityManager().persist(admin1);
			admin1.getGallery().add(gallery1); 
			admin1.getGallery().add(gallery2);			
		dbAdmin.getEntityManager().getTransaction().commit();		 
		dbAdmin.close(); 	
		
		int id = admin1.getId();
		
		Set<Gallery> galleries = dbAdmin.getGalleries(id);
		
		Assert.assertEquals( 2, galleries.size());
		
	}

	
	
	@Test (expected= RuntimeException.class)
	public void testgetGalleries2(){
					
		Set<Gallery> galleries = dbAdmin.getGalleries(0);
		
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
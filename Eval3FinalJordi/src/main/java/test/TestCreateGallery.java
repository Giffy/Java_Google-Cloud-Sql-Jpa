package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dao.DBAdmin;
import model.Admin;
import model.Gallery;
import model.Item;


public class TestCreateGallery {
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
			
		Admin admin1 = getMockAdmin ("Pedro");
		Gallery gallery1 =getMockGallery("paisajes", "Fotos de paisajes");
		gallery1.setAdmin(admin1);
		
		
		dbAdmin.connect();			
		dbAdmin.getEntityManager().getTransaction().begin();		
			dbAdmin.getEntityManager().persist(admin1);
			admin1.getGallery().add(gallery1); 		
		dbAdmin.getEntityManager().getTransaction().commit();		 
		dbAdmin.close(); 	
		
		int id = admin1.getId();
		
		dbAdmin.connect();			
		dbAdmin.getEntityManager().getTransaction().begin();		
			Admin toCheck = dbAdmin.getEntityManager().find(Admin.class, id);
			int size= toCheck.getGallery().size();
		dbAdmin.getEntityManager().getTransaction().commit();		 
		dbAdmin.close(); 
		
		Assert.assertEquals( 1, size);      		//   Test de una galeria antes de a�adir otra
	
			Gallery gallery2 =getMockGallery("viajes", "Fotos de viajes");
				
		dbAdmin.createGallery(admin1, gallery2);
		
		
		dbAdmin.connect();			
		dbAdmin.getEntityManager().getTransaction().begin();		
			toCheck = dbAdmin.getEntityManager().find(Admin.class, id);
			size= toCheck.getGallery().size();
		dbAdmin.getEntityManager().getTransaction().commit();		 
		dbAdmin.close(); 
		
		Assert.assertEquals( 2, size);   			//   Test con la galeria a�adida	
	}
	
	
	@Test (expected= RuntimeException.class)
	public void testgetGalleries2(){
		Admin admin1 = getMockAdmin ("Pedro");
		Gallery gallery1 =getMockGallery("paisajes", "Fotos de paisajes");
		gallery1.setAdmin(admin1);
			
		dbAdmin.connect();			
		dbAdmin.getEntityManager().getTransaction().begin();		
			dbAdmin.getEntityManager().persist(admin1);
			admin1.getGallery().add(gallery1); 		
		dbAdmin.getEntityManager().getTransaction().commit();		 
		dbAdmin.close(); 	
		
		dbAdmin.createGallery(admin1, gallery1);    //   Test a�adir galeria existente Expected Runtime Exception	
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
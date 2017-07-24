package test;


import java.util.ArrayList;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import dao.DBAdmin;
import model.Admin;
import model.Comment;
import model.Gallery;
import model.Item;


public class TestRemoveAdmin {
DBAdmin dbAdmin; 

	@Before
	public void init(){
		dbAdmin = new DBAdmin(); 	
		dbAdmin.connect();
		dbAdmin.deleteAll(Admin.class); 
		dbAdmin.close();
	}
	
	
	@Test
	public void testRemoveAdmin(){
			
		Admin admin1 = getMockAdmin ("Pedro");
		Gallery gallery1 =getMockGallery("paisajes", "Fotos de paisajes");
		Item item1 = getMockItem("Foto", "Foto", 10.5f);
		gallery1.setAdmin(admin1);
		item1.setGallery(gallery1);
		Admin admin2 = getMockAdmin ("Javier");
		Gallery gallery2 =getMockGallery("viajes", "Fotos de viajes");
		Item item2 = getMockItem("Cuadro", "Cuadro", 10.5f);
		gallery2.setAdmin(admin2);
		item2.setGallery(gallery2);
		
		dbAdmin.connect();			
		dbAdmin.getEntityManager().getTransaction().begin();		
			dbAdmin.getEntityManager().persist(admin1);
			admin1.getGallery().add(gallery1); 
			gallery1.getItem().add(item1);
			dbAdmin.getEntityManager().persist(admin2);
			admin2.getGallery().add(gallery2);
			gallery2.getItem().add(item2);
		dbAdmin.getEntityManager().getTransaction().commit();		 
		dbAdmin.close(); 	
		
		int id = admin1.getId();
		
		dbAdmin.connect();
			dbAdmin.getEntityManager().getTransaction().begin();		
				Admin toRemoveAdmin = dbAdmin.getEntityManager().find(Admin.class, id);
			dbAdmin.getEntityManager().getTransaction().commit();
		dbAdmin.close(); 
			
		dbAdmin.removeAdmin(toRemoveAdmin);
				
		dbAdmin.connect();
		Set <Admin> test = dbAdmin.selectAll(Admin.class);
		dbAdmin.close();
		
		Assert.assertEquals( 1, test.size());
		
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
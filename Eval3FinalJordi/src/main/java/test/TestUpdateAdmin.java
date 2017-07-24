package test;


import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import dao.DBAdmin;
import model.Admin;
import model.Comment;
import model.Gallery;
import model.Item;


public class TestUpdateAdmin {
DBAdmin dbAdmin; 

	@Before
	public void init(){
		dbAdmin = new DBAdmin(); 	
		dbAdmin.connect();
		dbAdmin.deleteAll(Admin.class);
		dbAdmin.deleteAll(Comment.class); 
		dbAdmin.close();
	}
	
	
	@Test 
	public void testUpdateAdminOK(){
			
		Admin admin1 = getMockAdmin ("Pedro");
		Gallery gallery1 =getMockGallery("paisajes", "Fotos de paisajes");
		Item item1 = getMockItem("Foto", "Foto", 10.5f);
		gallery1.setAdmin(admin1);
		item1.setGallery(gallery1);
				
		dbAdmin.connect();			
		dbAdmin.getEntityManager().getTransaction().begin();		
			dbAdmin.getEntityManager().persist(admin1);
			admin1.getGallery().add(gallery1); 
			gallery1.getItem().add(item1);		
		dbAdmin.getEntityManager().getTransaction().commit();		 
		dbAdmin.close();
		
		int id = admin1.getId();
		
		dbAdmin.connect();	
			Admin toUpdate = dbAdmin.find(Admin.class, id);
			toUpdate.setName("Javier");			
		dbAdmin.close(); 
		
		dbAdmin.updateAdmin(toUpdate);
				
		dbAdmin.connect();
			Admin test = dbAdmin.find(Admin.class, id);
		dbAdmin.close(); 
		
		Assert.assertEquals( "Javier", test.getName());
		
		
		
	}
	
	@Test (expected= RuntimeException.class)
	public void testUpdateAdminBadId(){
				
		 int id = 800; 		
		 Admin toUpdate = new Admin(); 
		 toUpdate.setId(id); 
		 toUpdate.setName("Javier"); 
		
		dbAdmin.updateAdmin(toUpdate);
				
		
		
		
		
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
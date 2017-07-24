package test;

import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dao.DBAdmin;
import model.Admin;
import model.Comment;
import model.Gallery;
import model.Item;


public class TestGetComment {
DBAdmin dbAdmin; 

	@Before
	public void init(){
		dbAdmin = new DBAdmin(); 	
		dbAdmin.connect();
		dbAdmin.deleteAll(Admin.class);
		dbAdmin.close();
	}
	
	
	@Test
	public void testGetComment(){
		
		/* ------  START MOCK -------  */
		Admin admin1 = getMockAdmin ("Javier");
		Gallery gallery1 =getMockGallery("paisajes", "Fotos de paisajes");
		Gallery gallery2 =getMockGallery("viajes", "Fotos de viajes");
		Item item1 = getMockItem("Cuadro", "cuadro redondo", 100.4f);
		Comment comment1 = getMockComment( 9 , "cuadro redondo");
		Comment comment2 = getMockComment( 6 , "cuadro redondo");
				
		gallery1.setAdmin(admin1);
		gallery2.setAdmin(admin1);
		item1.setGallery(gallery1);
		comment1.setItem(item1);
		comment2.setItem(item1);
				
		dbAdmin.connect();			
		dbAdmin.getEntityManager().getTransaction().begin();		
			dbAdmin.getEntityManager().persist(admin1);
			admin1.getGallery().add(gallery1); 
			admin1.getGallery().add(gallery2);	
			gallery1.getItem().add(item1);
			item1.getComments().add(comment1);
			item1.getComments().add(comment2);
		dbAdmin.getEntityManager().getTransaction().commit();		 
		dbAdmin.close(); 	
		/* ------  END MOCK -------  */
		
		int id = item1.getId();
		
		Set<Comment> coleccionComentarios= dbAdmin.getComment(id);
		Assert.assertEquals( 2, coleccionComentarios.size());
	}
		
	@Test (expected= RuntimeException.class)
	public void testgetItems2(){
					
		Set<Comment> coleccionComentarios= dbAdmin.getComment(0);
		
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
	
	public Comment getMockComment(int rate, String message) {
		 Comment comment = new Comment();
		  comment.setRate(rate);
		  comment.setMessage(message);		  
		 return comment;
	}
	
}
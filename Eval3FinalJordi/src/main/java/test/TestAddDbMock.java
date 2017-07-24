package test;

import org.junit.Before;
import org.junit.Test;

import dao.DBAdmin;
import model.Admin;
import model.Comment;
import model.Gallery;
import model.Item;


public class TestAddDbMock {
DBAdmin dbAdmin; 

	@Before
	public void init(){
		dbAdmin = new DBAdmin(); 	
		dbAdmin.connect();
		dbAdmin.deleteAll(Admin.class);
		dbAdmin.close();
	}
	
	
	@Test
	public void AddMock(){
			
		Admin admin1 = getMockAdmin ("Javier Martinez");
		Gallery gallery1 =getMockGallery("Esculturas", "Esculturas de animales");
		Gallery gallery2 =getMockGallery("Fotos", "Fotos de viajes");
		Item item1 = getMockItem("Escultura", "Tigre", 1700.4f);
		Item item2 = getMockItem("Foto", "India de noche", 300.2f);
		Comment comment1 = getMockComment( 9 , "Buen acabado");
		Comment comment2 = getMockComment( 6 , "Precio alto");
		
		gallery1.setAdmin(admin1);
		gallery2.setAdmin(admin1);
		item1.setGallery(gallery1);
		item2.setGallery(gallery2);
		comment1.setItem(item1);
		comment2.setItem(item1);
				
		dbAdmin.connect();			
		dbAdmin.getEntityManager().getTransaction().begin();		
	dbAdmin.getEntityManager().getTransaction().commit();		 
		dbAdmin.close(); 	

		Admin admin10 = getMockAdmin ("Pedro Picapiedra");
		Gallery gallery10 =getMockGallery("Fotos", "Fotos de paisajes");
		Gallery gallery20 =getMockGallery("Mobiliario", "Mesa del siglo XV");
		Item item10 = getMockItem("Foto", "Vistas desde la costa", 100f);
		Item item20 = getMockItem("Mobiliario", "Mesa para 10 personas", 3000f);
		Comment comment10 = getMockComment( 5 , "Foto redonda");
		Comment comment20 = getMockComment( 6 , "Poca luminosidad");
		
		gallery10.setAdmin(admin10);
		gallery20.setAdmin(admin10);
		item10.setGallery(gallery10);
		item20.setGallery(gallery20);
		comment10.setItem(item10);
		comment20.setItem(item10);
				
		dbAdmin.connect();			
		dbAdmin.getEntityManager().getTransaction().begin();		
			dbAdmin.getEntityManager().persist(admin1);
			admin1.getGallery().add(gallery1); 
			admin1.getGallery().add(gallery2);	
			gallery1.getItem().add(item1);
			gallery2.getItem().add(item2);
			item1.getComments().add(comment1);
			item1.getComments().add(comment2);

			dbAdmin.getEntityManager().persist(admin10);
			admin10.getGallery().add(gallery10); 
			admin10.getGallery().add(gallery20);	
			gallery10.getItem().add(item10);
			gallery20.getItem().add(item20);
			item10.getComments().add(comment10);
			item10.getComments().add(comment2);
		dbAdmin.getEntityManager().getTransaction().commit();		 
		dbAdmin.close(); 	
		
		
		
		
		
		
		
		
		
		
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
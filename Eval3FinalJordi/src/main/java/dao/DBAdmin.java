package dao;

import java.util.HashSet;
import java.util.Set;

import model.Admin;
import model.Comment;
import model.Gallery;
import model.Item;



public class DBAdmin extends DBManager implements AdminServices {

	@Override
	public void createAdmin(Admin admin) {
	  connect();
		entitymanager.getTransaction().begin();
			entitymanager.persist(admin);
		entitymanager.getTransaction().commit();
	  close();
	}

	@Override
	public void removeAdmin(Admin admin) {
		if(admin==null){
			throw new RuntimeException("Debe enviarse un Admin");
		}else if(admin.getId()==0){
			throw new RuntimeException("Administrador no existe");
		}else{
		  connect();
			entitymanager.getTransaction().begin();
				Admin toRemove = getEntityManager().find(Admin.class, admin.getId());
				if(toRemove==null){
					throw new RuntimeException("Administrador no existe 2");
				}else{
				getEntityManager().remove(toRemove);
				}
			getEntityManager().getTransaction().commit();
		  close();
	    }
	}
		

	@Override
	public void updateAdmin(Admin admin) {		
	  	if(admin==null){
			throw new RuntimeException("Debe enviarse un Admin");
		}else if(admin.getId()==0){
			throw new RuntimeException("Administrador no existe");
		}else{
			connect();
				getEntityManager().getTransaction().begin();
				Admin toUpdate = new Admin();
			
			
				    toUpdate =  getEntityManager().find(Admin.class, admin.getId());	
			
					if(toUpdate!=null){
						toUpdate.setName(admin.getName());				
					}else{
						throw new RuntimeException("ID no valido");
					}
					
					getEntityManager().getTransaction().commit();
			close();
		}
	}

	@Override
	public Set<Gallery> getGalleries(int adminId) {
	  if (adminId!=0){
	  connect();
		entitymanager.getTransaction().begin();
			Admin toGetGallery = getEntityManager().find(Admin.class, adminId);
			Set<Gallery> gallery = toGetGallery.getGallery();
		entitymanager.getTransaction().commit();
	  close();
	  return gallery;
	  }else{
		  throw new RuntimeException("El admin no existe"); 
	  }	  
	}

	@Override
	public void createGallery(Admin admin, Gallery gallery) {
		connect();
			entitymanager.getTransaction().begin();
				int idAdmin= admin.getId();
				
				Admin toGetGallery = getEntityManager().find(Admin.class, idAdmin);
				if (toGetGallery!=null){
					if (idAdmin==0){
						gallery.setAdmin(admin);
						getEntityManager().persist(admin);
						admin.getGallery().add(gallery);
					}else{
						try {
						int idGallery= gallery.getId();
							if (idGallery==0){
								int id= admin.getId(); 
								Admin owner2= getEntityManager().find(Admin.class, id);
								gallery.setAdmin(owner2);
								owner2.getGallery().add(gallery); 
							}else{
								throw new RuntimeException("La galeria ya existe"); 
							}
						}catch (NullPointerException e){
							throw new NullPointerException("La galeria es null");
						}
					}
				}else{
					throw new NullPointerException("El administrador no existe");
				}
								
			entitymanager.getTransaction().commit();		 
		close();		
	}
	
	

	@Override
	public void removeGallery(Gallery gallery) {

		connect();
			entitymanager.getTransaction().begin();
				Gallery toRemove = getEntityManager().find(Gallery.class, gallery.getId());
				Admin ownerGallery = toRemove.getAdmin();
				
				ownerGallery.getGallery().remove(toRemove);
			entitymanager.getTransaction().commit();
		close();
		
	}

	@Override
	public void update(Gallery gallery) {
	  	if(gallery==null){
				throw new RuntimeException("Debe enviarse un Admin");
			}else if(gallery.getId()==0){
				throw new RuntimeException("Administrador no existe");
			}else{
				connect();
					getEntityManager().getTransaction().begin();
					Gallery toUpdate = new Gallery();
								
					    toUpdate =  getEntityManager().find(Gallery.class, gallery.getId());	
				
						if(toUpdate!=null){
							toUpdate.setName(gallery.getName());
							toUpdate.setDescription(gallery.getDescription());				
						}else{
							throw new RuntimeException("ID no valido");
						}
						
						getEntityManager().getTransaction().commit();
				close();
			}
		}

	@Override
	public Set<Item> getItems(int galleryId) {
		if (galleryId!=0){
		connect();
			entitymanager.getTransaction().begin();
				Gallery toGetGallery = getEntityManager().find(Gallery.class, galleryId);
				Set<Item> item = toGetGallery.getItem();
			entitymanager.getTransaction().commit();
		close();	
		return item;
		}else{
			  throw new RuntimeException("La galeria no existe"); 
		}		
	}

	

	@Override
	public void createItem(Gallery gallery, Item item) {
		connect();
		entitymanager.getTransaction().begin();
			int idAdmin= gallery.getId();
			if (idAdmin==0){
				item.setGallery(gallery);
				getEntityManager().persist(gallery);
				gallery.getItem().add(item);
			}else{
				try {
				int idGallery= item.getId();
					if (idGallery==0){
						int id= gallery.getId(); 
						Gallery gallery2= getEntityManager().find(Gallery.class, id);
						item.setGallery (gallery2);
						gallery2.getItem().add(item); 
					}else{
						throw new RuntimeException("El Item ya existe"); 
					}
				}catch (NullPointerException e){
					throw new NullPointerException("El Item es null");
				}
			}
							
		entitymanager.getTransaction().commit();		 
	close();		
}

	@Override
	public void removeItem(Item item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateItem(Item item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<Comment> getComment(int itemId) {
		if (itemId!=0){
		connect();
			entitymanager.getTransaction().begin();
				Item toGetItem = getEntityManager().find(Item.class, itemId);
				Set<Comment> comments = toGetItem.getComments();
			entitymanager.getTransaction().commit();
		close();	
		return comments;
		}else{
			  throw new RuntimeException("El item no existe"); 
		}		
	}

	@Override
	public Set<Admin> getAllAdmin() {
				
		connect();
			entitymanager.getTransaction().begin();
				HashSet<Admin> listAdmin = (selectAll(Admin.class));
			entitymanager.getTransaction().commit();
		close();	
		
			
		return listAdmin;
	}
	
	
	public Admin getAdmin(int id) {
				
		connect();
			entitymanager.getTransaction().begin();
				Admin admin = getEntityManager().find(Admin.class, id);
			entitymanager.getTransaction().commit();
		close();			
			
		return admin;
	}
	
}

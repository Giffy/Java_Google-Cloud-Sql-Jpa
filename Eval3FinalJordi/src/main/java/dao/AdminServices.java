package dao;

import java.util.Set;


import model.Admin;
import model.Gallery;
import model.Item;
import model.Comment;


public interface AdminServices {
	/**
	 * Obtiene la lista de todos los Admin.
	 * @return Set con los Admin
	 */
	public Set<Admin> getAllAdmin();
	/**
	 * Inserta un Nueve administrador Admin en la base de datos.
	 * @param admin
	 */
	public void createAdmin(Admin admin);
	/**
	 * Remueve un administrador de la base de datos. Debe remover en cascada sus Gallery, Item, Comment.
	 * @param admin
	 */
	public void removeAdmin (Admin admin);
	/**
	 * Actualiza los datos de un Admin.
	 * @param admin
	 */
	public void updateAdmin (Admin admin);
	
	/**
	 * Obtiene la lista de Galleries de un Admin con adminId.
	 * @param adminId
	 * @return
	 */
	public Set<Gallery> getGalleries(int adminId);
	/**
	 * Inserta un Nuevo Gallery en la base de datos.
	 * @param admin
	 * @param gallery
	 */
	public void createGallery(Admin admin, Gallery gallery);
	/**
	 * Remueve un Gallery de la base de datos. Debe remover en cascada sus Item y Comment.
	 * @param gallery
	 */
	public void removeGallery (Gallery gallery);
	/**
	 * Actualiza los datos de un gallery
	 * @param gallery
	 */
	public void update (Gallery gallery);
	/**
	 * Obtiene la lista de items de un Gallery con galleryId
	 * @param galleryId
	 * @return
	 */
	public Set<Item> getItems(int galleryId);
	
	
	public void createItem (Gallery gallery, Item item);
	public void removeItem (Item item);
	public void updateItem (Item item);
	public Set<Comment> getComment (int itemId);

}

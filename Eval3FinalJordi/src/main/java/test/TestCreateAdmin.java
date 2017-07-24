package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dao.DBAdmin;
import model.Admin;


public class TestCreateAdmin {
DBAdmin dbAdmin; 

	@Before
	public void init(){
		dbAdmin = new DBAdmin(); 	
		dbAdmin.connect();
		dbAdmin.deleteAll(Admin.class);
		dbAdmin.close();
	}
	
	@Test
	public void testCreateAdmin(){
		
		Admin admin1 = getMockAdmin ("Pedro");
		
		dbAdmin.createAdmin(admin1);
		
		Assert.assertEquals(true, admin1.getId()!=0);
		Assert.assertEquals("Pedro", admin1.getName());

	}

	
	
	/*    MOCK creation    */
	
	public Admin getMockAdmin(String name) {
		 Admin admin = new Admin();
		  admin.setName(name);		  
		 return admin;
	}

}
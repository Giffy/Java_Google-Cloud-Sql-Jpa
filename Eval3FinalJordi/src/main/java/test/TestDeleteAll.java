package test;

import org.junit.Before;
import org.junit.Test;

import dao.DBAdmin;
import model.Admin;

public class TestDeleteAll {
	DBAdmin dbAdmin; 

	@Test
	public void init(){
		dbAdmin = new DBAdmin(); 	
		dbAdmin.connect();
		dbAdmin.deleteAll(Admin.class);
		dbAdmin.close();
	}

}

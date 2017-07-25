Desarrollo y despliegue de una aplicacion Java en el modelo de base de datos
============================================================================

La aplicación implementa una capa de presentación, negociación y acceso a datos.
Utiliza MySQL, en local y en Google Cloud.

* Definicion de interface AdminServices con los metodos:

	public Set<Admin> getAllAdmin()
	public void createAdmin(Admin admin)
        public void removeAdmin(Admin admin)
        public void updateAdmin(Admin admin)

        public public Set<Gallery> getGalleries(int adminId)
        public void createGallery(Admin admin, Gallery gallery)
        public void removeAdmin(Gallery gallery)
	public void updateAdmin(Gallery gallery)

        public Set<Item> getGalleries(int galleryId)



*Entities de la base de datos:

	
Admin.class
           
     id:int
		name:String
		galleries:Set<Gallery>
		Relacion con cascade  Admin (one  -->  many) Gallery
	
Gallery.class

		id:int
    
		name:String
    
		description:String
    
		admin:Admin
    	
		items:Set<Item>
    
		Relacion   Gallery (one  -->  many) Item
	
Item.class    
		id:int
    
		name:String
    
		description:String
    
		price:float
    
		Relacion   Item (one  -->  many) Comment
	Comment.class
    
		id:int
    
		rate:int
    
		message:String
    
		item:item
    
		user:User
    
		Relacion   Comment (one  -->  one) User

	User.class
    
		id:int
    
		name:String




appengine-standard-archetype
============================

This is a generated App Engine Standard Java application from the appengine-standard-archetype archetype.


* Java 7
* [Maven](https://maven.apache.org/download.cgi) (at least 3.3.9)
* [Gradle](https://gradle.org/gradle-download/) (optional)
* [Google Cloud SDK](https://cloud.google.com/sdk/) (aka gcloud)

Initialize the Google Cloud SDK using:

    gcloud init

This skeleton is ready to run.



    mvn appengine:run


    mvn appengine:deploy


    mvn test



    gradle appengineRun


    gradle appengineDeploy


    gradle test


As you add / modify the source code (`src/main/java/...`) it's very useful to add [unit testing](https://cloud.google.com/appengine/docs/java/tools/localunittesting)
to (`src/main/test/...`).  The following resources are quite useful:

* [Junit4](http://junit.org/junit4/)
* [Mockito](http://mockito.org/)
* [Truth](http://google.github.io/truth/)

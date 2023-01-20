package com.ws.crud.mongodb;

import java.net.UnknownHostException;

import org.bson.Document;
import org.hibernate.loader.custom.Return;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class Mongo {
	public String loginAdmin(String emailEntry, String passwordEntry) throws UnknownHostException {

		// Admin admin = createAdmin();
		// DBObject doc = createDBObject(admin);
		String message =  null;
		ConnectionString connectionString = new ConnectionString(
				"mongodb+srv://admin:jGVATIz90LnzhVUi@andrianiaina.ehwcdje.mongodb.net/?retryWrites=true&w=majority");
		MongoClientSettings settings = MongoClientSettings.builder()
				.applyConnectionString(connectionString)
				.build();
		MongoClient mongoClient = MongoClients.create(settings);
		MongoDatabase database = mongoClient.getDatabase("enchere");

		MongoCollection<Document> col = database.getCollection("admins");

		// //create user
		// WriteResult result = col.insert(doc);
		// System.out.println(result.getUpsertedId());
		// System.out.println(result.getN());
		// System.out.println(result.isUpdateOfExisting());

		// read example
		Document query = new Document("email", emailEntry);
		MongoCursor<Document> cursor = col.find(query).iterator();
		int count = 0;
		while(cursor.hasNext()){
			String password = (String) cursor.next().get("password");
			if(password.equals(passwordEntry)){
				System.out.println("Successfull");
				message = "Successfull";
			}else{
				System.out.println("Password Wrong");
				message = "Password Wrong";
			}
			count ++;
		}
		if(count == 0){
			System.out.println("Admin email : " +emailEntry+ " not found.");
			message = "Admin email : " +emailEntry+ " not found.";
		}

		return message;

		// //update example
		// user.setEmail("test@gmail.com");
		// doc = createDBObject(user);
		// result = col.update(query, doc);
		// System.out.println(result.getUpsertedId());
		// System.out.println(result.getN());
		// System.out.println(result.isUpdateOfExisting());
		// System.out.println(result.getLastConcern());

		// //delete example
		// result = col.remove(query);
		// System.out.println(result.getUpsertedId());
		// System.out.println(result.getN());
		// System.out.println(result.isUpdateOfExisting());
		// System.out.println(result.getLastConcern());

		// close resources
		// database.close();
	}
	public int getAdminId(String emailEntry, String passwordEntry) throws UnknownHostException {

		// Admin admin = createAdmin();
		// DBObject doc = createDBObject(admin);
		int adminId;
		ConnectionString connectionString = new ConnectionString(
				"mongodb+srv://admin:jGVATIz90LnzhVUi@andrianiaina.ehwcdje.mongodb.net/?retryWrites=true&w=majority");
		MongoClientSettings settings = MongoClientSettings.builder()
				.applyConnectionString(connectionString)
				.build();
		MongoClient mongoClient = MongoClients.create(settings);
		MongoDatabase database = mongoClient.getDatabase("enchere");

		MongoCollection<Document> col = database.getCollection("admins");

		// //create user
		// WriteResult result = col.insert(doc);
		// System.out.println(result.getUpsertedId());
		// System.out.println(result.getN());
		// System.out.println(result.isUpdateOfExisting());

		// read example
		Document query = new Document("email", emailEntry);
		Document cursor = col.find(query).iterator().next();
		adminId = (int) cursor.get("id");
		return adminId;
	}

	// private static DBObject createDBObject(Admin admin) {
	// 	BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();

	// 	docBuilder.append("_id", admin.getId());
	// 	docBuilder.append("username", admin.getEmail());
	// 	docBuilder.append("password", admin.getPassword());
	// 	return docBuilder.get();
	// }

	// private static Admin createAdmin() {
	// 	Admin admin = new Admin();
	// 	admin.setId(1);
	// 	admin.setEmail("admin@gmail.com");
	// 	admin.setPassword("password");
	// 	return admin;
	// }
}

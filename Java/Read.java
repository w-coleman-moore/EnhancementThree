
import java.net.UnknownHostException;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

//Read a document from the market database
//in the stocks collection
public class Read {

	public static void main(String[] args) throws UnknownHostException {

		// connect to the Mongo server using port 27017
		MongoClient mongo = new MongoClient("localhost", 27017);
		// get the 'market' database
		MongoDatabase db = mongo.getDatabase("market");
		// get the 'stocks' collection
		MongoCollection<Document> col = db.getCollection("stocks");

		// find the first stock in the collection with a profit margin equal to 0.900
		Document firstStock = col.find(new Document("Profit Margin",0.900)).first();

		// print the first stock that matched our search
		System.out.println("Stock: " + firstStock.toJson());

		// close the connection to the server
		mongo.close();
	}
}

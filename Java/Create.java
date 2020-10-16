
import java.net.UnknownHostException;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

// Create a document in the market database
// in the stocks collection
public class Create {

	public static void main(String[] args) throws UnknownHostException {

		// connect to the Mongo server using port 27017
		MongoClient mongo = new MongoClient("localhost", 27017);
		// get the 'market' database
		MongoDatabase db = mongo.getDatabase("market");
		// get the 'stocks' collection
		MongoCollection<Document> col = db.getCollection("stocks");

		// create 3 fields and values for the document to create
		String json = "{'Ticker':'ZRTC','Profit Margin':0.878, 'EPS growth past 5 years':0.158}";

		// create a document from the json string
		Document doc = new Document(Document.parse(json));
		// insert (create) the document in the collectioon
		col.insertOne(doc);

		System.out.println("ID Generated=" + doc.getObjectId("_id").toString());
		
		// close the connection to the server
		mongo.close();
	}
}
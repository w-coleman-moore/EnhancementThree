
import java.net.UnknownHostException;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.conversions.Bson;

//Update an existing document in the market database
//in the stocks collection
public class Update {

	public static void main(String[] args) throws UnknownHostException {

		// connect to the Mongo server using port 27017
		MongoClient mongo = new MongoClient("localhost", 27017);
		// get the 'market' database
		MongoDatabase db = mongo.getDatabase("market");
		// get the 'stocks' collection
		MongoCollection<Document> col = db.getCollection("stocks");

		// search for the document where the ticker symbol is ZRTC
		Bson filter = eq("Ticker", "ZRTC");
		// in the document found, set the profit margin to 0.900
		Bson updateOperation = set("Profit Margin",0.900);
		// this actually does the work, find the document and update the field
		UpdateResult updateResult = col.updateOne(filter, updateOperation);

		// close the connection to the server
		mongo.close();
	}
}

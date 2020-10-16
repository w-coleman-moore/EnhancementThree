
import java.net.UnknownHostException;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.conversions.Bson;

//Delete a document from the market database
//in the stocks collection
public class Delete {

	public static void main(String[] args) throws UnknownHostException {

		// connect to the Mongo server using port 27017
		MongoClient mongo = new MongoClient("localhost", 27017);
		// get the 'market' database
		MongoDatabase db = mongo.getDatabase("market");
		// get the 'stocks' collection
		MongoCollection<Document> col = db.getCollection("stocks");

		// the filter is the value to search for - where the ticker symbol equals ZRTC
		Bson filter = eq("Ticker", "ZRTC");
		// delete the document from the collection
        DeleteResult result = col.deleteOne(filter);
        System.out.println(result);

		// close the connection to the server
		mongo.close();
	}
}

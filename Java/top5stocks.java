
import java.net.UnknownHostException;
import java.util.ArrayList;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.FindIterable;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.lt;

//Filter our criteria for searching and find and store all
//documents that match our criteria. Then print all of the
//documents we found
public class top5stocks {

	public static void main(float[] args) throws UnknownHostException {

		// connect to the Mongo server using port 27017
		MongoClient mongo = new MongoClient("localhost", 27017);
		// get the 'market' database
		MongoDatabase db = mongo.getDatabase("market");
		// get the 'stocks' collection
		MongoCollection<Document> col = db.getCollection("stocks");

		// create the filters for all the parameters of our search
		// this filter is used in the find() function below
		Bson filter = Filters.and(
                Filters.eq("Ticker", 1), 
                Filters.eq("Industry", 2),
                Filters.eq("Shares Outstanding",3),
                Filters.eq("50-Day Simple Moving Average",4),
                Filters.eq("Sector",5),
                Filters.eq("_id",0)
              );
		
		// find the stocks in the collection that meet the selected criteria
		@SuppressWarnings("unchecked")
		FindIterable<Document> fit = (FindIterable<Document>) col.find(filter).first();

        // create an array of documents to store the search results
        ArrayList<Document> docs = new ArrayList<Document>();

        // perform the search, store the matching documents
        fit.into(docs);

        // print out each of the documents found that match our criteria
        for (Document doc : docs) {
            System.out.println(doc);
        }

		// close the connection to the server
		mongo.close();
	}
}

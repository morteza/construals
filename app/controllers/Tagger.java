package controllers;

import java.util.List;

import fuschia.tagger.common.*;

import play.mvc.*;

public class Tagger extends Controller {

	static DocumentRepository repo = DocumentRepository.get911();

    public static void index() {
        //List<Person> people = Person.find("ORDER BY lastName").fetch();
        //render(people);
    	render();
    }
    
    public static void search(String searchId) {
    	
    	String resultId = new String(searchId);
    	String resultText = null;

    	try {
    	Document doc = repo.getDocument(searchId);
    	int tagSize = doc.size();
    	resultText = "<div>";
		for (int i = 0; i < tagSize; i++) {

			// TOKEN
			resultText += "<code><strong style=\"color: #000000;\">" + doc.tokens[i] + "</strong>&nbsp;";

			// TAG
			resultText += "<em style=\"color: #CC0000;\">" + doc.tags[i] + "</em></code>  ";
		}

    	resultText += "</div>";
    	} catch (Exception e) {}
    	render(resultId,resultText);
   }
    
}

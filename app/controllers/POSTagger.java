package controllers;

import java.util.List;

import fuschia.tagger.common.*;

import play.mvc.*;

public class POSTagger extends Controller {

	static DocumentRepository repo = DocumentRepository.get911();

    public static void index() {
        //List<Person> people = Person.find("ORDER BY lastName").fetch();
        //render(people);
    	render();
    }
    
    public static void postag(String surveyId, String universityId, String participantId, String questionId) {
    	
    	if (participantId.length() == 2)
    		participantId = "0" + participantId;
    	else if (participantId.length() == 1)
    		participantId = "00" + participantId;
    	
    	String strSurveySuffix = "";
    	if (surveyId.equals("2"))
    		strSurveySuffix = "B";
    	else if (surveyId.equals("3"))
    		strSurveySuffix = "C";
    	
    	String strQuery = universityId + participantId + strSurveySuffix + "-Q" + questionId;
    	String resultText = null;

    	try {
    	Document doc = repo.getDocument(strQuery);
    	int tagSize = doc.size();
    	resultText = "<div>";
		for (int i = 0; i < tagSize; i++) {

			// TOKEN
			resultText += "<em style=\"color: #000000;\">" + doc.tokens[i] + "</em></code>  ";

			// TAG
			resultText += "<span class=\"label label-info\">" + doc.tags[i] + "</span>&nbsp;";
		}

    	resultText += "</div>";
    	} catch (Exception e) {}
    	render(strQuery,resultText);
   }
    
}

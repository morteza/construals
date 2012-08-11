package controllers;

import fuschia.tagger.Bootstrap;
import play.mvc.*;

public class Survey extends Controller {

    public static void index() {
    	String participantId;
    	do {
    		participantId = Bootstrap.DefaultDocumentRepository.getParticipantMap().getRandomS1Key();
    	} while (participantId.endsWith("B"));
    	
    	String strSurveyContent = Bootstrap.DefaultDocumentRepository.getDocumentById(participantId+"-Q1").getText();
        render(participantId, strSurveyContent);
    }

}

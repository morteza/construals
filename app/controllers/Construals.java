package controllers;

import fuschia.tagger.Bootstrap;
import fuschia.tagger.common.Document;
import models.ConstrualData;
import play.mvc.*;

public class Construals extends Controller {

    public static void index() {
        render();
    }

    public static void construal_result(String universityId, String participantId, String questionId) {
    	
       	if (participantId.length() == 2)
    		participantId = "0" + participantId;
    	else if (participantId.length() == 1)
    		participantId = "00" + participantId;
    	    	
    	String mappedS1Id = Bootstrap.DefaultDocumentRepository.getParticipantMap().getS1(universityId + participantId);
    	String mappedS2Id = Bootstrap.DefaultDocumentRepository.getParticipantMap().getS2(universityId + participantId);
    	String mappedS3Id = Bootstrap.DefaultDocumentRepository.getParticipantMap().getS3(universityId + participantId);
    	String strQuery1 = mappedS1Id + "-Q" + questionId;
    	String strQuery2 = mappedS2Id + "-Q" + questionId;
    	String strQuery3 = mappedS3Id + "-Q" + questionId;

    	String strQuery = mappedS1Id + ", " + mappedS2Id + ", " + mappedS3Id + " (Q" + questionId + ")";
    	String[] abstScores = new String[3];
    	
    	try {
    		Document[] docs = new Document[3];
	    	docs[0] = Bootstrap.DefaultDocumentRepository.getDocumentById(strQuery1);
	    	docs[1] = Bootstrap.DefaultDocumentRepository.getDocumentById(strQuery2);
	    	docs[2] = Bootstrap.DefaultDocumentRepository.getDocumentById(strQuery3);
	    	
	    	int[] verbsCount = new int[3];
	    	int[] adjectivesCount = new int[3];
	    	
	    	for (int i=0;i<3;i++) {
		    	for (String tag:docs[i].tags) {
					if (tag.startsWith("VB")) {
						verbsCount[i]++;
					} else if (tag.startsWith("JJ")
								|| tag.startsWith("RB")
								|| tag.startsWith("WRB")) {
						adjectivesCount[i]++;
					}
		    	}
		    	if (verbsCount[i]+adjectivesCount[i]>0) {
			    	int intAbstractionScore = (int) ((verbsCount[i]*3+adjectivesCount[i])*100/(verbsCount[i]+adjectivesCount[i]));
			    	abstScores[i] = String.valueOf(intAbstractionScore/100) + "." + String.valueOf(intAbstractionScore%100);		    		
		    	} else {
		    		abstScores[i] = "N/A";
		    	}

	    	}
    	} catch (Exception e) {}
    	String abstScores_survey1 = abstScores[0];
    	String abstScores_survey2 = abstScores[1];
    	String abstScores_survey3 = abstScores[2];    	
		render(strQuery, abstScores_survey1, abstScores_survey2, abstScores_survey3);

    }
}

package fuschia.tagger;
import fuschia.tagger.common.DocumentRepository;
import play.jobs.*;
 
@OnApplicationStart
public class Bootstrap extends Job {
	
	public static DocumentRepository DefaultDocumentRepository;

    public void doJob() {
    	DefaultDocumentRepository = DocumentRepository.get911();
    }
    
}
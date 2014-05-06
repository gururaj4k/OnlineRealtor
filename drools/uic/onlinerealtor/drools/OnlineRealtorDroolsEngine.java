package uic.onlinerealtor.drools;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.json.JSONObject;
import uic.onlinerealtor.entities.Question;
import uic.onlinerealtor.entities.UserAnalysis;
import uic.onlinerealtor.entities.UserPreference;
/**
 * This is implemented class of the IDroolsEngine interface and it provides the definition of methods.
 * @author OnlineRealtor
 * @version 1.0
 */
public class OnlineRealtorDroolsEngine implements IDroolsEngine {
	/** 
	 * Connects to the Drools engine and provides userpreference object as input and 
	 * get useranalysis object as output which contains processed data as per the rules defined.
	 * @param userPreference - UserPreference object with user information provided
	 * @return UserAnalysis -UserAnalysis object with the data processed by drools engine.
	 */
	public UserAnalysis getUserAnalysis(UserPreference userpreference) {
		JSONObject json=new JSONObject(userpreference);
		System.out.println(json.toString());
		UserAnalysis userAnalysis = null;
		try {
			System.out.println("RealtorRules.drl is loading...");
			KnowledgeBase base = readKnowledgeBase("RealtorRules.drl");
			System.out.println("done");
			StatefulKnowledgeSession session = base
					.newStatefulKnowledgeSession();
			session.insert(userpreference);
			userAnalysis = new UserAnalysis();
			session.setGlobal("userAnalysis", userAnalysis);
			session.fireAllRules();
		} catch (Exception ex) {
			//log the exception
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		return userAnalysis;
	}
	/** 
	 * Connects to the Drools engine and provides user answered question object as input and 
	 * get question class object as output which contains next question text 
	 * @param userPreference - Question Class object with the user answer.
	 * @return UserAnalysis -Question Class object with the next question text 
	 */
	public Question getNextQuestion(Question currentQuestion) {
		Question nextQuestion = null;
		try {
			KnowledgeBase base = readKnowledgeBase("WorkFlow.drl");
			StatefulKnowledgeSession session = base
					.newStatefulKnowledgeSession();
			//System.out.println("1..");
			session.insert(currentQuestion);
			nextQuestion = new Question();
			//System.out.println("1..");
			session.setGlobal("nextQuestion", nextQuestion);
			//System.out.println("2...");
			session.fireAllRules();
		} catch (Exception ex) {
			//log the exception
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}		
		return nextQuestion;
	}
	/** 
	 * This load the rules file dynamically by accepting it as an argument
	 * @param rules - rules file needs to be loaded
	 * @return KnowledgeBase -KnowledgeBase class object 
	 */
	private KnowledgeBase readKnowledgeBase(String rules) throws Exception {		
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory
				.newKnowledgeBuilder();
		kbuilder.add(ResourceFactory.newClassPathResource(rules),
				ResourceType.DRL);
		KnowledgeBuilderErrors errors = kbuilder.getErrors();
		if (errors.size() > 0) {
			for (KnowledgeBuilderError error : errors) {
				System.err.println(error);
			}
			throw new IllegalArgumentException("Could not parse knowledge.");		}
		KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
		kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
		return kbase;
	}

}

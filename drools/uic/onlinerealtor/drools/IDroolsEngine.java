package uic.onlinerealtor.drools;

import uic.onlinerealtor.entities.Question;
import uic.onlinerealtor.entities.UserAnalysis;
import uic.onlinerealtor.entities.UserPreference;

/**
 * This is an interface for the drools rules engine and declares the methods to for the drools interface.
 * @author OnlineRealtor
 * @version 1.0
 */
public interface IDroolsEngine {
	UserAnalysis getUserAnalysis(UserPreference userpreference);
	Question getNextQuestion(Question ques);

}

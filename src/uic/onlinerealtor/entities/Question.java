package uic.onlinerealtor.entities;

public class Question {
private int questionId;
private String question;
private String userAns;
/**
 * @return The ID of the question 
 */
public int getQuestionId() {
	return questionId;
}
/**
 * @param questionId Uniwue ID of the question
 */
public void setQuestionId(int questionId) {
	this.questionId = questionId;
}
/**
 * @return The text of the question
 */
public String getQuestion() {
	return question;
}
/**
 * @param question the text of the question
 */
public void setQuestion(String question) {
	this.question = question;
}
/**
 * @return The answer of the question
 */
public String getUserAns() {
	return userAns;
}
/**
 * @param userAns Answer of the question
 */
public void setUserAns(String userAns) {
	this.userAns = userAns;
}
}

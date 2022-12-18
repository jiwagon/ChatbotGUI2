/*
 * chatbot.component is added for Assignment 3 (Language Understanding)
 * 
 * WeatherIntentClassifier.java is added for Assignment 3 (Language
 * Understanding)
 */

package chatbot.component;

public class FoodIntentClassifier {
	
	private static String[] intentDictionary;
	
	public FoodIntentClassifier() {
		initializeIntentDictionary();
	}
	
	/**
	 * Create a dictionary of intents (under Weather domain)  
	 */
	private void initializeIntentDictionary() {
		intentDictionary = new String[]{"FindFood", "OrderFood", "PayForFood"};
		
		System.out.print("Intents: (");
		for(int i=0;i<intentDictionary.length;i++) {
			System.out.print(intentDictionary[i]);
			if(i!=intentDictionary.length-1) {
				System.out.print(", ");
			}
		}
		System.out.println(")");
	}
	
	/**
	 * Calculate the given meesage's score for each intent. The chatbot will
	 * select the intent with the *highest* score.
	 * 
	 * The initial score of each domain is 0.0.
	 * 
	 * @param nowInputText	An English message sent from the user.
	 * @return				An Double array that contains the score of each
	 * 						intent.
	 */
	private Double[] calculateIntentScores(String nowInputText) {
		
		//DO NOT change the following 4 lines
		//initiate all the scores to 0.0 
		Double[] scoreArray = new Double[intentDictionary.length];
		for(int i=0;i<scoreArray.length;i++) {
			scoreArray[i] = Double.valueOf(0.0);
		}
		
		//============= Please Modify Here (begins) =============== 
		
		//The following is the part you need to modify. 
		//This current version just assign random values to each intent.

		//for(int i=0;i<scoreArray.length;i++) {
		//	scoreArray[i] = Math.random();
		//}
		
		String[] findFoodDictionary = new String[] {"find", "food", "options", "hungry", "eat", "restaurants"};
		String[] tokenList1 = nowInputText.trim().toLowerCase().split("\\W");
		for (String intentKeyword: findFoodDictionary) {
			for(int i = 0; i < tokenList1.length; i++) {
				//Ji's edit: removed indexOf(), instead compared the values of each input keyword and intent keyword 
				if(tokenList1[i].compareTo(intentKeyword) == 0) { 
					//scoreArray[0] indicates the score for FindFood intent
					scoreArray[0] = scoreArray[0].doubleValue()+1.0;
				}
			
			}
		}
		
		String[] orderFoodDictionary = new String[] {"order", "food", "add", "online", "get"};
		String[] tokenList2 = nowInputText.trim().toLowerCase().split("\\W");
		for(String snowKeyword: orderFoodDictionary) {
			for(int j = 0; j < tokenList2.length; j++) {
				if(tokenList2[j].compareTo(snowKeyword) == 0) {
					//scoreArray[1] indicates the score for OrderFood intent
					scoreArray[1] = scoreArray[1].doubleValue()+1.0;
				}
			}
		}
		
		String[] payFoodDictionary = new String[] {"food", "price", "how", "much", "pay", "total"};
		String[] tokenList3 = nowInputText.trim().toLowerCase().split("\\W");
		for(String rainKeyword: payFoodDictionary) {
			for(String token: tokenList3) {
				if(token.compareTo(rainKeyword) == 0) {
					//scoreArray[2] indicates the score for PayForFood intent
					scoreArray[2] = scoreArray[2].doubleValue()+1.0;
				}
			}
		}
		
		//============= Please Modify Here (ends) =============== 
		
		//do not change the following lines
		if(scoreArray.length!=intentDictionary.length) {
			System.err.println("The score array size does not equal to the intent array size.");
			System.exit(1);
		}
		for(Double nowValue: scoreArray) {
			if(nowValue==null) {
				System.err.println("The score array contains null values.");
				System.exit(1);
			}
		}
		return scoreArray;
	}
	
	/**
	 * Input:
	 * 	nowInputText: the message that the user sent to your chatbot
	 * 
	 * Output:
	 * 	the label (intent) name string
	 * 
	 * @param nowInputText	An English message sent from the user.
	 * @return 				The name of the intent.
	 * 
	 */
	public String getLabel(String nowInputText) {
		Double[] intentScores = calculateIntentScores(nowInputText);
		Double nowMaxScore = null;
		int nowMaxIndex = -1;
		System.out.print("Intent Scores: (");
		for(int i=0;i<intentScores.length;i++){
			System.out.print(intentScores[i].doubleValue());
			if(i!=intentScores.length-1) {
				System.out.print(", ");
			}
			if(nowMaxScore==null||nowMaxIndex==-1||intentScores[i].doubleValue()>nowMaxScore.doubleValue()) {
				nowMaxIndex = i;
				nowMaxScore = intentScores[i].doubleValue();
			}
		}
		System.out.println(")");
		return intentDictionary[nowMaxIndex];
	}

}

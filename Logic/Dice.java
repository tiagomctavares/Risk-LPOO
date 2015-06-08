package Logic;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Dice {
	
	static int MAX = 6;
	static int MIN = 1;

	public static Integer[] simulateAttack(int numberAttackers, int numberDefenders) {
		Integer[] resultsAttacker = getDiceValues(numberAttackers);
		resultsAttacker[0] = 6;
		Integer[] resultsDefender = getDiceValues(numberDefenders);
		Integer[] attackResults = new Integer[2];
		
		if(numberAttackers == 1 || numberDefenders == 1) {
			if(resultsAttacker[0] > resultsDefender[0])
				attackResults[0] = 1;
			else
				attackResults[0] = 0;
			
			attackResults[1] = -1;
		} else {
			if(resultsAttacker[0] > resultsDefender[0])
				attackResults[0] = 1;
			else
				attackResults[0] = 0;
			
			if(resultsAttacker[1] > resultsDefender[1])
				attackResults[1] = 1;
			else
				attackResults[1] = 0;
		}
		
		return attackResults ;
	}

	private static Integer[] getDiceValues(int size) {
		Integer[] results = new Integer[size];
		
		for (int i = 0; i < results.length; i++) {
			results[i] = randomRoll();
		}

		Arrays.sort(results, Collections.reverseOrder());
		
		return results;
	}

	private static int randomRoll() {
		Random rand = new Random();
		int randomNum = rand.nextInt((MAX - MIN) + 1) + MIN;
		return randomNum;
	}
}

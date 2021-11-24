package baseball;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

public class Game {

    private final int DIGIT = 3;
    private String randNum;
    private String userNum;
    private int ball;
    private int strike;

    private String makeRandNum () {
        StringBuilder randNum = new StringBuilder();

        while (randNum.length() < DIGIT) {
            String temp = Integer.toString(Randoms.pickNumberInRange(1, 9));

            if (!randNum.toString().contains(temp)) {
                randNum.append(temp);
            }
        }

        return String.valueOf(randNum);
    }

    private void isValidInput() {
        if (userNum.length() != DIGIT) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < DIGIT; i++) {
            isNumber(i);
            noZeros(i);
            noOverlap(i);
        }
    }

    private void isNumber(int i) {
        if (!Character.isDigit(userNum.charAt(i))) {
            throw new IllegalArgumentException();
        }
    }

    private void noZeros(int i) {
        if (Character.toString(userNum.charAt(i)).equals("0")) {
            throw new IllegalArgumentException();
        }
    }

    private void noOverlap(int i) {
        for (int j = i+1; j < DIGIT; j++) {
            if (userNum.charAt(i) == userNum.charAt(j)) {
                throw new IllegalArgumentException();
            }
        }
    }

    private String compareNumbs() {
        ball = 0;
        strike = 0;

        for (int i = 0; i < DIGIT; i++) {
            parseNum(i, randNum.charAt(i));
        }

        String hint = getHint();
        System.out.println(hint);
        return hint;
    }

    private void parseNum(int i, char curNum) {
        if (userNum.contains(Character.toString(curNum))) {
            if (userNum.charAt(i) == curNum) {
                strike++;
                return;
            }
            ball++;
        }
    }

    private String getHint() {
        if (strike == 3) {
            return "3스트라이크";
        }
        if (strike == 0 && ball == 0) {
            return "낫싱";
        }
        if (strike == 0) {
            return ball + "볼";
        }
        if (ball == 0) {
            return strike + "스트라이크";
        }
        return ball + "볼 " + strike + "스트라이크";
    }
}

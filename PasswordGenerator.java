public class PasswordGenerator {

    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL = "!@#$%^&*";

    public static String generatePassword(
            int length, long seed, boolean useUppercase, boolean useDigits, boolean useSpecial) {
        String alphabet = LOWERCASE;

        if (useUppercase) {
            alphabet += UPPERCASE;
        }
        if (useDigits) {
            alphabet += DIGITS;
        }
        if (useSpecial) {
            alphabet += SPECIAL;
        }

        int len = alphabet.length();
        long current = seed;
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < length; i++) {
            current = nextRandom(current);
            int index = (int) (current % len);
            result.append(alphabet.charAt(index));
        }

        return result.toString();
    }

    public static String checkPassword(String password) {
        int score = 0;

        if (password.length() >= 8) {
            score++;
        }

        boolean hasLower = false;
        boolean hasUpper = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for (char c : password.toCharArray()) {
            if (!hasLower && LOWERCASE.indexOf(c) >= 0) {
                hasLower = true;
                score++;
            } else if (!hasUpper && UPPERCASE.indexOf(c) >= 0) {
                hasUpper = true;
                score++;
            } else if (!hasDigit && DIGITS.indexOf(c) >= 0) {
                hasDigit = true;
                score++;
            } else if (!hasSpecial && SPECIAL.indexOf(c) >= 0) {
                hasSpecial = true;
                score++;
            }

            if (hasLower && hasUpper && hasDigit && hasSpecial) {
                break;
            }
        }

        String verdict;
        if (score <= 2) {
            verdict = "Слабый";
        } else if (score == 3) {
            verdict = "Средний";
        } else if (score == 4) {
            verdict = "Надёжный";
        } else {
            verdict = "Очень надёжный";
        }

        return verdict + " пароль (оценка " + score + " из 5)";
    }

    private static long nextRandom(long number) {
        return (16807L * number) % 2147483647L;
    }
}

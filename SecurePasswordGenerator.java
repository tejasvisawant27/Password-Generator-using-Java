import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SecurePasswordGenerator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your favorite Things(comma-separated): ");
        String favoriteThingsInput = scanner.nextLine();

        System.out.print("Enter the desired password length: ");
        int passwordLength = scanner.nextInt();
        List<String> favoriteThings = new ArrayList<>();
        for (String thing : favoriteThingsInput.split(",")) {
            favoriteThings.add(thing.trim());
        }

        String generatedPassword = generatePassword(name, favoriteThings, passwordLength);

        System.out.println("Generated Password: " + generatedPassword);

        scanner.close();
    }

    private static String generatePassword(String name, List<String> favoriteThings, int passwordLength) {
        SecureRandom random = new SecureRandom();
        StringBuilder passwordBuilder = new StringBuilder();

        // Add parts of the name and favorite things
        passwordBuilder.append(name.toLowerCase().replaceAll("\\s", ""));
        for (String thing : favoriteThings) {
            passwordBuilder.append(thing.toLowerCase().replaceAll("\\s", ""));
        }

        // Add random characters
        String randomCharacters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()-_=+";
        while (passwordBuilder.length() < passwordLength) {
            int randomIndex = random.nextInt(randomCharacters.length());
            passwordBuilder.append(randomCharacters.charAt(randomIndex));
        }

        // Shuffle the characters in the password
        String shuffledPassword = shuffleString(passwordBuilder.toString());
        return shuffledPassword.substring(0, passwordLength);
    }

    private static String shuffleString(String input) {
        List<Character> characters = new ArrayList<>();
        for (char c : input.toCharArray()) {
            characters.add(c);
        }
        StringBuilder shuffled = new StringBuilder(input.length());
        while (!characters.isEmpty()) {
            int randPicker = (int) (Math.random() * characters.size());
            shuffled.append(characters.remove(randPicker));
        }
        return shuffled.toString();
    }
}
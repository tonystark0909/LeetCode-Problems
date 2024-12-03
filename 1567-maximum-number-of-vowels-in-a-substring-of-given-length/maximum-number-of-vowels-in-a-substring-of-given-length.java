class Solution {
    /**
     * Calculates the maximum number of vowels in any substring of length k.
     *
     * @param s The input string.
     * @param k The length of the substring.
     * @return The maximum number of vowels found in any substring of length k.
     */
    public int maxVowels(String s, int k) {
        // Initialize the total vowel count for the first window of size k
        int totalVowelsInWindow = 0, 
            stringLength = s.length();

        // Count the number of vowels in the initial window of size k
        for (int i = 0; i < k; ++i) {
            if (isVowel(s.charAt(i))) {
                ++totalVowelsInWindow;
            }
        }

        // Initialize the answer with the vowel count of the first window
        int maxVowels = totalVowelsInWindow;

        // Slide the window of size k across the string
        for (int i = k; i < stringLength; ++i) {
            // If the newly included character is a vowel, increase the count
            if (isVowel(s.charAt(i))) {
                ++totalVowelsInWindow;
            }

            // If the character that got excluded from the window is a vowel, decrease the count
            if (isVowel(s.charAt(i - k))) {
                --totalVowelsInWindow;
            }

            // Update maxVowels if the current window has more vowels than the previous ones
            maxVowels = Math.max(maxVowels, totalVowelsInWindow);
        }

        // Return the maximum number of vowels found
        return maxVowels;
    }

    /**
     * Helper method to check if a character is a vowel.
     *
     * @param character The character to be checked.
     * @return true if the character is a vowel, false otherwise.
     */
    private boolean isVowel(char character) {
        // A character is a vowel if it is one of 'a', 'e', 'i', 'o', or 'u'
        return character == 'a' || 
               character == 'e' || 
               character == 'i' || 
               character == 'o' || 
               character == 'u';
    }
}
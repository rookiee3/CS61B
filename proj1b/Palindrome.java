public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        LinkedListDeque<Character> deque = new LinkedListDeque<>();
        if (word == null) {
            return null;
        } else {
            for (int i = 0; i < word.length(); i++) {
                deque.addLast(word.charAt(i));
            }
        }
        return deque;
    }

    public boolean isPalindrome(String word) {
        Palindrome palindrome = new Palindrome();
        Deque d = palindrome.wordToDeque(word);
        //选择分支
        int swi = word.length();
        if (swi == 0 || swi == 1) {
            return true;
        } else if (swi % 2 == 0) { //偶数个字符
            while (!d.isEmpty()) {
                if (d.removeFirst() != d.removeLast()) {
                    return false;
                }
            }
        } else { //奇数个字符
            while (d.size() != 1) {
                if (d.removeFirst() != d.removeLast()) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Palindrome palindrome = new Palindrome();
        Deque d = palindrome.wordToDeque(word);
        //选择分支
        int swi = word.length();
        if (swi == 0 || swi == 1) {
            return true;
        } else if (swi % 2 == 0) { //偶数个字符
            while (!d.isEmpty()) {
                if (!cc.equalChars((char)d.removeFirst(), (char)d.removeLast())) {
                    return false;
                }
            }
        } else { //奇数个字符
            while (d.size() != 1) {
                if (!cc.equalChars((char)d.removeFirst(), (char)d.removeLast())) {
                    return false;
                }
            }
        }
        return true;
    }
}

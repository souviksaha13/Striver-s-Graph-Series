/*
 * Problem Link ::
 *      https://www.geeksforgeeks.org/problems/word-ladder/1
 */

import java.util.*;

class Solution {
    
    class Pair {
        String word;
        Integer steps;
        
        public Pair(String word, Integer steps) {
            this.word = word;
            this.steps = steps;
        }
    }
    
    public int wordLadderLength(String startWord, String targetWord, String[] wordList)
    {
        // Code here
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(startWord, 1));
        
        Set<String> hs = new HashSet<>();
        for(String word : wordList) {
            hs.add(word);
        }
        
        while(!q.isEmpty()) {
            String word = q.peek().word;
            int steps = q.peek().steps;
            q.poll();
            
            if(word.equals(targetWord)) return steps;
            
            for(int i=0; i<word.length(); i++) {
                char[] wordArray = word.toCharArray();
                
                for(char ch = 'a'; ch <= 'z'; ch++) {
                    wordArray[i] = ch;
                    String replacedWord = new String(wordArray);
                    
                    if(hs.contains(replacedWord)) {
                        q.offer(new Pair(replacedWord, steps+1));
                        hs.remove(replacedWord);
                    }
                }
            }
        }
        
        // case when it is not possible to get the targetWord
        return 0;
    }
}
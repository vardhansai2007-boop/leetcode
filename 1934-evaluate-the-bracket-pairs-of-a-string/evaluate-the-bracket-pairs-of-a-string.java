class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> map = new HashMap<>();
        
        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }

        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            
            if (s.charAt(i) == '(') {
                i++; 
                
                StringBuilder key = new StringBuilder();
                
              
                while (s.charAt(i) != ')') {
                    key.append(s.charAt(i));
                    i++;
                }

                // Get value from map
                if (map.containsKey(key.toString())) {
                    ans.append(map.get(key.toString()));
                } else {
                    ans.append("?");
                }
                
            } else {
                ans.append(s.charAt(i));
            }
        }

        return ans.toString();
        
    }
}
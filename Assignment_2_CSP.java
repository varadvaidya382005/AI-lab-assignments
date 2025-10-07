import java.util.*;

class MapColoringCSP {
    static Map<String, List<String>> neighbors = new HashMap<>();
    static Map<String, String> colors = new HashMap<>();
    static String[] availableColors = {"Red", "Green", "Blue"};

    static boolean isSafe(String state, String color){
        for(String nb : neighbors.get(state)){
            if(colors.containsKey(nb) && colors.get(nb).equals(color)) return false;
        }
        return true;
    }

    static boolean solve(List<String> states, int idx){
        if(idx == states.size()) return true;
        String state = states.get(idx);
        for(String c : availableColors){
            if(isSafe(state,c)){
                colors.put(state,c);
                if(solve(states,idx+1)) return true;
                colors.remove(state);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        neighbors.put("MH", Arrays.asList("MP","GJ","KA"));
        neighbors.put("MP", Arrays.asList("MH","UP"));
        neighbors.put("UP", Arrays.asList("MP"));
        neighbors.put("GJ", Arrays.asList("MH"));
        neighbors.put("KA", Arrays.asList("MH"));

        List<String> states = new ArrayList<>(neighbors.keySet());
        if(solve(states,0)) System.out.println(colors);
        else System.out.println("No solution");
    }
}

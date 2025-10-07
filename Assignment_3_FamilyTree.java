import java.util.*;

class FamilyTree {
    static Map<String, List<String>> parent = new HashMap<>();

    static void addParent(String p, String c){
        parent.computeIfAbsent(p, k -> new ArrayList<>()).add(c);
    }

    static boolean isParent(String p, String c){
        return parent.containsKey(p) && parent.get(p).contains(c);
    }

    static List<String> getChildren(String p){
        return parent.getOrDefault(p,new ArrayList<>());
    }

    static List<String> getSiblings(String c){
        List<String> sibs = new ArrayList<>();
        for(String p : parent.keySet()){
            if(parent.get(p).contains(c)){
                for(String other : parent.get(p)){
                    if(!other.equals(c)) sibs.add(other);
                }
            }
        }
        return sibs;
    }

    public static void main(String[] args) {
        addParent("Ram","Sita");
        addParent("Ram","Laxman");
        addParent("Geeta","Sita");
        
        System.out.println("Children of Ram: " + getChildren("Ram"));
        System.out.println("Siblings of Sita: " + getSiblings("Sita"));
    }
}
/// RAM -> Sita -> Geeta
///     -> Laxman
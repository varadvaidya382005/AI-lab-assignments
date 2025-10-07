import java.util.*;

class Puzzle8 {
    static String goal = "123804765"; // Example Goal State

    static List<String> getNeighbors(String state) {
        List<String> neighbors = new ArrayList<>();
        int zero = state.indexOf('0');
        int row = zero / 3, col = zero % 3;
        int[] dr = {-1,1,0,0}, dc = {0,0,-1,1};

        for(int i=0;i<4;i++){
            int nr=row+dr[i], nc=col+dc[i];
            if(nr>=0 && nr<3 && nc>=0 && nc<3){
                int newPos = nr*3+nc;
                char[] arr = state.toCharArray();
                arr[zero] = arr[newPos];
                arr[newPos] = '0';
                neighbors.add(new String(arr));
            }
        }
        return neighbors;
    }

    static void BFS(String start){
        Queue<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        q.add(start);
        visited.add(start);

        while(!q.isEmpty()){
            String curr = q.poll();
            System.out.println("Visiting: " + curr);
            if(curr.equals(goal)) { System.out.println("Goal found!"); return; }
            for(String n : getNeighbors(curr)){
                if(!visited.contains(n)){
                    visited.add(n);
                    q.add(n);
                }
            }
        }
    }

    static void DFS(String start){
        Stack<String> st = new Stack<>();
        Set<String> visited = new HashSet<>();
        st.push(start);
        visited.add(start);

        while(!st.isEmpty()){
            String curr = st.pop();
            System.out.println("Visiting: " + curr);
            if(curr.equals(goal)) { System.out.println("Goal found!"); return; }
            for(String n : getNeighbors(curr)){
                if(!visited.contains(n)){
                    visited.add(n);
                    st.push(n);
                }
            }
        }
    }

    public static void main(String[] args) {
        String start = "123045678"; // Initial state
        System.out.println("BFS:");
        BFS(start);
        System.out.println("\nDFS:");
        DFS(start);
    }
}

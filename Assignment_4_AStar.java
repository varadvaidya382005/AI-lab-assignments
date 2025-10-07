import java.util.*;

class AStar {
    static class Node {
        int x,y,g,h;
        Node parent;
        Node(int x,int y,int g,int h,Node p){this.x=x;this.y=y;this.g=g;this.h=h;this.parent=p;}
        int f(){ return g+h; }
    }

    static int heuristic(int x,int y,int gx,int gy){
        return Math.abs(x-gx)+Math.abs(y-gy); // Manhattan
    }

    static void AStarSearch(int sx,int sy,int gx,int gy,int n,int m){
        PriorityQueue<Node> open = new PriorityQueue<>(Comparator.comparingInt(Node::f));
        boolean[][] closed = new boolean[n][m];
        open.add(new Node(sx,sy,0,heuristic(sx,sy,gx,gy),null));

        while(!open.isEmpty()){
            Node curr = open.poll();
            if(curr.x==gx && curr.y==gy){
                System.out.println("Path found:");
                while(curr!=null){
                    System.out.print("("+curr.x+","+curr.y+") <- ");
                    curr=curr.parent;
                }
                return;
            }
            closed[curr.x][curr.y]=true;
            int[] dx={1,-1,0,0}, dy={0,0,1,-1};
            for(int i=0;i<4;i++){
                int nx=curr.x+dx[i], ny=curr.y+dy[i];
                if(nx>=0 && ny>=0 && nx<n && ny<m && !closed[nx][ny]){
                    open.add(new Node(nx,ny,curr.g+1,heuristic(nx,ny,gx,gy),curr));
                }
            }
        }
        System.out.println("No path found");
    }

    public static void main(String[] args) {
        AStarSearch(0,0,3,3,5,5);
    }
}

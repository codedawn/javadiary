```
class Solution {
    public int movingCount(int m, int n, int k) {

        Queue<Pair> queue=new LinkedList<Pair>();
        queue.offer(new Pair(0,0));
        int count=0;
        int[][] f=new int[m][n];
        f[0][0]=1;
        while(queue.size()>0){
            Pair p=queue.poll();
            System.out.println(p);
            if(p.getSum()<=k){
                 count++;
                if(p.x+1<m&&(f[p.x+1][p.y]!=1)){
                    queue.offer(new Pair(p.x+1,p.y));   
                    f[p.x+1][p.y]=1;
                }
                    
                if(p.y+1<n&&(f[p.x][p.y+1]!=1)){
                    queue.offer(new Pair(p.x,p.y+1));
                     f[p.x][p.y+1]=1;
                }
                   
            }
        }
        return count;
    }
    
    private class Pair{
        public int x;
        public int y;

        public Pair(int x,int y){
            this.x=x;
            this.y=y;
        }
        public int getSum(){
            int re=0;
            int tx=x;
            int ty=y;
            while(tx>0){
                re=re+tx%10;
                tx/=10;
            }
             while(ty>0){
                re=re+ty%10;
                ty/=10;
            }
            return re;
        }
        public String toString(){
            return x+"-"+y;
        }
    }
}
```



使用dfs，广度优先算法
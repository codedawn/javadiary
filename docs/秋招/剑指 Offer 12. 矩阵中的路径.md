```
class Solution {
    public boolean exist(char[][] board, String word) {
        char[] w=word.toCharArray();
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(dfs(board,i,j,0,w)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean dfs(char[][] board,int i,int j,int k,char[] words){
        if(i<0||i>=board.length)return false;
        if(j<0||j>=board[0].length)return false;
        if(board[i][j]!=words[k])return false;
        if(words.length-1==k) return true;
        board[i][j]='\0';
        boolean re=dfs(board,i-1,j,k+1,words)||dfs(board,i+1,j,k+1,words)||dfs(board,i,j-1,k+1,words)||dfs(board,i,j+1,k+1,words);
        board[i][j]=words[k];
        return re;
        
    }
}
```

 遍历所有元素，所为起点，使用dfs，深度优先算法，
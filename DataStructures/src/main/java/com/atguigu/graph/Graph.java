package com.atguigu.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class Graph {
    public static void main(String[] args) {
        //测试一把图是否创建 ok
        int n = 5 ;
        String vertextValue[] ={"A","B","C","D","E"};
        //创建图对象
        Graph graph = new Graph(n);
        //循环的添加顶点
        for (String value : vertextValue) {
            graph.insetVertex(value);
        }
        //添加边
        // A-B  A-C  B-C B-D  B-E
        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);
        //显示一把邻接矩阵
        graph.showGraph();
        System.out.println("****************************");

        System.out.println("深度遍历，测试一把");
        graph.dfs();
        System.out.println();
        System.out.println("***************************");
        System.out.println("广度优先");
        graph.isVisited = new boolean[5];
        graph.bfs();
    }

    private ArrayList<String> vertexList;//存储顶点集合
    private int[][] edges;// 存储图对应的邻接矩阵
    private int numOfEdges ;//表示变的树目
    //定义一个数组boolean[],记录某个节点是否被访问过
    private boolean[] isVisited;

    /**
     *
     * @param n  表示顶点的个数
     */
    public Graph(int n ){
        // 初始化矩阵 和 vertext
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);
        numOfEdges = 0;
        isVisited = new boolean[n];
    }


    /**
     * 得到第一个邻接节点的下标
     * @param index  当前节点的下标
     * @return  如果存在，则返回对应的下标，否则返回 -1
     */
    public int getFirstNeighbor(int index){
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] > 0){
                return  j;
            }
        }
        return  -1 ;
    }

    /**
     * 根据前一个邻接节点的下标取下一个邻接节点
     * @param v1 当前节点
     * @param v2  前一个节点
     * @return
     */
    public int getNextNeighbor(int v1,int v2){
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0){
                return j;
            }
        }
        return  -1;
    }

    /**
     * 遍历所有的节点，都进行广度优先搜索
     */
    public void bfs(){
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]){
                bfs(isVisited,i);
            }
        }
    }

    /**'
     * 对一个节点广度优先遍历的方法
     * @param isVisited
     * @param i
     */
    public void bfs(boolean[] isVisited,int i){
        int u;//表示队列的投节点对应的下标
        int w;//表示邻接节点的下标
        //队列，记录节点访问顺序
        LinkedList<Integer> queue = new LinkedList<Integer>();
        //访问节点
        System.out.printf(getValueByIndex(i) + "==>");
        //标记为已经访问过
        isVisited[i] =true;
        //将这个节点加入队列
        queue.addLast(i);

        while (!queue.isEmpty()){
            //取出队列的头结点下标
             u = queue.removeFirst();
            //得到 第一个邻接点的下标 w
            w = getFirstNeighbor(u);
            while (w != -1){ // 找到
                //判断是否访问过
                if (!isVisited[w]){
                    //没有访问过
                    System.out.printf(getValueByIndex(w) + "==>");
                    //标记为已经访问过
                    isVisited[w] = true;
                    //入队列
                    queue.addLast(w);
                }
                //如果已经访问，那么就以 u 为前驱节点，找出 w 后面的下一个邻接点
                w = getNextNeighbor(u,w); // 体现出我们的广度优先
            }
        }
    }


    /**
     * 深度优选算法
     * @param isVisited
     * @param i  第一次就是 0
     */
    public void dfs(boolean[] isVisited,int i){
        //首先访问的就是该节点,输出
        System.out.printf(getValueByIndex(i) + "--->");
        //将改点设置为已经访问过
        isVisited[i] = true;
        //查找点 i 的第一个邻接点
        int w = getFirstNeighbor(i);
        while (w != -1){ // 说明有
            if (!isVisited[w]){
                dfs(isVisited,w);
            }
            //如果w 节点已经被访问过，我们就应该查找邻接点的下一个邻接点
            w = getNextNeighbor(i,w);
        }
    }

    /**
     * 对dfs进行重载,遍历所有的节点，并进行dfs
     */
    public void dfs(){
        //遍历所有的节点，并进行dfs 【回溯】
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]){
                dfs(isVisited,i);
            }
        }
    }


    /**
     * 插入节点
     * @param vertext
     */
    public void insetVertex(String vertext){
        vertexList.add(vertext);
    }

    /**
     *功能：添加边
     * @param v1 表示点的下标, 即 v1 表示第几个顶点  "A" - "B"  "A"  -> 0  "B" -> 1
     * @param v2   v2 表示第二个顶点对应的下标
     * @param weight  边的权值
     */
    public void insertEdge(int v1,int v2,int weight){
        edges[v1][v2] = weight ;
        edges[v2][v1] = weight;
        numOfEdges++ ;
    }

    /**
     *
     * @return  返回节点（顶点）的个数
     */
    public int getNumOfVertex(){
        return vertexList.size();
    }

    /**
     * 得到边的数目
     * @return
     */
    public int getNumOfEdges(){
        return numOfEdges ;
    }

    /**
     * 返回节点 i（节点也称为顶点） 对应的数据
     * @param i
     * @return
     */
    public String  getValueByIndex(int i){
        return vertexList.get(i);
    }

    /**
     * 返回边 的权值
     * @param v1
     * @param v2
     * @return
     */
    public int getWeight(int v1,int v2){
        return edges[v1][v2];
    }

    public void showGraph(){
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }


}




































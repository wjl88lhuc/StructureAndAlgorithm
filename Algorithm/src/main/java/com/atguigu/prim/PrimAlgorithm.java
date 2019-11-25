package com.atguigu.prim;

import java.util.Arrays;

public class PrimAlgorithm {
    public static void main(String[] args) {
        //测试图是否创建成功
        char[] data = new char[]{'A','B','C','D','E','F','G'};
        int verxs = data.length;
        //邻接矩阵的关系 使用二维数组表示,使用10000 表示两个点不是连通的
        int[][] weight = new int[][]{
                {10000,5,7,10000,10000,10000,2},
                {5,10000,10000,9,10000,10000,3},
                {7,10000,10000,10000,8,10000,10000},
                {10000,9,10000,10000,10000,4,10000},
                {10000,10000,8,10000,10000,5,4},
                {10000,10000,10000,4,5,10000,6},
                {2,3,10000,10000,4,6,10000}
        };

        //创建一个Magraph的对象
        MGraph graph = new MGraph(verxs);
        //创建一个MinTree对象
        MinTree minTree = new MinTree();
        minTree.createGraph(graph,verxs,data,weight);
        //输出
        minTree.show(graph);

        //测试普里姆算法
        minTree.prim(graph,0);
    }
}

//创建最小生成树 ---> 村庄的图
class MinTree{
    //创建图的邻接矩阵

    /**
     *
     * @param graph  图对象
     * @param verxs 图对应的顶点个数
     * @param data  图的各个顶点的值
     * @param weight  图的邻接矩阵
     */
    public void createGraph(MGraph graph,int verxs,char data[],int[][] weight){
        int i,j;
        for ( i = 0; i < verxs; i++) {//遍历顶点
            graph.data[i] = data[i];
            for (j = 0; j < verxs; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    //图的显示方法
    public void show(MGraph graph){
        for (int[] link : graph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * 编写prim算法，得到最小生成树
     * @param graph  图
     * @param v  表示从图的第几个顶点生成'A' -->0  'B' --> 1
     */
    public void prim(MGraph graph,int v){
        // visited 数组表示标记节点是否已经被访问过。默认元素的值都所示 0 ，表示没有访问过。
        int[] visited = new int[graph.verxs];
//        for (int i = 0; i < graph.verxs; i++) {
//            visited[0] = 0 ;
//        }
        //把当前这个节点标记为已经被访问过
        visited[v] = 1;
        // h1  h2 记录两个顶点的下标
        int h1 = -1;
        int h2 = -1;
        int minWeight = 10000;//将 minWeight初始化成为一个大数，后面遍历的过程中，会被替换
        for (int k = 1; k < graph.verxs; k++) {//因为 graph.verxs 顶点，就会有（ graph.verxs - 1 ）条边
            //这个是确定每一次生成的子图 与 哪个节点的距离最近
            for (int i = 0; i < graph.verxs; i++) { // i 节点表示被访问过的节点
                for (int j = 0; j < graph.verxs; j++) { // j 表示还没有访问过的节点
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight){
                        //替换minWeight（寻找已经访问的节点与未访问过的节点之间的权值最小的边）
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2=j;
                    }
                }
            }
            //每一次遍历一条已经被访问过的顶点，就找到一条哦最小的边
            System.out.println("边<"+graph.data[h1]+"," + graph.data[h2] +"> 权值：" + minWeight );
            //将当前这个节点标记为已经访问过
            visited[h2] = 1;
            //将minWeight 重新设置为最大值 10000
            minWeight =10000;
        }
    }
}

class MGraph{
    int verxs;//表示图的节点个数
    char[] data;//存放节点数据
    int[][] weight;//存放边，就是我们的邻接矩阵

    public MGraph(int verxs){
        this.verxs = verxs;
        data = new char[verxs];
        weight = new int[verxs][verxs];
    }
}




























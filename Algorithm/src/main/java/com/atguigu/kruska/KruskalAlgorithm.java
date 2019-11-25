package com.atguigu.kruska;

import java.util.Arrays;

public class KruskalAlgorithm {
    private int edgeNum;//边的个数
    private char[] vertexs;//顶点数组
    private int[][] matrix;//邻接矩阵
    //使用INF 表示 两个顶点不能连通
    private static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) {
        //测试初始化及其打印
        char[] vertexs = {'A','B','C','D','E','F','G'};
        //克鲁斯卡尔算法的邻接矩阵
        int matrix[][] = {
                /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                /*A*/ {   0,  12, INF, INF, INF,  16,  14},
                /*B*/ {  12,   0,  10, INF, INF,   7, INF},
                /*C*/ { INF,  10,   0,   3,   5,   6, INF},
                /*D*/ { INF, INF,   3,   0,   4, INF, INF},
                /*E*/ { INF, INF,   5,   4,   0,   2,   8},
                /*F*/ {  16,   7,   6, INF,   2,   0,   9},
                /*G*/ {  14, INF, INF, INF,   8,   9,   0}};
        //创建KruskalCase 对象实例
        KruskalAlgorithm kruskalCase = new KruskalAlgorithm(vertexs, matrix);
        //输出构建的
        kruskalCase.print();
        System.out.println("**********************************************************************************");
        EData[] edges = kruskalCase.getEdges();
        kruskalCase.sortEdges(edges);
        System.out.println(Arrays.toString(edges));
        kruskalCase.kruskal();
    }

    //构造函数
    public KruskalAlgorithm(char[] vertexs, int[][] matrix) {
        //初始化顶点数和边的个数
        int vlen = vertexs.length;
        
        //初始化顶点
        this.vertexs = new char[vlen];
        for (int i = 0; i < vertexs.length; i++) {
            this.vertexs[i] = vertexs[i];
        }
        this.matrix = matrix;

        //初始化边,使用的是复制拷贝的方式
        this.matrix = new int[vlen][vlen];
        for (int i = 0; i < vlen; i++) {
            for (int j = 0; j < vlen; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }

        //统计边的数量
        for (int i = 0; i < vlen; i++) {
            for (int j = i + 1; j < vlen; j++) {
                if (this.matrix[i][j] != INF){//表示这一条是有效的
                    edgeNum++;
                }
            }
        }
    }

    public void kruskal(){
        int index = 0;//表示最好结果数组的索引
        int[] ends = new int[edgeNum];//用于保存”已有最小生成树“中的每个顶点在最小生成树中的终点
        //创建 结果数组，保存最好的最小生成树
        EData[] rets = new EData[edgeNum];

        //获取图中的所有的边的集合
        EData[] edges = getEdges();
        //按照边的权值大小进行排序
        sortEdges(edges);

        //遍历 edges 数组,将边添加到最小生成树中时，判断是准备加入的边是否形成了回路，如果没有，就加入rets,否则不能加入
        for (int i = 0; i < edgeNum; i++) {
            //获取到第 i 条边的第一个顶点
            int p1 = getPosition(edges[i].start);
            //获取到第 i 条边的第二个顶点
            int p2 = getPosition(edges[i].end);

            //获取p1 这个顶点在已有最小生成树中的终点
            int m = getEnd(ends, p1);
            //获取p2 这个顶点在已有最小生成树中的终点
            int n = getEnd(ends, p2);
            //是否构成回路
            if (m != n){//没有构成回路
                ends[m] = n;//设置 m  在"已有最小生成树中"的终点
                rets[index++]=edges[i];//有一条边加入到rets数组中
            }
        }

        //统计并打印最小生成树,输出 rets
        System.out.println("最小生成树为： " + Arrays.toString(rets));

    }

    //打印邻接矩阵
    public void print(){
        System.out.println("邻接矩阵为: \n");
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                System.out.printf("%10d\t",matrix[i][j]);
            }
            System.out.println();
        }
    }

    //对边进行排序处理，冒泡
    public void sortEdges(EData[] edges){
        EData temp = null;
        for (int i = 0; i <edges.length - 1 ; i++) {
            for (int j = 0; j < edges.length - 1 - i; j++) {
                if (edges[j].weight > edges[j + 1].weight){
                    temp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = temp;
                }
            }
        }
    }

    //编写一个方法

    /**
     *
     * @param ch 顶点的值，比如 'A','B'
     * @return  返回ch 顶点对应的下标，找不到就返回 -1
     */
    public int getPosition(char ch){
        for (int i = 0; i < vertexs.length; i++) {
            if (vertexs[i] == ch){
                return i;//找到
            }
        }
        return -1 ;//找不到
    }

    /**
     * 功能：获取途中的边，放到  EData[] 数组中，后面我们需要遍历该数组
     * 是通过 martix 邻接矩阵来获取
     * EData[]  形式[['A','B',12],['B','F',7],....]
     * @return
     */
    public EData[] getEdges(){
        int index = 0;
        EData[] edges = new EData[edgeNum];
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = i + 1; j < vertexs.length; j++) {
                if (matrix[i][j] != INF){
                    edges[index++] = new EData(vertexs[i],vertexs[j],matrix[i][j]);
                }
            }
        }
        return edges;
    }

    /**
     * 功能： 获取下标是 i 的顶点的终点，用于后面判断两个顶点的终点是否相同
     * @param ends  记录了各个顶点的终点是哪个的数组，该数组是在遍历过程中逐步形成的
     * @param i 传入的顶点下标
     * @return  返回的就是下标为 i 的这个顶点对应的终点的下标
     */
    public int getEnd(int[] ends,int i){
        while (ends[i] != 0){
            i = ends[i];
        }
        return i;
    }

}

//创建一个Edata类，他的对象实例就是一条边
class EData{
    char start;//边的起点
    char end;//边的另外一个点
    int weight;//边的权值

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    //重写toString方法，便于输出边的信息

    @Override
    public String toString() {
        return "EData[ start=" + start + ", end=" + end + ", weight=" + weight + ']';
    }

}




























































































package com.atguigu.recusion;
//迷宫问题
public class MiGong {
    public static void main(String[] args) {
        //先创建一个二维数组，模拟迷宫
        //地图
        int[][] map = new int[8][7];//8行7列
        //使用1 表示墙，上下全部置为 1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }

        //左右全部置为 1
        for (int i = 0; i <8 ; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //设置中间的半个墙
        map[3][1] = map[3][2] = 1;
        //输出地图
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7 ; j++) {
                System.out.print(map[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("******");

        //使用递归回溯给小球找路
        setWay(map,1,1);
        //输出新地图，小球走过，并表示过的递归
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7 ; j++) {
                System.out.print(map[i][j] + "\t");
            }
            System.out.println();
        }

    }

    /**
     * 使用递归回溯来给小球找路
     * (i,j)表示从哪里开始找路，这里是(1,1);
     * 如果小球能找到map[6][5]位置，则说明通路找到了。
     * 约定：当map[i][j]为 0 表示该点没有走过，当为 1 表墙，如果为 2 表示通路可以走；3 表示该店已经走过，但是走不通。
     * 在走迷宫时，需要确定一个策略（方法） 下 -> 右 ->上 -> 左，如果改点走不通再回溯
     *
     * 实际应用： 如何求出最短路径
     * @param map 表示地图
     * @param i
     * @param j
     * @return
     */
    public static boolean setWay(int[][] map,int i ,int j){
        if (map[6][5] == 2){//map[6][5] 通路已经找到了，即 已经找到终点。map[6][5] 就是终点
            return  true;
        }
        if (map[i][j] == 0){//如果当前这个点没有走过,就按照先下，如果下路走不通，再右路尝试，如果右路走不通，再尝试上路，如果上路走不通再左路，即 下 -> 右 ->上 -> 左
            map[i][j] = 2;//假定改点是可以走通的
            if (setWay(map,i + 1,j)){//如果在假定这个点走通的情况下，尝试向“下”走能够走通，那么这一点就是真的走通了
                return true;
            }
            if (setWay(map,i,j+ 1)){//如果在假定这个点走通的情况下，尝试向 “右”走能够走通，那么这一点就是真的走通了
                return  true;
            }
            if (setWay(map,i -1 ,j)){//如果在假定这个点走通的情况下，尝试向 “上”走能够走通，那么这一点就是真的走通了
                return  true;
            }
            if (setWay(map,i,j -1)){//如果在假定这个点走通的情况下，尝试向 “左”走能够走通，那么这一点就是真的走通了
                return  true;
            }
            map[i][j] = 3;
            return false;
        }
        //如果map[i][j]不等于 0 ，那么可能是的 值 1 ，2，3 ，直接返回 false 即可。
        // 因为判断一个 点能不能走得通是在 map[i][j] = 0 的时候才判断的，其他状态都不能判断（除了终点之外）
        return false;
    }

}































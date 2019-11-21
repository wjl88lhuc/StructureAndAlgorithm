package com.atguigu.tree.huffcode;

import scala.math.Ordering;

import java.io.*;
import java.util.*;

public class HuffmanCode {
    public static void main(String[] args) {
        /*
        String content = "i like like like java do you like a java";
        System.out.println("content的长度 ： " + content.length());
        byte[] contentBytes = content.getBytes();
        byte[] huffmanCodeBytes = huffmanZip(contentBytes);
        System.out.println("huffmanCodeBytes = " + Arrays.toString(huffmanCodeBytes) + " \t 长度为 " + huffmanCodeBytes.length);
        System.out.println(new String(decode(huffmanCodes, huffmanCodeBytes)));
        */

        //测试对文件的压缩
//        String srcFile = "f://buma.png";
//        String dstFile = "f://dst.zip";
//        zipFile(srcFile,dstFile);
//        System.out.println("压缩成功");

        //测试解压文件
        String zipFile = "f://dst.zip";
        String dstFile = "f://buma2.png";
        unzipFile(zipFile,dstFile);
        System.out.println("解压成功");
    }

    /**
     * 编写一个方法，完成对压缩文件的解压
     * @param zipFile  有待解压的文件
     * @param dstFile  要将文件解压到哪个文件中
     */
    public static void unzipFile(String zipFile,String dstFile){
        //定义文件的输入流
        InputStream is = null;
        //定义一个对象输入流
        ObjectInputStream ois = null;

        //定义一个文件的输出流
        OutputStream os = null;
        try {
            //创建文件的输入流
            is = new FileInputStream(zipFile);
            //创建一个与 is关联的对象输入流
            ois = new ObjectInputStream(is);
            //读取byte数组  huffmanBytes
            byte[] huffmanBytes = (byte[])ois.readObject();

            //读取赫夫曼编码表
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();

            //解码
            byte[] bytes = decode(huffmanCodes, huffmanBytes);

            //将bytes数组写入到目标文件中
            os = new FileOutputStream(dstFile);
            //把数据写入到dstFile文件中即可
            os.write(bytes);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            try {
                if (os != null ){
                    os.close();
                }
                if (ois != null){
                    ois.close();
                }
                if (is != null) {
                    is.close();
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * 编写一个方法，将一个文件进行压缩
     *
     * @param srcFile 希望压缩的文件的全路径
     * @param dstFile 压缩后将文件存放在哪个目录
     */
    public static void zipFile(String srcFile, String dstFile) {
        //创建输出流
        OutputStream outputStream = null;
        ObjectOutputStream obj = null;
        //创建文件的输入流
        FileInputStream is = null;

        try {
            is = new FileInputStream(srcFile);
            //创建一个和源文件大小一样的byte[]
            byte[] b = new byte[is.available()];
            //读取文件
            is.read(b);//读取的内容放在 b 字节数组中

            //直接源文件压缩
            byte[] huffmanBytes = huffmanZip(b);

            //创建文件的输出流，存放压缩文件
            outputStream = new FileOutputStream(dstFile);

            //创建一个和文件输出流关联的 ObjectOutPutStream
            obj = new ObjectOutputStream(outputStream);

            //把赫夫曼编码后的字节数组写入压缩文件
            obj.writeObject(huffmanBytes);

            //以对象流的方式写入赫夫曼编码，是为了以后我们恢复源文件时使用
            //注意一定把赫夫曼编码写入压缩文件
            obj.writeObject(huffmanCodes);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            try {
                if (is != null){
                    is.close();
                }
                if (obj != null){
                    obj.close();
                }
                if (outputStream != null){
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 编写一个方法，完成对压缩数据的解码
     *
     * @param huffmanCodes 哈夫曼编码表 map
     * @param huffmanBytes 哈夫曼编码得到的字节数组
     * @return 返回的就是原来的字符串对应的数组
     */
    public static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        //1. 先得到 huffmanBytes 对应的二进制的字符串,形式 10101000010111
        StringBuilder stringBuilder = new StringBuilder();
        //2. 将byte 数组转换成二进制的字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            //判断是不是最后一个字节
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, huffmanBytes[i]));
        }
        System.out.println("赫夫曼字节数组对应的二进制字符串 = " + stringBuilder.toString());
        //把字符串按照指定的赫夫曼编码进行解码
        //把赫夫曼编码表进行调换
        Map<String, Byte> map = new HashMap<String, Byte>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }

        //创建一个集合，存放我们的byte
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1;//小的计数器
            boolean flag = true;
            Byte b = null;
            while (flag) {
                //递增的取出 key
                String key = stringBuilder.substring(i, i + count);
                b = map.get(key);
                if (b == null) {//说明没有匹配到
                    count++;
                } else {
                    //匹配到
                    flag = false;
                }
            }
            list.add(b);
            i += count; //直接移动 count
        }
        //当for循环结束只会，我们的list中就存放了所有的字符。把list 中的数据放入到byte[]并返回
        byte[] b = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }

    /**
     * 将一个byte 转换成一个二进制的字符串
     *
     * @param flag 表示是否需要补高位，如果传入的 true 表示需要补高位，反之，表示不需要补高位。因为当传入的byte补足 8 位就不需要补高位了(最后一位可能就不足 8 位)
     * @param b    传入的byte 。
     * @return 是该 b 对应的二进制的字符串，注意（注意所示按照补码返回）
     */
    public static String byteToBitString(boolean flag, byte b) {
        int temp = b;//使用变量保存 b ,将b 转换成为 int
        //如果 是正数的话，我们还存在补高位
        if (flag) {
            temp |= 256; // temp |= 256  是按位与
        }
        String str = Integer.toBinaryString(temp);//返回的是temp对应的二进制的补码
        if (flag) {
            return str.substring(str.length() - 8);
        }
        return str;
    }

    /**
     * 使用一个方法将前面的方法封装起来，便于我们的调用
     *
     * @param bytes 原始的字符串对应的字节数组
     * @return 经过 赫夫曼编码处理后的字节数组（压缩后的数组）
     */
    public static byte[] huffmanZip(byte[] bytes) {
        List<Node> nodes = getNodes(bytes);
        //根据 nodes 创建赫夫曼树
        Node huffmanTreeRoot = createHuffmanTree(nodes);

        //对应的赫夫曼编码（根据赫夫曼树）
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);

        //根据生成的赫夫曼编码，压缩得到压缩后的赫夫曼编码字节数组
        return zip(bytes, huffmanCodes);
    }

    /**
     * 编写一个方法，将字符串对应的byte[] 数组，通过生成的赫夫曼编码表，返回一个赫夫曼编码压缩后的byte[]
     *
     * @param bytes        原始的字符串对应的byte[]
     * @param huffmanCodes huffmanCodes 生成的赫夫曼编码map
     * @return 返回赫夫曼编码处理后的byte[]
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        //利用huffmanCodes 将bytes 转换成赫夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        //遍历 bytes数组
        for (byte aByte : bytes) {
            stringBuilder.append(huffmanCodes.get(aByte));
        }
        //将stringBuilder的字符串转换成byte[]
        //统计返回 byte[] huffmanCodeBytes的长度
//        int len=stringBuilder.length() % 8 == 0 ? stringBuilder.length() % 8 : stringBuilder.length() % 8 + 1;
        int len = (stringBuilder.length() + 7) / 8;//等价于 int len=stringBuilder.length() % 8 == 0 ? stringBuilder.length() % 8 : stringBuilder.length() % 8 + 1;
        //创建存储压缩后的byte数组
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;//记录第几个 byte
        for (int i = 0; i < stringBuilder.length(); i += 8) {//因为是 每8 位对应一个byte ,所以步长 +8
            String strByte;
            if (i + 8 > stringBuilder.length()) {
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            //将strByte转换成一个byte，放入到huffmanCodeBytes
            huffmanCodeBytes[index++] = (byte) Integer.parseInt(strByte, 2);
        }
        return huffmanCodeBytes;
    }

    public static List<Node> getNodes(byte[] bytes) {
        List<Node> nodes = new ArrayList<>();

        //遍历 bytes ,统计每一个byte出现的次数 ->map[key,value]
        Map<Byte, Integer> counts = new HashMap<>();
        for (byte aByte : bytes) {
            Integer integer = counts.get(aByte);
            if (counts.containsKey(aByte) == false) {
                counts.put(aByte, 1);
            } else {
                counts.put(aByte, counts.get(aByte) + 1);
            }
        }
        //把每一个键值对转换成 Node 对象，并加入 nodes集合中
        for (Map.Entry<Byte, Integer> byteIntegerEntry : counts.entrySet()) {
            nodes.add(new Node(byteIntegerEntry.getKey(), byteIntegerEntry.getValue()));
        }
        return nodes;
    }

    public static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            //排序，从小到大
            Collections.sort(nodes);
            //取出第一颗最小的二叉树
            Node leftNode = nodes.get(0);
            //取出第二颗最小的二叉树
            Node rightNode = nodes.get(1);
            //创建一颗新的二叉树，他的根节点没有 data,只有权值
            Node parentNode = new Node(null, leftNode.weight + rightNode.weight);
            parentNode.left = leftNode;
            parentNode.right = rightNode;

            //将已经处理的两颗二叉树从 nodes删除
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //将新的二叉树加入到 nodes
            nodes.add(parentNode);
        }
        //返回结果。因为 nodes的最后只有一个元素
        return nodes.get(0);
    }

    //前序遍历的方法
    public static void preOrderList(Node root) {
        if (root == null) {
            return;
        }
        root.preOrderList();
    }

    /**
     * 生成赫夫曼对应的赫夫曼编码表
     * 思路：
     * 1. 将赫夫曼编码表存放在 Map<byte,String> 的形式
     * 2. 在生成赫夫曼编码表，需要区拼接路径，定义一个 StringBuilder 存储某个叶子节点的路径
     */
    static Map<Byte, String> huffmanCodes = new HashMap<Byte, String>();
    static StringBuilder stringBuilder = new StringBuilder();


    public static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }
        //处理左子树
        getCodes(root.left, "0", stringBuilder);
        //处理root的右子树
        getCodes(root.right, "1", stringBuilder);
        return huffmanCodes;
    }

    /**
     * 功能： 得到将传入的 node 节点的所有叶子节点的赫夫曼编码，并放入huffmanCodes集合中
     *
     * @param node          传入的节点
     * @param code          路径：左子节点为 0  ，右子节点为 1
     * @param stringBuilder 用于拼接路径
     */
    public static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        //将code 加入到 stringBuilder2
        stringBuilder2.append(code);
        if (node != null) {//如果node 等于 null 就不处理
            //判断当前node 是叶子节点还是非叶子节点
            if (node.data == null) {//说明是非叶子节点
                //递归处理
                getCodes(node.left, "0", stringBuilder2);//向左
                getCodes(node.right, "1", stringBuilder2);//向右
            } else {//说明是一个叶子节点
                //表示找到了某个叶子节点的最后
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }


    }


}

//创建Node ,存储数据和权值
class Node implements Comparable<Node> {
    Byte data;//存放数据（字符本身），比如 'a' -> 97
    int weight;//权值，表示字符出现的次数
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;//升序:从小到大排序
    }

    @Override
    public String toString() {
        return "Node[data=" + data + ", weight=" + weight + ']';
    }

    /**
     * 前序遍历
     */
    public void preOrderList() {
        if (this == null) {
            return;
        }
        if (this != null) {
            System.out.println(this);
        }
        if (this.left != null) {
            this.left.preOrderList();
        }
        if (this.right != null) {
            this.right.preOrderList();
        }
    }
}















































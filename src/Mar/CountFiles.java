package Mar;

import java.io.File;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

// 注意读写文件库
//  给定一个文件目录，写一个函数统计这个目录下所有的文件数量并返回
// 隐藏文件算，文件夹不算
public class CountFiles {
    public static int getFileNumber(String folderPath){
        File root = new File(folderPath);
        if (!root.isDirectory() && !root.isFile()) return 0;
        if (root.isFile()) return 1;

        Queue<File> queue = new LinkedList<>();
        queue.add(root);
        int fileNum = 0;
        while (!queue.isEmpty()){
            File folder = queue.poll();
            for (File next : folder.listFiles()){
                if (next.isDirectory()){
                    queue.add(next);
                } else if (next.isFile()){
                    fileNum ++;
                }
            }
        }

        return fileNum;

    }

    public static void main(String[] args) {
        // 你可以自己更改目录
        String path = "/Users/hanyiyu/Documents/algorithms/src";
        System.out.println(getFileNumber(path));
    }
}

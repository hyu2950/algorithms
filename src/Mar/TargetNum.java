package Mar;

import java.util.HashMap;

// leetcode 494
public class TargetNum {

    public static int findTargetSumWays1(int[] arr,int s){
        return process1(arr,0,s);
    }

    public static int process1(int[] arr, int index, int rest){
        if (index == arr.length){
            return rest == 0 ? 1: 0;  // count the number of method
        }
        return process1(arr,index + 1, rest - arr[index]) + process1(arr,index + 1, rest + arr[index]);
    }

    // 记忆化搜索的方法
    public static int findTargetSumWays2(int[] arr,int s){
        return process1(arr,0,s);
    }

    // use hashmap to store data
    // HashMap<Integer,HashMap<Integer,Integer>>
    //          index           rest --> value
    public static int process2(int[] arr, int index, int rest, HashMap<Integer,HashMap<Integer,Integer>> dp){
        if (dp.containsKey(index) && dp.get(index).containsKey(rest)){
            return dp.get(index).get(rest);
        }
        int ans = 0;

        if (index == arr.length){
            ans =  rest == 0 ? 1: 0;  // count the number of method
        } else {
            ans = process2(arr,index + 1, rest - arr[index],dp) + process2(arr,index + 1, rest + arr[index],dp);
        }
        if (!dp.containsKey(index)){
            dp.put(index,new HashMap<>());
        }
        dp.get(index).put(rest,ans);
        return ans;
    }

    // 严格位置依赖的方法
    // 1. 把所有复数变成正数
    // 2. 如果target > sum 则 结果一定是 0 种方法
    // 3. 如果target和sum 的奇偶性不一样， 结果一定是0 种方法
    // 4. 取正的数为集合P，取负的数为集合N
}

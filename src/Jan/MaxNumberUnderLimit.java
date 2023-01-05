package Jan;

import java.util.Arrays;

// 来自字节
// 输入:
// 去重数组arr，里面的数只包含0~9
// limit，一个数字
// 返回:
// 要求比limit小的情况下，能够用arr拼出来的最大数字
public class MaxNumberUnderLimit {

    public static int maxNumber(int arr[],int limit){
        Arrays.sort(arr);
        limit--; // turn the original problem into trying to find the res <= limit

        // use offset as the index of digits in limit
        // at first, the offset should point to the first digit in the limit number
        // eg limit = 98765 => offset = 10000, # of first digit = limit / offset = 9
        int offset = 1;
        while (offset * 10 <= limit){
            offset *= 10;
        }
        int ans = search(arr,limit,offset);
        // find the answer with same # of digits
        if (ans != -1){
            return ans;
        }

        return restdigits(offset/10,arr[arr.length-1]);
    }

    public static int search(int arr[],int limit,int offset){
        if (offset == 0){
            return limit;
        }
        // get cur digit of limit
        int cur = (limit/ offset) % 10;
        int near = near(arr,cur);
        // no num in arr are smaller or equal to cur digit
        if (near == -1){
            return -1;
        }
        // if we find a num that smaller to cur digit than we can get the res immediately
        // result has three part:
        // 1.same num as limit;2. cur num found in Binary search;3. biggest num in arr
        // eg. arr =  [3,6,8] , limit = 3764 , res = 3688
        if (arr[near] < cur){
            return (limit / (offset * 10) * offset * 10) + arr[near] * offset + restdigits(offset/10,arr[arr.length-1]);
        }

        // the number that equal to the cur digit, continue the search
        if (arr[near] == cur){
            int ans = search(arr,limit,offset/10);
            if (ans != -1){
                return ans;
            }
            // cannot find ans with cur digit
            if (near == 0) {
                return -1;
            }
            return (limit / (offset * 10) * offset * 10) + arr[near-1] * offset + restdigits(offset/10,arr[arr.length-1]);
        }
        return -1;
    }

    // binary search, return the biggest index which number are smaller or equal to target
    // return min(index) && arr[index]<= target
    public static int near(int arr[],int target){
        int l = 0;
        int r = arr.length -1;
        int index = -1;
        while (l<=r){
            int mid = l + ((r-l) >> 1);
            if (arr[mid] <= target){
                index = mid;
                l = mid + 1;
            } else{
                r = mid - 1;
            }
        }
        return index;
    }

    public static int restdigits(int offset,int num){
        int rest = 0;
        while (offset > 0){
            rest += offset*num;
            offset /=10;
        }
        return rest;
    }

    public static int tmp = 0;

    // brute force
    public static int maxNumber1(int[] arr, int limit) {
        tmp = 0;
        Arrays.sort(arr);
        limit--;
        int offset = 1;
        while (offset <= limit / 10) {
            offset *= 10;
        }
        process1(arr, 0, offset, limit);
        if (tmp == 0) {
            int rest = 0;
            offset /= 10;
            while (offset > 0) {
                rest += arr[arr.length - 1] * offset;
                offset /= 10;
            }
            return rest;
        }
        return tmp;
    }

    public static void process1(int[] arr, int num, int offset, int limit) {
        if (offset == 0) {
            if (num <= limit) {
                tmp = Math.max(tmp, num);
            }
        } else {
            for (int cur : arr) {
                process1(arr, num * 10 + cur, offset / 10, limit);
            }
        }
    }

    // for test
    public static int[] randomArray() {
        int[] arr = new int[(int) (Math.random() * 10) + 1];
        boolean[] cnt = new boolean[10];
        for (int i = 0; i < arr.length; i++) {
            do {
                arr[i] = (int) (Math.random() * 10);
            } while (cnt[arr[i]]);
            cnt[arr[i]] = true;
        }
        return arr;
    }

    public static void main(String[] args) {
        int max = 3000;
        int testTime = 100;
        System.out.println("测试开始");
        for (int i = 0; i < max; i++) {
            int[] arr = randomArray();
            for (int j = 0; j < testTime; j++) {
                int ans1 = maxNumber1(arr, i);
                int ans2 = maxNumber(arr, i);
                if (ans1 != ans2) {
                    System.out.println("出错了!");
                    System.out.println("数组为 ：");
                    for (int num : arr) {
                        System.out.print(num + " ");
                    }
                    System.out.println();
                    System.out.println("数字为 ：" + i);
                    System.out.println(ans1);
                    System.out.println(ans2);
                }
            }
        }
        System.out.println("测试结束");

    }

}

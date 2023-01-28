package Jan;


import java.util.*;

public class EvenSubarray {
    public static int isEven(int k){
        if (k % 2 == 0){
            return 0;
        }
        for (int i = 3;i<k/2;i=i+2){
            if (k % i == 0){
                return 0;
            }
        }
        return 1;
    }
    public static int fun1(List<Integer> numbers, int k) {
        int n = numbers.size();
        int[] flag = new int[n];
        for (int i = 0;i<n;i++){
            flag[i] = isEven(numbers.get(i));
        }
        int res = 0;
        for (int target = 0;target<=k;target++){
            int sum = 0;
            Map<Integer,Integer> map = new HashMap<>();
            map.put(0,1);
            for (int i = 0; i<n;i++){
                sum += flag[i];
                if (map.containsKey(sum-target)) res += map.get(sum-target);
                map.put(sum,map.getOrDefault(sum,0)+1);
            }
            System.out.println(res);
        }

        return res;
    }

    public static int fun(List<Integer> numbers, int k) {
        int n = numbers.size();
        int[] flag = new int[n];
        flag[0] = isEven(numbers.get(0));
        for (int i = 1;i<n;i++){
            flag[i] = flag[i-1] + isEven(numbers.get(i));

        }
        int res = 0;
        Set<String> s = new HashSet<>();
        for (int i = 0;i<n;i++){
            String str = "";
            for (int j=i;j<n;j++){
                String tmp = str + Integer.toString(numbers.get(j)) + '.';
                str = tmp;
                int diff = i == 0? flag[j] :flag[j]-flag[i-1];
                if (diff <= k){
//                    System.out.print(i);
//                    System.out.print("  ");
//                    System.out.print(j);
//                    System.out.print(":");
//                    System.out.println(tmp);
                    s.add(tmp);
                } else {
                    break;
                }
            }
        }

        return s.size();
    }
    public static void main(String[] args) {
        List<Integer> arr = new ArrayList<>();
        arr.add(6);
        arr.add(3);
        arr.add(5);
        arr.add(8);

        System.out.println(fun(arr,1));
    }
}

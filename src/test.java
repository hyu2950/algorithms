import javax.naming.PartialResultException;

public class test {
    public static int minSwaps(int index,int[] arr) {
        int f = index;
        int m = index == 0?arr.length-1:index-1;
        int swaps = 0;
        while (f != m){
            while (f!=m && arr[f] == 1){
                f++;
                if (f == arr.length){
                    f = 0;
                }
            }
            while (f!=m && arr[m] == 0){
                m--;
                if (m == -1){
                    m = arr.length-1;
                }
            }

            if (f!=m){
                swaps++;
                arr[f] = 1;
                arr[m] = 0;
            }
        }

        return swaps;
    }

    public static int solution(int n, String s) {
        int[] a = new int[n];
        char[] ch = s.toCharArray();
        for (int i=0;i<n;i++){
            if (ch[i] == 'F'){
                a[i] = 1;
            } else{
                a[i] = 0;
            }
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0;i<n;i++){
            int tmp;
            if (a[i] == 1){
                tmp = minSwaps(i,a);

                res = Math.min(res,tmp);
            }

        }
       return res;

    }

    public static int findJudge(int n, int[][] trust) {
            int[] count = new int[n + 1];
            for (int[] t : trust) {
                count[t[0]]--;
                count[t[1]]++;
            }
            for (int i = 1; i <= n; i++) {
                if (count[i] == n - 1) return i;
            }
            return -1;
        }


    public static void main(String[] args) {
        int[][] arr = {{1,2}};
      //  System.out.print(findJudge(2,arr));
        System.out.print(solution(4,"MFMF"));
    }
}

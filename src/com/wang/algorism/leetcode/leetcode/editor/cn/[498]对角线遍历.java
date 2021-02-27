package com.wang.algorism.leetcode.leetcode.editor.cn;//给定一个含有 M x N 个元素的矩阵（M 行，N 列），请以对角线遍历的顺序返回这个矩阵中的所有元素，对角线遍历如下图所示。
//
// 
//
// 示例: 
//
// 输入:
//[
// [ 1, 2, 3 ],
// [ 4, 5, 6 ],
// [ 7, 8, 9 ]
//]
//
//输出:  [1,2,4,7,5,3,6,8,9]
//
//解释:
//
// 
//
// 
//
// 说明: 
//
// 
// 给定矩阵中的元素总数不会超过 100000 。 
// 
// 👍 169 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] findDiagonalOrder(int[][] matrix) {
        int[] result = new int[matrix.length * matrix[0].length];
        int x = 4;
        int y = 4;
        int[][] nums = new int[x][y];
        for(int i = 0 ; i < x * y; i ++){
            nums[i / x][i % y] = i + 1;
        }

        int[] arr0 = nums[0];
        for(int i = 0; i < arr0.length + nums.length ; i++){
            // System.out.println(1);
            for(int j =0 ; j < nums.length; j++){
                if(i - j >=0 && i - j < nums[j].length ){
                    System.out.print(" " + nums[j][i-j]);
                }
            }
        }
        return result;
        //Scanner in = new Scanner(System.in);
        //int a = in.nextInt();
        //System.out.println(a);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

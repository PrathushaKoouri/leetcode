/***
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
 *
 * Example:
 *
 * Input: [-2,1,-3,4,-1,2,1,-5,4],
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 * Follow up:
 *
 * If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 */

// O(nlgn) solution

class Solution {
    public int maxSubArray(int[] nums) {
        return maxSubArrayHelper(nums, 0, nums.length-1);
    }

    private static int maxSubArrayHelper(int[] nums, int p, int r) {
        if (p == r) {
            return nums[p];
        }
        else {
            int q = (p + r)/2;
            int leftSum = maxSubArrayHelper(nums, p, q);
            int rightSum = maxSubArrayHelper(nums, q+1, r);
            int crossoverSum = maxCrossoverSum(nums, p, q, r);
            return Math.max(Math.max(leftSum, rightSum), crossoverSum);
        }
    }

    private static int maxCrossoverSum(int[] nums, int p, int q, int r) {
        int suffixSum = nums[q], prefixSum = nums[q+1];
        int bestSuffixSum = nums[q], bestPrefixSum = nums[q+1];
        int bestStart = q, bestEnd = q + 1;

        for(int i = q - 1; i >= p; i--) {
            suffixSum += nums[i];
            if(suffixSum > bestSuffixSum) {
                bestSuffixSum = suffixSum;
                bestStart = i;
            }
        }

        for(int i = q + 2; i <= r; i++) {
            prefixSum += nums[i];
            if(prefixSum > bestPrefixSum) {
                bestPrefixSum = prefixSum;
                bestEnd = i;
            }
        }

        return bestSuffixSum + bestPrefixSum;
    }
}
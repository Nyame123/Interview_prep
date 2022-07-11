package com.bis.interview_prep.search;

/**
 * Given a sorted array of n integers that has been rotated an unknown
 * number of times, write code to find an element in the array. You may assume that the array was
 * originally sorted in increasing order.
 * EXAMPLE
 * Input find 5 in {15, 16, 19, 20, 25, 1, 3,4,5,7,10, 14}
 * Output 8 (the index of 5 in the array)
 **/
public class RotatedSortedSearch {

    public static void main(String[] args) {
        int[] arr = {15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14};
        int index = sortedIndex(arr, 25);
        System.out.println(index);
    }

    //Time Complexity = O(logn)
    static int sortedIndex(int[] arr, int item) {
        //find the rotated point
        int rotatedIndex = findPivot(arr);
        int resultIndex = -1;
        //check the left half for index
        if (arr[0] <= item && item <= arr[rotatedIndex]) {
            resultIndex = binarySearch(arr, 0, rotatedIndex, item);
        } else {
            resultIndex = binarySearch(arr, rotatedIndex + 1, arr.length, item);
        }
        return resultIndex;
    }

    private static int binarySearch(int[] arr, int low, int high, int item) {

        /*int mid = low + (high - low) / 2;
        if (arr[mid] == item) {
            return mid;
        } else if (arr[mid] < item) {
            return binarySearch(arr,mid+1,high,item);
        } else {
            return binarySearch(arr,low,mid,item);
        }*/
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == item) {
                return mid;
            } else if (arr[mid] < item) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return -1;
    }

    private static int findPivot(int[] arr) {
        //the array is sorted in increasing order
        //so after rotation, the left half must increase up to
        // the pivot where the odds happens then we find the pivot
        int i = 0;
        while (i < arr.length && arr[i] <= arr[i + 1]) {
            i++;
        }
        return i;
    }
}

/**
 * There is an integer array nums sorted in ascending order (with distinct values).
 * <p>
 * Prior to being passed to your function, nums is possibly rotated at an unknown pivot
 * index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ...,
 * nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might
 * be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
 * Given the array nums after the possible rotation and an integer target, return the index of
 * target if it is in nums, or -1 if it is not in nums.
 * <p>
 * You must write an algorithm with O(log n) runtime complexity.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 * Example 2:
 * <p>
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 * Example 3:
 * <p>
 * Input: nums = [1], target = 0
 * Output: -1
 * Constraints:
 * <p>
 * 1 <= nums.length <= 5000
 * -104 <= nums[i] <= 104
 * All values of nums are unique.
 * nums is an ascending array that is possibly rotated.
 * -104 <= target <= 104
 **/
class RotatedArraySearch {

    public static void main(String[] args) {
        int[] nums = {3,1};
        int target = 1;
        int searchedElementIndex = findElementInRotatedArr(target, nums);
        System.out.println(searchedElementIndex);
    }

    private static int search(int[] nums, int target){
        int n = nums.length;
        int left = 0, right = n-1;
        while (left <= right){
            int mid = left + (right-left)/2;
            if (nums[mid] == target){
                return mid;
            }

            if (nums[mid] >= nums[left]){
                if(nums[left] <= target && target < nums[mid]){
                    right = mid - 1;
                }else{
                    left = mid + 1;
                }
            }else {
                if(nums[mid] < target && target <= nums[right]){
                    left = mid + 1;
                }else{
                    right = mid - 1;
                }
            }
        }

        return -1;
    }

    /**
     * Using Binary Search Algorithm
     * <p>
     * Time Complexity = O(Log(N))
     **/
    private static int findElementInRotatedArr(int target, int[] nums) {

        int n = nums.length;
        int pivot = findPivot(nums); //O(N)
        if (pivot == -1) {
            return binarySearch(target,nums,0,n-1);
        }

        if (nums[pivot] == target)
            return pivot;

        int leftSearch = binarySearch(target, nums, 0, pivot - 1); //logn
        int rightSearch = binarySearch(target, nums, pivot, n - 1); //logn

        if (leftSearch == -1) {
            return rightSearch;
        } else {
            return leftSearch;
        }
    }

    private static int binarySearch(int target, int[] nums, int left, int right) {

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    private static int findPivot(int[] nums) {
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (mid - 1 >= 0 && nums[mid] < nums[mid - 1]) {
                return mid;
            }
            if (left - 1 >= 0 && nums[left] < nums[left - 1]) {
                return left;
            }

            if (right - 1 >= 0 && nums[right] < nums[right - 1]) {
                return right;
            }
            if (nums[left] < nums[mid]) {
                left = mid + 1;
            } else if (nums[mid] < nums[right]) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}

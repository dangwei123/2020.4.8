给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。

注意：答案中不可以包含重复的三元组。
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res=new ArrayList<>();
        Arrays.sort(nums);
        int k=0;
        for(;k<nums.length-2;k++){
            if(nums[k]>0) break;
            if(k>0&&nums[k]==nums[k-1]) continue;
            int i=k+1;
            int j=nums.length-1;
            while(i<j){
                int sum=nums[k]+nums[i]+nums[j];
                if(sum<0){
                    while(i<j&&nums[i]==nums[++i]){}
                }else if(sum>0){
                    while(i<j&&nums[j]==nums[--j]);
                }else{
                    res.add(new ArrayList<Integer>(Arrays.asList(nums[k],nums[i],nums[j])));
                    while(i<j&&nums[i]==nums[++i]){}
                    while(i<j&&nums[j]==nums[--j]);
                }
            }
        }
        return res;
    }
}


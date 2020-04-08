统计所有小于非负整数 n 的质数的数量。
class Solution {
    public int countPrimes(int n) {
        if(n<3) return 0;
        boolean[] isPrim=new boolean[n+1];
        int count=0;
        for(int i=2;i<n;i++){
            if(!isPrim[i]){
                count++;
                for(int j=1;i*j<=n;j++){
                    isPrim[i*j]=true;
                }
            }
        }
        return count;
    }
}

 //插入排序
    private void insertSort(int[] nums){
        for(int i=1;i<nums.length;i++){
            int tmp=nums[i];
            int d=i-1;
            while(d>=0&&nums[d]>tmp){
                nums[d+1]=nums[d];
                d--;
            }
            nums[d+1]=tmp;
        }
    }
    //折半插入排序
    private void insertBinarySort(int[] nums){
        for(int i=1;i<nums.length;i++){
            int tmp=nums[i];
            int left=0;
            int right=i;
            while(left<right){
                int mid=(left+right)>>1;
                if(nums[mid]<=tmp){
                    left=mid+1;
                }else{
                    right=mid;
                }
            }
            System.arraycopy(nums,left,nums,left+1,i-left);
            nums[left]=tmp;
        }
    }
    //希尔排序
    private void shellSort(int[] nums){
        int gap=nums.length;
        while(gap>1){
            shell(nums,gap);
            gap=gap/3+1;
        }
        shell(nums,1);
    }
    private void shell(int[] nums,int gap){
        for(int i=gap;i<nums.length;i++){
            int tmp=nums[i];
            int d=i-gap;
            while(d>=0&&nums[d]>tmp){
                nums[d+gap]=nums[d];
                d-=gap;
            }
            nums[d+gap]=tmp;
        }
    }
    //选择排序
    private void selectSort(int[] nums){
        for(int i=0;i<nums.length-1;i++){
            int max=0;
            for(int j=1;j<nums.length-i;j++){
                if(nums[j]>nums[max]){
                    max=j;
                }
            }
            swap(nums,max,nums.length-1-i);
        }
    }
    //双向选择排序
    private void selectDouble(int[] nums){
        int left=0;
        int right=nums.length-1;
        while(left<right){
            int max=left;
            int min=left;
            for(int i=left+1;i<=right;i++){
                if(nums[i]>nums[max]){
                    max=i;
                }
                if(nums[i]<nums[min]){
                    min=i;
                }
            }
            swap(nums,max,right);
            if(min==right){
                min=max;
            }
            swap(nums,min,left);
            left++;
            right--;
        }
    }
    //堆排
    public void heapSort(int[] nums){
        createHeap(nums);
        for(int i=0;i<nums.length-1;i++){
            swap(nums,0,nums.length-1-i);
            shiftDown(nums,0,nums.length-i-1);
        }
    }
    private void createHeap(int[] nums){
        for(int i=(nums.length-2)/2;i>=0;i--){
            shiftDown(nums,i,nums.length);
        }
    }
    private void shiftDown(int[] nums,int parent,int size){
        int left=2*parent+1;
        while(left<size){
            int max=left;
            int right=2*parent+2;
            if(right<size&&nums[right]>nums[max]){
                max=right;
            }
            if(nums[parent]>nums[max]){
                break;
            }
            swap(nums,max,parent);
            parent=max;
            left=2*parent+1;
        }
    }
    //冒泡排序
    private void bubbleSort(int[] nums){
        boolean isSort=false;
        for(int i=0;i<nums.length-1;i++){
            isSort=true;
            for(int j=0;j<nums.length-1-i;j++){
                if(nums[j]>nums[j+1]){
                    isSort=false;
                    swap(nums,j,j+1);
                }
            }
            if(isSort){
                break;
            }
        }
    }
    //快速排序(递归)
    public void quickSort(int[] nums,int left,int right){
        if(left>=right){
            return;
        }
        int p=partition3(nums,left,right);
        quickSort(nums,left,p-1);
        quickSort(nums,p+1,right);
    }
    //快速排序(非递归)
    private void quickSortNor(int[] nums){
        Stack<Integer> stack=new Stack<>();
        stack.push(nums.length-1);
        stack.push(0);
        while(!stack.isEmpty()){
            int left=stack.pop();
            int right=stack.pop();
            if(left>=right){
                continue;
            }
            int p=partition3(nums,left,right);
            stack.push(right);
            stack.push(p+1);
            stack.push(p-1);
            stack.push(left);
        }
    }
    private int partition1(int[] nums,int left,int right){
        int pivot=nums[left];
        int i=left;
        int j=right;
        while(i<j){
            while(i<j&&nums[j]>=pivot){
                j--;
            }
            while(i<j&&nums[i]<=pivot){
                i++;
            }
            swap(nums,i,j);
        }
        swap(nums,i,left);
        return i;
    }
    private int partition2(int[] nums,int left,int right){
        int pivot=nums[left];
        int i=left;
        int j=right;
        while(i<j){
            while(i<j&&nums[j]>=pivot){
                 j--;
            }
            nums[i]=nums[j];
            while(i<j&&nums[i]<=pivot){
                 i++;
            }
             nums[j]=nums[i];
        }
        nums[i]=pivot;
        return i;
    }
    private int partition3(int[] nums,int left,int right){
        int pivot=nums[left];
        int d=left+1;
        for(int i=left+1;i<=right;i++){
            if(nums[i]<pivot){
                swap(nums,d,i);
                d++;
            }
        }
        swap(nums,d-1,left);
        return d-1;
    }
	
	//归并排序(递归)
    public void mergeSort(int[] nums,int left,int right){
        if(left>=right){
            return;
        }
        int mid=(left+right)>>1;
        mergeSort(nums,left,mid);
        mergeSort(nums,mid+1,right);
        merge(nums,left,mid,right);
    }
    //归并排序(非递归)
    public void mergeSortNor(int[] nums){
        for(int i=1;i<nums.length;i*=2){
            for(int j=0;j<nums.length;j+=2*i){
                int left=j;
                int mid=j+i;
                int right=j+2*i;
                if(mid>nums.length){
                    mid=nums.length;
                }
                if(right>nums.length){
                    right=nums.length;
                }
                merge(nums,left,mid-1,right-1);
            }
        }
    }
    private void merge(int[] nums,int left,int mid,int right){
        int i=left;
        int j=mid+1;
        int len=right-left+1;
        int k=0;
        int[] tmp=new int[len];
        while(i<=mid&&j<=right){
            if(nums[i]<=nums[j]){
                tmp[k++]=nums[i++];
            }else{
                tmp[k++]=nums[j++];
            }
        }
        while(i<=mid){
            tmp[k++]=nums[i++];
        }
        while(j<=right){
            tmp[k++]=nums[j++];
        }
        System.arraycopy(tmp,0,nums,left,len);
    }
    

    private void swap(int[] nums,int i,int j){
        int tmp=nums[i];
        nums[i]=nums[j];
        nums[j]=tmp;
    }
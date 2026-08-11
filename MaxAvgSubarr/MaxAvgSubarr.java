class Solution {
public double findMaxAverage(int[] nums, int k) {
int maxsum = 0;
int currentsum = 0;
for (int i=0;i\<k;i++){
currentsum+=nums[i];
}
maxsum = currentsum;

```
    for(int i=k;i<nums.length;i++){
        currentsum +=nums[i]-nums[i-k];

        if(currentsum>maxsum){
            maxsum=currentsum;
        }
    }
    return (double)maxsum/k;
}
```

}
package multithread;

public class MergeSort {
	private int[] nums;
	
	public MergeSort(int[] nums) {
		this.nums = nums;
		
		parallelMergeSort(0, nums.length-1);
	
	}
	
	void mergeSort(int low,int high){
	    if(low>=high){
	        return;//returns recursively
	    }
	    int mid =low+ (high-low)/2;
	    mergeSort(low,mid);
	    mergeSort(mid+1,high);
	    merge(low,mid,high);
	   
	}
	
	private void parallelMergeSort(int low, int high) {
		if(low>=high) {
			return;
		}
		int mid = (low+high)/2;
		Thread leftSort = mergeSortParallel(low, mid);
		Thread rightSort = mergeSortParallel(mid+1,high);
		leftSort.start();
		rightSort.start();
		
		try {
			leftSort.join();
			rightSort.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Thread merge = mergeThread(low,mid,high);
		merge.start();
		
		try {
			merge.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	private Thread mergeSortParallel(int low,int high) {
		return new Thread() {
			@Override
			public void run() {
				parallelMergeSort(low,high);
			}
		};
	}
	

	
	public int[] getSortedList() throws InterruptedException {
		return this.nums;
	}
	
	
	private Thread mergeThread(int low, int mid, int high) {
		return new Thread() {
			@Override
			public void run() {
				merge(low, mid,high);
			}
		};
	}
	
	private void merge(int low, int mid, int high) {
		int n1 = mid-low+1;
		int n2 = high-mid;
		
		//Create temp arrays
		int[] L= new int[n1];
		int[] R= new int[n2];
		
		// Copy data to temp arrays L[] and R[]
		for(int i=0;i<n1;i++) {
			L[i]= nums[low+i];
		}
		for(int j=0;j<n2;j++) {
			R[j] = nums[mid+1+j];
		}
		
		// Merge the temp arrays back into arr[l..r]
		 
	    // Initial index of first subarray
	    int i = 0;
	 
	    // Initial index of second subarray
	    int j = 0;
	 
	    // Initial index of merged subarray
	    int k = low;
	 
	    while (i < n1 && j < n2) {
	        if (L[i] <= R[j]) {
	            nums[k] = L[i];
	            i++;
	        }
	        else {
	            nums[k] = R[j];
	            j++;
	        }
	        k++;
	    }
		
	 // Copy the remaining elements of
	    // L[], if there are any
	    while (i < n1) {
	        nums[k] = L[i];
	        i++;
	        k++;
	    }
	 
	    // Copy the remaining elements of
	    // R[], if there are any
	    while (j < n2) {
	        nums[k] = R[j];
	        j++;
	        k++;
	    }
		
	}
	
}


public class MergeSort {
	private int[] list;
	public MergeSort(int[] list) {
		this.list =list;
	}
	
	public int[] getResult() {
		this.mergeSort(this.list, 0, this.list.length-1);
		return this.list;
	}
	
	private void merge(int[] list, int start, int mid, int end) {
		
		int[] temp = new int[end-start+1];
		int i=start, j=mid+1,k=0;
		while(i<=mid && j<=end) {
			if(list[i]<=list[j]) {
				temp[k]= list[i];
				k+=1;
				i+=1;
				
			}else {
				temp[k] = list[j];
				k+=1;
				j+=1;
			}
		}
		
		while(i<=mid) {
			temp[k]= list[i];
			k+=1;
			i+=1;
		}
		
		while(j<=end) {
			temp[k] = list[j];
			k+=1; j+=1;
		}
		
		for(i= start; i<=end;i+=1) {
			list[i] = temp[i-start];
		}
	}
	
	private void mergeSort(int[] list, int start, int end) {
		if(start<end) {
			int mid = (start+end)/2;
			mergeSort(list, start, mid);
			mergeSort(list, mid+1,end);
			System.out.print("merge.");
			merge(list,start,mid,end);
		}
	}
}

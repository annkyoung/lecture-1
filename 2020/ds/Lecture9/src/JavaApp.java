import java.util.Random;
class Sort {
	//	Selection ����
	public static void selection(int[] v, int n) {
		for(int i=0; i<n-1;i++) {
			int select = i;
			for(int j=i+1;j<n;j++) {
				if(v[select]>v[j]) select=j;
			}
			//	swap
			int t = v[i];
			v[i] = v[select];
			v[select] = t;
		}
	}
	//	Insertion ����
	public static void insertion(int[] v, int n) {
		for(int i=1; i<n; i++) {
			int s = v[i];
			int last = i-1;
			while(last >=0 && v[last] > s) {
				v[last+1]=v[last];
				last--;
			}
			v[last+1] = s;
		}
	}
	//	Merge ����
	static void merge(int v[], int s, int m, int e, int[] t) {
		int i=s, j = m+1, k = 0;
		while(i<=m && j<=e) {
			if(v[i]<v[j]) t[k++] = v[i++];
			else t[k++] = v[j++];
		}
		while(i<=m) t[k++] = v[i++];
		while(j<=e) t[k++] = v[j++];
		for(i=0;i<k;i++) v[s+i] = t[i];
	}
	static void mergeSortInt(int[] v, int s, int e, int[] t) {
		if(s==e) return;
		int m = (s+e)/2;
		mergeSortInt(v, s, m, t);
		mergeSortInt(v, m+1, e, t);
		merge(v, s, m, e, t);
	}
	public static void mergeSort(int[] v, int n) {
		int[] t = new int[n];
		mergeSortInt(v, 0, n-1, t);
	}
	//	Heap ����
	static void heapify(int[] v, int r, int n) {
		int max=r*2;
		if(max > n) return;
		if(max < n && v[max] < v[max+1]) max++;
		if(v[max] <= v[r]) return;
		//	swap
		int t = v[r];
		v[r] = v[max];
		v[max] = t;
		//	Make a heap with root = max
		heapify(v, max, n);
	}
	public static void heapSort(int[] v, int n) {
		//	build heap
		//	ó���� ���� �ƴ� �迭�� ���� ��, ������ ������ִ� ����
		//	����ð� : O(n)
		for(int i=n/2;i>=1;i--) heapify(v, i, n);
		while(n>1) {
			//	swap
			int t = v[1];
			v[1]=v[n];
			v[n]=t;
			//	ũ�� ���̱�
			n--;
			//	������ �����
			heapify(v, 1, n);
		}
	}
}
public class JavaApp {
	public static void main(String[] args) {
		Random rand = new Random(100);
		int[] v = new int[100001];
		for(int i=1;i<=100000;i++) {
			v[i] = rand.nextInt(1000000);
		}
		long start = System.currentTimeMillis();
		//	sorting...
		Sort.heapSort(v, 100000);
		for(int i=1;i<=10;i++) System.out.println(v[i]);
		System.out.println("Elapsed time is "+
				(System.currentTimeMillis()-start)+"ms.");
	}
}

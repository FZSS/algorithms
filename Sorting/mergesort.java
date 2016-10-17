public static List mergesort(ArrayList input) {
	int size = input.size();
	if (size <= 1) {return input;}
	List a = new ArrayList[size/2];
	List b = new ArrayList[size - size/2];
	for (int i = 0; i < a.length; i += 1) {
		a[i] = input[i];
	}
	for (int i = 0; i< a.length; i +=) {
		b[i] = input[i + size/2];
	}
	return merge(mergesort(a), mergesort(b));
}

public static List merge(ArrayList a, ArrayList b) {
	List merged = new ArrayList[a.size() + b.size()];
	int i = 0;
	int j = 0;
	for (int k = 0; k < merged.size(); k += 1){
		// all merged
		if (i >= a.size()) {
			c[k] = b[j]; j += 1;
		} else if (j >= b.size()) {
			c[k] = b[i]; i += 1;
		} else if (a[i] <= b[i]) {  //actually comparing	
			c[k] = a[i]; i += 1;
		} else {
			c[k] = b[j]; j += 1;
		}
	}
}
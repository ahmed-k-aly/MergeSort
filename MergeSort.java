public class MergeSort {
    public static int[] getArrayMaxMin(Value[] arr) {
    int min = arr[0].getVal();
    int max = arr[0].getVal();
    for (int i = 0; i < arr.length; i++) {
        if (arr[i].getVal() > max) max = arr[i].getVal();
        if (arr[i].getVal() < min) min = arr[i].getVal();
    }
    return new int[]{max, min};
    }


    public static Value[] setArrayOfValues(int n){
        // Creates an array of Value object setting the instance variable val to a random integer between  1 and n
        Value[] objects = new Value[n];
        for (int i = 0; i < objects.length ; i++) {
            objects[i] = new Value(1+(int)(Math.random()*n));
            System.out.println(objects[i].getVal());
        }
        return objects;
    }

    public static void draw(Value[] objects, int[] maxMin, int ie){
        // Draws all the rectangles based on height val. Rectangles are scaled from min  to max 0.75
        StdDraw.line(0,0.85,1,0.85); // Line at Max
        double ratio = 1.0/objects.length; // = 0.1
        for (int i = 0; i < objects.length ; i++) {
            double x = ratio + i * ratio;
            if (i == ie) {
                StdDraw.setPenColor(StdDraw.BOOK_RED);
            } else{ StdDraw.setPenColor( StdDraw.DARK_GRAY);}
            objects[i].draw(x,maxMin[0],maxMin[1], ratio);
        }
    }

    public static void playSound(Value[] obj, int[] maxMin){
        // Plays sound. Not important for the sorting algorithm
        for (int i = 0; i < obj.length ; i++) {
            obj[i].setSound((int) (440 * Math.pow(2, ((obj[i].getVal()-maxMin[1])/12.0))));
            StdAudio.play(obj[i].getSound());
        }
    }

    public static void mergesort(Value[] arr, int[] maxMin) {
        mergesort_helper(arr, 0, arr.length, maxMin);
    }
    // A helper method that actually does the work. Meaning of the parameters:
    // arr: the array whose sub-array [low, high) we want to sort.
    // low: the lower index of the sub-array we want to sort, inclusive.
    // high: the higher index of the sub-array we want to sort, exclusive.
    public static void mergesort_helper(Value[] arr, int low, int high, int[] maxMin) {
        // Base case: the sub-array has length 0 or 1.
        // (void methods can return, they just don't return anything)
        if (high - low <= 1) {

            return;
        } else {
            // Prepare for the recursive calls
            // Find the mid-point to "split" the sub-array in two "halves"
            int mid = low + (high - low) / 2;
            // Recursive calls / "Divide" phase
            // Sort the two "halves" recursively
            mergesort_helper(arr, low, mid, maxMin);
            mergesort_helper(arr, mid, high, maxMin);
            // "Conquer" phase: merge the sorted sub-arrays.
            merge(arr, low, high, maxMin);

        }
    }
    // Merge two sorted subarrays [low, mid) and [mid,high) of arr (mid is
    // computed in the method). This method is separate from mergesort_helper
    // just for better code organization.
    public static void merge(Value[] arr, int low, int high, int[] maxMin) {
        int mid = low + (high - low) / 2;  // the mid-point
        // Merge the two "halves" into a new array merged
        Value[] merged = new Value[high - low];
        int low_i = low;
        int upp_i = mid;
        for (int mer_i = 0; mer_i < merged.length; mer_i++) {
            if (low_i == mid) {
                // We already put all elements from the lower half in their
                // right place, so just put all the elements from the upper half
                // in their place, and be done.
                while (upp_i < high) {
                    merged[mer_i] = arr[upp_i];
                    upp_i++;
                    mer_i++;
                }
                break;
            } else if (upp_i == high) {
                // We already put all elements from the upper half in their
                // right place, so just put all the elements from the lower half
                // in their place, and be done.
                while (low_i < mid) {
                    merged[mer_i] = arr[low_i];
                    low_i++;
                    mer_i++;
                }
                break;
            } else if (arr[low_i].getVal() < arr[upp_i].getVal()) { // when comparing objects, use  arr[low_i].compareTo(arr[upp_i) < 0
                merged[mer_i] = arr[low_i];
                low_i++;

            } else {
                merged[mer_i] = arr[upp_i];
                upp_i++;
            }
        }
        // Copy the elements of merged back into arr in the right place.
        for (int i = 0; i < merged.length; i++){
            arr[low + i] = merged[i];
            StdDraw.clear(); // Clears canvas
            draw(arr, maxMin,i); // Draws all rectangles, colors rectangle at i with red
            playSound(arr, maxMin); // plays sound
        }
    }

    public static void main(String[] args) {
        int inputVar = 20;
        //checkArgs(inputVar);
        Value[] objects = setArrayOfValues(inputVar);
        int[] maxMin = getArrayMaxMin(objects);
        mergesort(objects, maxMin);
    }

}

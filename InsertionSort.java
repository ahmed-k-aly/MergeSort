public class InsertionSort {
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
        Value[] objects = new Value[n];
        for (int i = 0; i < objects.length ; i++) {
            objects[i] = new Value(1+(int)(Math.random()*n));
            System.out.println(objects[i].getVal());
        }
        return objects;
    }

    public static void draw(Value[] objects, int[] maxMin, int ie){
        StdDraw.line(0,0.85,1,0.85);
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
        for (int i = 0; i < obj.length ; i++) {
            obj[i].setSound((int) (440 * Math.pow(2, ((obj[i].getVal()-maxMin[1])/12.0))));
            StdAudio.play(obj[i].getSound());
        }
    }

    public static void output(Value[] objects, int[] maxMin, int i){
        draw(objects, maxMin, i);
        playSound(objects, maxMin);
        sortingAlgorithm(objects, maxMin);
    }

    public static void sortingAlgorithm(Value[] objects, int[] maxMin){
        int n = objects.length;
        for (int i = 0; i < n-1; i++){
            if (objects[i].getVal()>(objects[i+1].getVal())) {
                Value tmp = objects[i]; // Swap
                objects[i] = objects[i + 1]; // Swap
                objects[i + 1] = tmp; // Swap
                // Draw the two changed columns
                StdDraw.clear();
                output(objects, maxMin, i);
                }
        }
    }


    public static void main(String[] args) {
        int inputVar =50;
        //checkArgs(inputVar);
        Value[] objects = setArrayOfValues(inputVar);
        int[] maxMin = getArrayMaxMin(objects);
        output(objects, maxMin, -1);

    }

}

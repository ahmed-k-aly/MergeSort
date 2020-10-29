public class Value {
    private final int SAMPLING_RATE = 44100;
    private final int val;
    private final int NUMBER_OF_SAMPLES = (int) (SAMPLING_RATE*0.1);
    private final double[] sound = new double[NUMBER_OF_SAMPLES];

    public Value(int val){
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public void setSound(int hz) {
        for (int i = 0; i < sound.length; i++)
            sound[i] = Math.sin(2 * Math.PI * i * hz / SAMPLING_RATE);
    }


    public void draw(double x, int max, int min, double ratio){
        // Draws the passed rectangle at midpoint x, 0, with width ratio and height + 0.1
        System.out.println("Max: " + max);
        System.out.println("Min: " + min);
        double range = max - min;
        double scaledValForPlot;
        if (range == 0){
            scaledValForPlot = 0.75;
        }
        else {
            scaledValForPlot = 0.75 * (val - min)/range;
        }
        StdDraw.filledRectangle((x-ratio/2),0,0.5*ratio, + 0.1+scaledValForPlot);
    }

    public double[] getSound() {
        return sound;
    }
}

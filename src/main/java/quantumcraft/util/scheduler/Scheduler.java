package quantumcraft.util.scheduler;

public abstract class Scheduler {

    protected int often = 0;
    protected int iteration = 0;

    public Scheduler(int oftenSet) {
        often = oftenSet;
    }

    public void run() {
        if (iteration < often) {
            iteration++;
        } else {
            iteration = 0;
            process();
        }
    }

    public abstract void process();

}

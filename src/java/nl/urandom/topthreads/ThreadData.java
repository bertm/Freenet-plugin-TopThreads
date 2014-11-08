package nl.urandom.topthreads;

/**
 * FIXME Javadoc
 *
 * @author bertm
 */
public class ThreadData {
    private final String name;
    private final long cpuTime;

    public ThreadData(String name, long cpuTime) {
        this.name = name;
        this.cpuTime = cpuTime;
    }

    @Override
    public String toString() {
        return cpuTime + " " + name;
    }

    public long getCpuTime() {
        return cpuTime;
    }
}

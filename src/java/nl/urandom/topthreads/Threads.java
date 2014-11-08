package nl.urandom.topthreads;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * FIXME Javadoc
 *
 * @author bertm
 */
public class Threads {
    public static ThreadData[] getTop(int num) {
        ThreadMXBean threads = ManagementFactory.getThreadMXBean();
        long[] threadIds = threads.getAllThreadIds();
        ThreadInfo[] threadInfos = threads.getThreadInfo(threadIds);

        ThreadData[] ret = new ThreadData[threadIds.length];
        int i = 0;

        for (ThreadInfo info : threadInfos) {
            long cpuTime = threads.getThreadCpuTime(info.getThreadId());
            String name = info.getThreadName();
            ret[i++] = new ThreadData(name, cpuTime);
        }

        Arrays.sort(ret, new Comparator<ThreadData>() {
            // Descending CPU time comparator
            @Override
            public int compare(ThreadData o1, ThreadData o2) {
                return Long.compare(o2.getCpuTime(), o1.getCpuTime());
            }
        });

        return Arrays.copyOf(ret, num);
    }
}

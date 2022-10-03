
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Solution {

    public List<Integer> killProcess(List<Integer> processID, List<Integer> parentProcessID, int terminateProcessID) {

        Map<Integer, List<Integer>> perentToChildren = new HashMap<>();
        for (int i = 0; i < processID.size(); ++i) {
            perentToChildren.putIfAbsent(parentProcessID.get(i), new ArrayList<>());
            perentToChildren.get(parentProcessID.get(i)).add(processID.get(i));
        }

        List<Integer> processesToBeTerminated = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(terminateProcessID);

        while (!queue.isEmpty()) {

            int currentProcessID = queue.poll();
            processesToBeTerminated.add(currentProcessID);

            if (!perentToChildren.containsKey(currentProcessID)) {
                continue;
            }

            List<Integer> childrenProcessesID = perentToChildren.get(currentProcessID);
            for (int childProcessID : childrenProcessesID) {
                queue.add(childProcessID);
            }
        }
        return processesToBeTerminated;
    }
}

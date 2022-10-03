
#include <queue>
#include <vector>
using namespace std;

class Solution {
    
public:
    vector<int> killProcess(vector<int>& processID, vector<int>& parentProcessID, int terminateProcessID) const {

        unordered_map<int, vector<int>> perentToChildren;
        for (int i = 0; i < processID.size(); ++i) {
            perentToChildren[parentProcessID[i]].push_back(processID[i]);
        }

        vector<int> processesToBeTerminated;
        queue<int> queue;
        queue.push(terminateProcessID);

        while (!queue.empty()) {

            int currentProcessID = queue.front();
            queue.pop();
            processesToBeTerminated.push_back(currentProcessID);

            //C++20: if (!perentToChildren.contains(currentProcessID)){...}
            if (perentToChildren.find(currentProcessID) == perentToChildren.end()) {
                continue;
            }

            vector<int> childrenProcessesID = perentToChildren[currentProcessID];
            for (const auto& childProcessID : childrenProcessesID) {
                queue.push(childProcessID);
            }
        }
        return processesToBeTerminated;
    }
};

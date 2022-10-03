
/**
 * @param {number[]} processID
 * @param {number[]} parentProcessID
 * @param {number} terminateProcessID
 * @return {number[]}
 */
var killProcess = function (processID, parentProcessID, terminateProcessID) {

    const perentToChildren = new Map();//Map<number, number[]>
    for (let i = 0; i < processID.length; ++i) {
        if (!perentToChildren.has(parentProcessID[i])) {
            perentToChildren.set(parentProcessID[i], []);
        }
        perentToChildren.get(parentProcessID[i]).push(processID[i]);
    }

    const processesToBeTerminated = [];
    const queue = new Queue();
    queue.enqueue(terminateProcessID);

    while (!queue.isEmpty()) {

        let currentProcessID = queue.dequeue();
        processesToBeTerminated.push(currentProcessID);

        if (!perentToChildren.has(currentProcessID)) {
            continue;
        }

        const childrenProcessesID = perentToChildren.get(currentProcessID);
        for (let childProcessID of childrenProcessesID) {
            queue.enqueue(childProcessID);
        }
    }
    return processesToBeTerminated;
};

function runSimulation() {
    const selectedQueueElement = document.querySelector('input[name="diskQueue"]:checked');
    const initialPosition = parseInt(selectedQueueElement.dataset.initialPos, 10);
    //console.log(initialPosition);

    const selectedQueue = JSON.parse(selectedQueueElement.value);
    const selectedAlgorithm = document.querySelector('input[name="algorithm"]:checked').value;

    const dataToSend = {
        queue: selectedQueue,
        initialPosition: initialPosition,
        algorithm: selectedAlgorithm
    };
    console.log(dataToSend);

    // Sending the data to the server
    fetch('http://localhost:8080/schedule', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(dataToSend)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            // Building the results text
            const resultText = `Total Seek Operations: ${data.totalSeekCount}<br>Seek Sequence: ${data.sequence.join(', ')}`;
            document.getElementById('resultsText').innerHTML = resultText;
        })
        .catch(error => {
            console.error('Error:', error);
            document.getElementById('resultsText').textContent = 'Failed to run simulation.';
        });
}

const algorithmDescriptions = {
    FCFS: "First Come First Serve (FCFS) is the simplest type of disk scheduling algorithm. In this type, the requests are addressed in the order they arrive in the disk queue.",
    SSTF: "Shortest Seek Time First (SSTF) selects the request with the shortest seek time from the current head position.",
    SCAN: "Elevator (SCAN) algorithm moves the head towards one end of the disk, and upon reaching the end, it reverses direction and services the remaining requests in the other direction.",
    CSCAN: "Circular SCAN (C-SCAN) moves the head from one end of the disk to the other, servicing requests along the way, and then jumps back to the beginning and repeats the process.",
    LOOK: "LOOK algorithm is similar to SCAN but the head only travels as far as the last request in each direction before reversing direction.",
    CLOOK: "C-LOOK is similar to C-SCAN but the arm only goes as far as the last request in one direction before going back to the first request in the opposite direction."
};

function updateDescription() {
    const selectedAlgorithm = document.querySelector('input[name="algorithm"]:checked').value;
    document.getElementById('algorithmDescription').textContent = algorithmDescriptions[selectedAlgorithm];
}




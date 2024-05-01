function runSimulation() {
    // Collecting form data
    const selectedQueue = document.querySelector('input[name="diskQueue"]:checked').value;
    const selectedAlgorithm = document.querySelector('input[name="algorithm"]:checked').value;

    const initialPositions = {
        queue1: 50,
        queue2: 70,
        queue3: 90
    };

    const dataToSend = {
        queue: JSON.parse(selectedQueue),
        initialPosition: initialPositions[selectedQueue.split('queue')[1]], // Assuming queue name is like queue1, queue2...
        algorithm: selectedAlgorithm
    };

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

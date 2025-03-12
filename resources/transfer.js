 // Handle form submission using Fetch API
    document.getElementById('moneyTransferForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Prevent the default form submission

    // Get form data
    const senderAccountNumber = document.getElementById('senderAccountNumber').value;
    const receiverAccountNumber = document.getElementById('receiverAccountNumber').value;
    const amountToTransfer = document.getElementById('amountToTransfer').value;

    // Create the request body object
    const transferData = {
    senderAccountNumber: senderAccountNumber,
    receiverAccountNumber: receiverAccountNumber,
    amountToTransfer: amountToTransfer
};

    // Send the POST request using Fetch API
    fetch('http://localhost:8080/api/transfer', {
    method: 'POST',
    headers: {
    'Content-Type': 'application/json'
},
    body: JSON.stringify(transferData)
})
    .then(response => response.text())
    .then(data => {
    console.log('Sukces:', data);
    document.getElementById("response").innerText = data;
    })
    .catch((error) => {
    console.error('Błąd:', error);
        document.getElementById("response").innerText = error;
    });
});
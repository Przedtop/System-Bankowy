document.getElementById('depositForm').addEventListener('submit', function (event) {
    event.preventDefault();

    const senderAccountNumber = document.getElementById('senderAccountNumber').value;
    const receiverAccountNumber = document.getElementById('receiverAccountNumber').value;
    const amountToTransfer = document.getElementById('amountToTransfer').value;

    const responseDiv = document.getElementById("response");

    if (!senderAccountNumber || isNaN(senderAccountNumber) || senderAccountNumber < 0 || senderAccountNumber > 999999999999999999) {
        responseDiv.style.display = 'block';
        responseDiv.innerText = 'Please enter a valid sender account number';
        return;
    }

    if (!receiverAccountNumber || isNaN(receiverAccountNumber) || receiverAccountNumber < 0 || receiverAccountNumber > 999999999999999999) {
        responseDiv.style.display = 'block';
        responseDiv.innerText = 'Please enter a valid receiver account number';
        return;
    }

    if (!amountToTransfer || isNaN(amountToTransfer) || amountToTransfer <= 0 || amountToTransfer > 9999999999) {
        responseDiv.style.display = 'block';
        responseDiv.innerText = 'Please enter a valid amount to transfer';
        return;
    }

    const transferData = {
        senderAccountNumber: Number(senderAccountNumber),
        receiverAccountNumber: Number(receiverAccountNumber),
        amountToTransfer: Number(amountToTransfer)
    };
    document.getElementById("response").style.display = 'block';
    fetch('http://localhost:8080/api/transfer', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(transferData)
    })
        .then(response => response.text())
        .then(data => {
            console.log('Success:', data);
            document.getElementById("response").innerText = data;
        })
        .catch((error) => {
            console.error('Error:', error);
            document.getElementById("response").innerText = error;
        });
});
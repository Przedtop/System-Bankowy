document.getElementById('deleteAccountByAccountNumberForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const accountId = document.getElementById('accountId').value;

    if (!accountId) {
        document.getElementById("response").style.display = 'block';
        document.getElementById("response").innerText = 'Please enter an account ID';
        return;
    }

    fetch(`http://localhost:8080/api/accounts/${accountId}`, {
        method: 'DELETE',
    })
        .then(response => response.text())
        .then(data => {
            try {
                const jsonData = JSON.parse(data);
                displayAccountData(jsonData);
            } catch (error) {
                console.error('Error parsing JSON:', error);
                document.getElementById("response").innerText = data;
            }
        })
        .catch((error) => {
            console.error('Error:', error);
            document.getElementById("response").style.display = 'block';
            document.getElementById("response").innerText = error;
        });
});


document.getElementById('deleteAccountByAccountNumberForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const accountNumber = document.getElementById('accountNumber').value;

    if (!accountNumber) {
        document.getElementById("response").style.display = 'block';
        document.getElementById("response").innerText = 'Please enter an account number';
        return;
    }

    fetch(`http://localhost:8080/api/accounts/accountNumber/${accountNumber}`, {
        method: 'DELETE',
    })
        .then(response => response.text())
        .then(data => {
            document.getElementById("response").style.display = 'block';
            document.getElementById("response").innerText = data;
        })
        .catch((error) => {
            console.error('Error:', error);
            document.getElementById("response").style.display = 'block';
            document.getElementById("response").innerText = error;
        });
});
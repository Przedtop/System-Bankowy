document.getElementById('deleteAccountByIdForm').addEventListener('submit', function (event) {
    event.preventDefault();


    const accountId = document.getElementById('accountId').value;

    if (!accountId || isNaN(accountId) || accountId < 0 || accountId > 999999999999999999) {
        document.getElementById("response").style.display = 'block';
        document.getElementById("response").innerText = 'Please enter a valid account ID';
        return;
    }

    document.getElementById("response").style.display = 'block';
    fetch(`http://${window.location.hostname}:8080/api/accounts/${accountId}`, {
        method: 'DELETE',
    })
        .then(response => response.text())
        .then(data => {
            document.getElementById("response").innerText = data;
        })
        .catch((error) => {
            console.error('Error:', error);
            document.getElementById("response").innerText = error;
        });
});


document.getElementById('deleteAccountByAccountNumberForm').addEventListener('submit', function (event) {
    event.preventDefault();

    const accountNumber = document.getElementById('accountNumber').value;

    if (!accountNumber || isNaN(accountNumber) || accountNumber < 0 || accountNumber > 999999999999999999) {
        document.getElementById("response").style.display = 'block';
        document.getElementById("response").innerText = 'Please enter a valid account number';
        return;
    }

    document.getElementById("response").style.display = 'block';
    fetch(`http://${window.location.hostname}:8080/api/accounts/accountNumber/${accountNumber}`, {
        method: 'DELETE',
    })
        .then(response => response.text())
        .then(data => {
            document.getElementById("response").innerText = data;
        })
        .catch((error) => {
            console.error('Error:', error);
            document.getElementById("response").innerText = error;
        });
});
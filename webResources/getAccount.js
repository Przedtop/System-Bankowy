document.getElementById('getAccountByIdForm').addEventListener('submit', function (event) {
    event.preventDefault();

    const accountId = document.getElementById('accountId').value;

    if (!accountId) {
        document.getElementById("response").style.display = 'block';
        document.getElementById("response").innerText = 'Please enter an account ID';
        return;
    }

    fetch(`http://localhost:8080/api/accounts/${accountId}`, {
        method: 'GET',
    })
        .then(response => response.text())
        .then(data => {
            try {
                const jsonData = JSON.parse(data);
                displayAccountData(jsonData);
            } catch (error) {
                console.error('Error parsing JSON:', error);
                document.getElementById("response").style.display = 'block';
                document.getElementById("response").innerText = data;
            }
        })
        .catch((error) => {
            console.error('Error:', error);
            document.getElementById("response").style.display = 'block';
            document.getElementById("response").innerText = error;
        });
});


document.getElementById('getAccountByAccountNumberForm').addEventListener('submit', function (event) {
    event.preventDefault();
    const accountNumber = document.getElementById('accountNumber').value;

    if (!accountNumber) {
        document.getElementById("response").style.display = 'block';
        document.getElementById("response").innerText = 'Please enter an account number';
        return;
    }

    fetch(`http://localhost:8080/api/accounts/accountNumber/${accountNumber}`, {
        method: 'GET',
    })
        .then(response => response.text())
        .then(data => {
            try {
                const jsonData = JSON.parse(data);
                displayAccountData(jsonData);
            } catch (error) {
                console.error('Error parsing JSON:', error);
                document.getElementById("response").style.display = 'block';
                document.getElementById("response").innerText = data;
            }
        })
        .catch((error) => {
            console.error('Error:', error);
            document.getElementById("response").style.display = 'block';
            document.getElementById("response").innerText = error;
        });
});


function displayAccountData(account) {
    const responseDiv = document.getElementById("response");
    responseDiv.style.display = 'block';
    responseDiv.innerHTML = `
        <table style="width: 100%; border-collapse: collapse; margin-top: 15px;">
            <tr style="background-color: #444; color: #fff;">
                <th style="padding: 10px; border: 1px solid #777;">ID</th>
                <th style="padding: 10px; border: 1px solid #777;">Account Number</th>
                <th style="padding: 10px; border: 1px solid #777;">Balance</th>
                <th style="padding: 10px; border: 1px solid #777;">Create Date</th>
                <th style="padding: 10px; border: 1px solid #777;">User ID</th>
            </tr>
            <tr style="background-color: #555; color: #fff;">
                <td style="padding: 10px; border: 1px solid #777;">${account.id}</td>
                <td style="padding: 10px; border: 1px solid #777;">${account.accountNumber}</td>
                <td style="padding: 10px; border: 1px solid #777;">${account.balance}</td>
                <td style="padding: 10px; border: 1px solid #777;">${account.createDate}</td>
                <td style="padding: 10px; border: 1px solid #777;">${account.userId}</td>
            </tr>
        </table>
    `;
}
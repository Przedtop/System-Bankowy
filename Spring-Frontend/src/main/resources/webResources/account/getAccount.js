import {serverConfig} from "../scripts/serverConfig.js";

document.getElementById('getAccountByIdForm').addEventListener('submit', function (event) {
    event.preventDefault();

    const accountId = document.getElementById('accountId').value;

    if (!accountId || isNaN(accountId) || accountId < 0 || accountId > 999999999999999999) {
        document.getElementById("response").style.display = 'block';
        document.getElementById("response").innerText = 'Please enter a valid account ID';
        return;
    }

    const token = localStorage.getItem('token');
    if (!token) {
        console.error("Token is missing or invalid");
        responseDiv.style.display = 'block';
        responseDiv.innerText = 'Login failed or expired';
        setTimeout(function() {
            window.location.href = '/login';
        }, 1500);
        return;
    }

    fetch(`http://${serverConfig.serverAddress}:${serverConfig.serverPort}/api/accounts/${accountId}`, {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${token}`,
        },
    })
        .then(response => {
            if (response.status === 401) {
                throw new Error('Unauthorized');
            }
            return response.text();
        })
        .then(data => {
            try {
                const jsonData = JSON.parse(data);
                displayAccountData(jsonData);
            } catch (error) {
                document.getElementById("response").innerText = data;
            }
        })
        .catch((error) => {
            console.error('Error:', error);
            if (error.message === 'Failed to fetch') {
                document.getElementById("response").innerText = 'Login failed or expired';
                setTimeout(function() {
                    window.location.href = '/login';
                }, 1500);
            } else {
                document.getElementById("response").innerText = error;
            }
        });
});


document.getElementById('getAccountByAccountNumberForm').addEventListener('submit', function (event) {
    event.preventDefault();
    const accountNumber = document.getElementById('accountNumber').value;

    if (!accountNumber || isNaN(accountNumber) || accountNumber < 0 || accountNumber > 999999999999999999) {
        document.getElementById("response").style.display = 'block';
        document.getElementById("response").innerText = 'Please enter a valid account number';
        return;
    }

    const token = localStorage.getItem('token');
    if (!token) {
        console.error("Token is missing or invalid");
        responseDiv.style.display = 'block';
        responseDiv.innerText = 'Login failed or expired';
        setTimeout(function() {
            window.location.href = '/login';
        }, 1500);
        return;
    }

    fetch(`http://${serverConfig.serverAddress}:${serverConfig.serverPort}/api/accounts/accountNumber/${accountNumber}`, {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${token}`,
        },
    })
        .then(response => {
            if (response.status === 401) {
                throw new Error('Unauthorized');
            }
            return response.text();
        })
        .then(data => {
            try {
                const jsonData = JSON.parse(data);
                displayAccountData(jsonData);
            } catch (error) {
                document.getElementById("response").innerText = data;
            }
        })
        .catch((error) => {
            console.error('Error:', error);
            if (error.message === 'Failed to fetch') {
                document.getElementById("response").innerText = 'Login failed or expired';
                setTimeout(function() {
                    window.location.href = '/login';
                }, 1500);
            } else {
                document.getElementById("response").innerText = error;
            }
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
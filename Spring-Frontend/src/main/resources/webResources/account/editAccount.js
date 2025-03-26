document.getElementById('editAccountForm').addEventListener('submit', function (event) {
    event.preventDefault();

    const id = document.getElementById('id').value;
    const accountNumber = document.getElementById('accountNumber').value;
    const balance = document.getElementById('balance').value;
    const userId = document.getElementById('userID').value;
    const createDate = document.getElementById('date').value;
    const responseDiv = document.getElementById("response");

    if (!id || isNaN(id) || id < 0 || id > 999999999) {
        responseDiv.style.display = 'block';
        responseDiv.innerText = 'Please enter a valid ID';
        return;
    }

    if (accountNumber !== '' && (isNaN(accountNumber) || accountNumber < 0 || accountNumber > 999999999)) {
        responseDiv.style.display = 'block';
        responseDiv.innerText = 'Please enter a valid account number';
        return;
    }

    if (balance !== '' && (isNaN(balance) || balance < 0 || balance > 999999999)) {
        responseDiv.style.display = 'block';
        responseDiv.innerText = 'Please enter a valid balance';
        return;
    }

    if (userId !== '' && (isNaN(userId) || userId < 0 || userId > 999999999)) {
        responseDiv.style.display = 'block';
        responseDiv.innerText = 'Please enter a valid user ID';
        return;
    }

    if (createDate !== '' && isNaN(Date.parse(createDate))) {
        responseDiv.style.display = 'block';
        responseDiv.innerText = 'Please enter a valid date';
        return;
    }

    const transferData = {
        id: Number(id),
        accountNumber: accountNumber === '' ? null : Number(accountNumber),
        balance: balance === '' ? null : Number(balance),
        userId: userId === '' ? null : Number(userId),
        createDate: createDate === '' ? null : createDate
    };

    function getTokenFromLocalStorage() {
        return localStorage.getItem('token');
    }

    const token = getTokenFromLocalStorage();
    if (!token) {
        console.error("Token is missing or invalid");
        responseDiv.style.display = 'block';
        responseDiv.innerText = 'Login failed or expired';
        return;
    }

    document.getElementById("response").style.display = 'block';
    fetch(`http://${window.location.hostname}:8080/api/accounts`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`,
        },
        body: JSON.stringify(transferData)
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
document.getElementById('createUserFrom').addEventListener('submit', function (event) {
    event.preventDefault();

    let name = document.getElementById('firstName').value;
    let lastName = document.getElementById('lastName').value;
    let identificationNumber = document.getElementById('identificationNumber').value;
    let login = document.getElementById('login').value;
    let password = document.getElementById('password').value;

    const responseDiv = document.getElementById("response");

    if (name === '') {
        responseDiv.style.display = 'block';
        responseDiv.innerText = 'Please enter a valid first name';
        return;
    }

    if (lastName === '') {
        responseDiv.style.display = 'block';
        responseDiv.innerText = 'Please enter a valid last name';
        return;
    }

    if (identificationNumber !== '' && (isNaN(identificationNumber) || identificationNumber < 0)) {
        responseDiv.style.display = 'block';
        responseDiv.innerText = 'Please enter a valid identification number';
        return;
    }

    if (login === '') {
        responseDiv.style.display = 'block';
        responseDiv.innerText = 'Please enter a valid login';
        return;
    }

    if (password === '' || password.length < 6 || password.length > 20) {
        responseDiv.style.display = 'block';
        responseDiv.innerText = 'Please enter a valid password (6-20 characters)';
        return;
    }

    let userData = {
        name: name,
        lastName: lastName,
        identificationNumber: identificationNumber === '' ? null : Number(identificationNumber),
        login: login,
        password: password
    };
    document.getElementById("response").style.display = 'block';
    fetch(`http://${window.location.hostname}:8080/api/users`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(userData)
    })
        .then(response => response.text())
        .then(data => {
            try {
                const jsonData = JSON.parse(data);
                displayUserData(jsonData);
            } catch (error) {
                document.getElementById("response").innerText = data;
            }
        })
        .catch((error) => {
            console.error('Error:', error);
            document.getElementById("response").innerText = error;
        });
});

function displayUserData(user) {
    const responseDiv = document.getElementById("response");
    responseDiv.style.display = 'block';
    responseDiv.innerHTML = `
        <table style="width: 100%; border-collapse: collapse; margin-top: 15px;">
            <tr style="background-color: #444; color: #fff;">
                <th style="padding: 10px; border: 1px solid #777;">ID</th>
                <th style="padding: 10px; border: 1px solid #777;">First Name</th>
                <th style="padding: 10px; border: 1px solid #777;">Last Name</th>
                <th style="padding: 10px; border: 1px solid #777;">Identification Number</th>
                <th style="padding: 10px; border: 1px solid #777;">Login</th>
                <th style="padding: 10px; border: 1px solid #777;">Password</th>
            </tr>
            <tr style="background-color: #555; color: #fff;">
                <td style="padding: 10px; border: 1px solid #777;">${user.id}</td>
                <td style="padding: 10px; border: 1px solid #777;">${user.name}</td>
                <td style="padding: 10px; border: 1px solid #777;">${user.lastName}</td>
                <td style="padding: 10px; border: 1px solid #777;">${user.identificationNumber}</td>
                <td style="padding: 10px; border: 1px solid #777;">${user.login}</td>
                <td style="padding: 10px; border: 1px solid #777;">${user.password}</td>
            </tr>
        </table>
    `;
}
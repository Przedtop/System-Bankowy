document.getElementById('getUserByIdForm').addEventListener('submit', function (event) {
    event.preventDefault();

    const userId = document.getElementById('userId').value;

    if (!userId || isNaN(userId) || userId < 0 || userId > 999999999999999999) {
        document.getElementById("response").style.display = 'block';
        document.getElementById("response").innerText = 'Please enter a valid user ID';
        return;
    }

    fetch(`http://localhost:8080/api/users/${userId}`, {
        method: 'GET',
    })
        .then(response => response.text())
        .then(data => {
            try {
                const jsonData = JSON.parse(data);
                displayUserData(jsonData);
            } catch (error) {
                document.getElementById("response").style.display = 'block';
                document.getElementById("response").innerText = data;
            }
        })
        .catch((error) => {
            document.getElementById("response").style.display = 'block';
            document.getElementById("response").innerText = error;
        });
});


document.getElementById('getUserByIdentificationNumberForm').addEventListener('submit', function (event) {
    event.preventDefault();
    const userIdentificationNumber = document.getElementById('userIdentificationNumber').value;

    if (!userIdentificationNumber || isNaN(userIdentificationNumber) || userIdentificationNumber < 0 || userIdentificationNumber > 999999999999999999) {
        document.getElementById("response").style.display = 'block';
        document.getElementById("response").innerText = 'Please enter a valid user identification number';
        return;
    }

    fetch(`http://localhost:8080/api/users/idNumber/${userIdentificationNumber}`, {
        method: 'GET',
    })
        .then(response => response.text())
        .then(data => {
            try {
                const jsonData = JSON.parse(data);
                displayUserData(jsonData);
            } catch (error) {
                document.getElementById("response").style.display = 'block';
                document.getElementById("response").innerText = data;
            }
        })
        .catch((error) => {
            document.getElementById("response").style.display = 'block';
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
                <th style="padding: 10px; border: 1px solid #777;">Role</th>
            </tr>
            <tr style="background-color: #555; color: #fff;">
                <td style="padding: 10px; border: 1px solid #777;">${user.id}</td>
                <td style="padding: 10px; border: 1px solid #777;">${user.name}</td>
                <td style="padding: 10px; border: 1px solid #777;">${user.lastName}</td>
                <td style="padding: 10px; border: 1px solid #777;">${user.identificationNumber}</td>
                <td style="padding: 10px; border: 1px solid #777;">${user.login}</td>
                <td style="padding: 10px; border: 1px solid #777;">${user.password}</td>
                <td style="padding: 10px; border: 1px solid #777;">${user.role}</td>
            </tr>
        </table>
    `;
}
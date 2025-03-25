document.getElementById('deleteUserByIdForm').addEventListener('submit', function (event) {
    event.preventDefault();

    const userId = document.getElementById('userId').value;

    if (!userId || isNaN(userId) || userId < 0 || userId > 999999999999999999) {
        document.getElementById("response").style.display = 'block';
        document.getElementById("response").innerText = 'Please enter a valid account ID';
        return;
    }

    document.getElementById("response").style.display = 'block';
    fetch(`http://localhost:8080/api/users/${userId}`, {
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


document.getElementById('deleteUserByIdentificationNumberForm').addEventListener('submit', function (event) {
    event.preventDefault();

    const userIdentificationNumber = document.getElementById('userIdentificationNumber').value;

    if (!userIdentificationNumber || isNaN(userIdentificationNumber) || userIdentificationNumber < 0 || userIdentificationNumber > 999999999999999999) {
        document.getElementById("response").style.display = 'block';
        document.getElementById("response").innerText = 'Please enter a valid account number';
        return;
    }

    document.getElementById("response").style.display = 'block';
    fetch(`http://localhost:8080/api/users/idNumber/${userIdentificationNumber}`, {
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
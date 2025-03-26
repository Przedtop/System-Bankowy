 document.getElementById('login-form').addEventListener('submit', async function(event) {
    event.preventDefault();

    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    const response = await fetch(`http://localhost:8080/auth/login`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ login: username, password: password })
    });
    if (response.ok) {
        const data = await response.json();
        localStorage.setItem('token', data.token);
        window.location.href = '/'; // Redirect to a protected page
    } else {
        document.getElementById('error-message').textContent = await response.text();
    }
});
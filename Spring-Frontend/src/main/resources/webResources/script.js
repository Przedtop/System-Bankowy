import {serverConfig} from "/scripts/serverConfig.js";

const token = localStorage.getItem('token');

const transferData = {
    token: token
};

fetch(`http://${serverConfig.serverAddress}:${serverConfig.serverPort}/auth/tokenCheck`, {
    method: 'POST',
    headers: {
        'Content-Type': 'application/json',
    },
    body: JSON.stringify(transferData)
})
    .then(response => response.text())
    .then(text => {
        if (text === "Token is valid") {
            alert("Logged automatically");
            window.location.href = '/';
        }
    })

document.getElementById('login-form').addEventListener('submit', async function(event) {
    event.preventDefault();

    document.body.classList.add('loading');
    document.querySelector('.spinner-overlay').style.display = 'flex';

    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    const response = await fetch(`http://${serverConfig.serverAddress}:${serverConfig.serverPort}/auth/login`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ login: username, password: password })
    });
    if (response.ok) {
        const data = await response.json();
        localStorage.setItem('token', data.token);
        window.location.href = '/';
    } else {
        document.getElementById('error-message').textContent = await response.text();
        document.body.classList.remove('loading');
        document.querySelector('.spinner-overlay').style.display = 'none';
    }
});
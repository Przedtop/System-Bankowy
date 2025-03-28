export const serverConfig = {
    serverAddress: '192.168.1.112',
    serverPort: 8080,
};

export function authenticationCheck() {
    let token = localStorage.getItem('token');
    if(!token){
        window.location.href = '/login';
    }
    if(token){
        token = {token: token};
        fetch(`http://${serverConfig.serverAddress}:${serverConfig.serverPort}/auth/tokenCheck`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(token)
        })
            .then(response => response.text())
            .then(text => {
                if (text === "Token expired") {
                    window.location.href = '/login';
                }
            })
    }
}
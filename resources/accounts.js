async function getAccount() {
    const accountNumber = document.getElementById("accountNumber").value;
    const response = await fetch(`/api/accounts/${accountNumber}`);
    const data = await response.json();
    document.getElementById("response").innerText = JSON.stringify(data, null, 2);
}
async function deleteAccount() {
    const accountNumber = document.getElementById("accountNumber").value;
    const response = await fetch(`/api/accounts/accountNumber/${accountNumber}`, { method: 'DELETE' });
    document.getElementById("response").innerText = await response.text();
}
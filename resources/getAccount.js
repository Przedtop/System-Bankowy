async function getAccountById() {

    const accountId = document.getElementById('accountId').value;
    const response = await fetch(`/api/accounts/${accountId}`);
    const data = await response.json();
    document.getElementById("response").innerText = JSON.stringify(data, null, 2);
}
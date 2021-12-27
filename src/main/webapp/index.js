let role = "";

document.addEventListener("DOMContentLoaded", evt => {
    fetch("http://localhost:8080/auth")
        .then(resp => {
            if (resp.status === 401) return;
            return resp.json()
        })

        .then(data => {
            if (data === undefined) return;
            role = data;
        })
})
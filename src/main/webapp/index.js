let user = {};

document.addEventListener("DOMContentLoaded", evt => {
    fetch('http://localhost:8080/auth')
        .then(resp => {
            if (resp.status === 401) return;
            return resp.json()
        })

        .then(userData => {
            if (userData === undefined) return;
            user = userData;
        })
})
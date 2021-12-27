let user = {};

document.addEventListener("DOMContentLoaded", evt => {
    fetch('http://localhost:8080/auth')
        .then(resp => {
            return resp.json()
        })

        .then(userData => {
            user = userData;
            update();
        })
})

function update() {
    if (user.role !== "ADMIN") document.location.href = "../../index.html"
}

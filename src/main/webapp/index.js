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
            update();
        })
})

function update() {
    if (user.role ===  "ADMIN") {
        let bodyElement = document.getElementsByTagName("body")[0];
        let adminPanelElement = document.createElement("a");
        adminPanelElement.setAttribute("href", "pages/admin/admin.html");
        adminPanelElement.innerText = "Admin panel";
        bodyElement.appendChild(adminPanelElement);
    }
}
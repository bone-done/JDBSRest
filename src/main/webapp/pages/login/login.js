let emailElement = document.getElementsByName("email")[0];
let passwordElement = document.getElementsByName("password")[0];
let loginButton = document.getElementById("login");

loginButton.addEventListener("click", ev => {
    let email = emailElement.value;
    let password = passwordElement.value;

    fetch("http://localhost:8080/login", {
        method: 'POST',
        body: JSON.stringify(
            {
                "email": email,
                "password": password
            })
    }).then(res => {
        console.log(res);
        return res.json();
    })
})
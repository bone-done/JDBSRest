let emailElement = document.getElementsByName("email")[0];
let passwordElement = document.getElementsByName("password")[0];
let passwordRepeatElement = document.getElementsByName("passwordRepeat")[0];
let firstNameElement = document.getElementsByName("firstName")[0];
let lastNameElement = document.getElementsByName("lastName")[0];
let registerButton = document.getElementById("register");

let isPasswordMatch = false;

registerButton.addEventListener("click", ev => {
    let email = emailElement.value;
    let password = passwordElement.value;
    let passwordRepeat = passwordRepeatElement.value;
    let firstName = firstNameElement.value;
    let lastName = lastNameElement.value;

    if (password !== passwordRepeat) {
        isPasswordMatch = false;
        console.error("password don't match");
        return;
    } else isPasswordMatch = true;

    let user = {
        "email": email,
        "password": password,
        "firstName": firstName,
        "lastName": lastName
    };

    fetch('http://localhost:8080/registration', {
        method: 'POST',
        body: JSON.stringify(user)
    })
        .then(res => {
            if (res.status === 200) document.location.href = "../login/login.html";
            return res;
        })


})

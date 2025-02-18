document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("registrationForm").addEventListener("submit", function (event) {
        let password = document.getElementById("password").value;
        let confirmPassword = document.getElementById("confirmPassword").value;
        let errorElement = document.getElementById("error");

        if (password !== confirmPassword) {
            event.preventDefault();
            errorElement.textContent = "Le password non combaciano!";
        } else {
            errorElement.textContent = "";
        }
    });
});
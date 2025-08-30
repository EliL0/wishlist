document.getElementById("login-form").addEventListener("submit", function(e){
    e.preventDefault();

    const username = document.getElementById("username-input").value;
    const password = document.getElementById("password-input").value;

    fetch("/login-request", {
        method: "POST",
        headers : {"Content-Type" : "applications/json"},
        body : JSON.stringify({
            "username" : username,
            "password" : password
        })
    })
    .then(res => res.json())
    .then(data => {
        if(data.success){
            window.location.href = data.redirect;
        }else{
            alert("Login fail!");
        }
    })
    .catch(err => console.log(err));
})
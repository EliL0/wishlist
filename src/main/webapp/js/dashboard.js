
document.addEventListener("DOMContentLoaded", () => loadWishlist())


function addEntry(){
    const nameField = document.getElementById("item-name");
    const urlField = document.getElementById("item-url");

    const itemName = nameField.value;
    const itemUrl = urlField.value;

    fetch("/wishlist-request", {
        method: "POST",
        headers : {"Content-Type" : "applications/json"},
        body : JSON.stringify({
            "itemName" : itemName,
            "itemUrl" : itemUrl
        })
    })
        .then(res => res.json())
        .then(data => {
            if(data.success){
                console.log("Added entry successfully")
                nameField.value = "";
                urlField.value = "";
                loadWishlist();
            }
            else{
                console.log("Failed to add wishlist entry");
            }
        })
}
function loadWishlist(){
    const wishlist = document.getElementById("wishlist")
    wishlist.innerHTML = "";

    fetch("/wishlist-request",{
        method: "GET",
    })
        .then(res => res.json())
        .then(entries => {
            entries.forEach(item =>{
                const tableRow = document.createElement("tr")
                const itemType = document.createElement("td");
                const itemUrl = document.createElement("td");

                itemType.textContent = item.name;
                itemUrl.textContent = item.url;

                tableRow.appendChild(itemType)
                tableRow.appendChild(itemUrl)
                wishlist.appendChild(tableRow)
            })
        })
        .catch(err => console.error(err));
}
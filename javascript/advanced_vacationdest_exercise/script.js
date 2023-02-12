
(function () {
    //
    "use strict"
    //
    let detailsForm = document.querySelector("#destination_details_form")
    detailsForm.addEventListener("submit", handleFormSubmit)

    function handleFormSubmit(evt) {
        evt.preventDefault()
        // Obtain the values from the form
        const destName = evt.target.elements["name"].value
        const destLocation = evt.target.elements["location"].value
        const destPhoto = evt.target.elements["photo"].value
        const destDescription = evt.target.elements["description"].value
        // clear the form
        for (let index = 0; index < detailsForm.length; index++) {
            detailsForm.elements[index].value = ""
        }
        // Run a function that creates the new card
        let destCard = createDestinationCard(destName, destLocation, destPhoto, destDescription)
        let wishListContainer = document.getElementById("destinations_container")

        if (wishListContainer.children.length === 0) {
            document.getElementById("title").innerHTML = "My Wish List"
        }
        //
        document.querySelector("#destinations_container").appendChild(destCard)
    }
//
    function createDestinationCard(name, location, photoURL, description){
        let card = document.createElement("div")
        card.className = 'card'

        let img = document.createElement("img")
        img.setAttribute('alt', name)

        const constantPhotoURL = 'images/signpost.jpg'
        if(photoURL.length === 0){
            img.setAttribute('src', constantPhotoURL)
        } else {
            img.setAttribute('src', photoURL)
        }
        card.appendChild(img)

        let cardBody = document.createElement('div')
        cardBody.className = 'card-body'

        let cardTitle = document.createElement("h3")
        cardTitle.innerText = name
        cardBody.appendChild(cardTitle)

        let cardSubTitle = document.createElement('h4')
        cardSubTitle.innerText = location
        cardBody.appendChild(cardSubTitle)

        if(description.length !== 0){
            let cardText = document.createElement('p')
            cardText.className = 'card-text'
            cardText.innerText = description
            cardBody.appendChild(cardText)
        }

        let cardDeleteButton = document.createElement("button")
        cardDeleteButton.innerText = "Remove"

        cardDeleteButton.addEventListener('click', removeDestination)
        cardBody.appendChild(cardDeleteButton)

        card.appendChild(cardBody)
        return card
    }
//
    function removeDestination(event) {
        let card = event.target.parentElement.parentElement
        card.remove()
    }
    //
})()



(function () {
    "use strict"

    let convertType = "miles"
    const heading = document.querySelector('h1')
    const intro = document.querySelector('p')
    const answer = document.getElementById('answer')
    const form = document.getElementById('convert')
    //
    document.addEventListener('keydown', function(event) {
        const key = event.code
        switch (key){
            case 'KeyK':
                convertType = "kilometers";
                heading.innerHTML = "Kilometers to Miles Converter";
                intro.innerHTML = "Type in number of Kilometers to convert the distance to miles"
                break;
            case 'KeyM':
                convertType = "miles";
                heading.innerHTML = "Miles to Kilometers Converter";
                intro.innerHTML = "Type in number of Miles to convert the distance to Kilometers"
                break;
            default:
                console.log("Hurray!!")
        }
    })
    //
    form.addEventListener('submit', function(event) {
        event.preventDefault()
        const distance = parseFloat(document.getElementById("distance").value)
        let converted = 0.0
        if(distance){
            switch (convertType) {
                case 'miles':
                    converted = distance * 1.609344;
                    answer.innerHTML = `<h2>${distance} miles convert is ${converted} kilometers</h2>`
                    break;
                case 'kilometers':
                    converted = distance * 0.612371192;
                    answer.innerHTML = `<h2>${distance} kilometers convert is ${converted} miles</h2>`
                    break;
                default:
                    console.log("Hurray!!")
            }
        } else {
            answer.innerHTML = '<h2>Provide a valid number!!</h2>'
        }
    })
})()
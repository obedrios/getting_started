(function () {
    "use strict"

    const submenus = document.querySelectorAll('ul li ul')
    for (let index = 0; index < submenus.length; index++){
        submenus[index].className = 'hide-menu'
    }

    const menulinks = document.querySelectorAll('.menulink')
    for (let index = 0; index < menulinks.length; index++){
        menulinks[index].addEventListener('click', function(event){
            event.preventDefault()
            const thisMenu = this.parentNode.querySelector('ul')
            if(thisMenu.classList.contains('hide-menu')){
                thisMenu.className = 'show-menu'
            } else {
                thisMenu.className = 'hide-menu'
            }
        })
    }
})()
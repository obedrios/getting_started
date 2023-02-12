(function () {
    "use strict"

    jQuery('ul li ul').hide()
    jQuery('.menulink').click(function () {
        const thisMenu = jQuery(this).next('ul')
        jQuery('ul li ul').not(thisMenu).hide()
        thisMenu.toggle()
    })
})()
$(document).ready(() => {
    console.debug("Initial layout start");
    $('.profile-dropdown-button').dropdown({
        inDuration: 300,
        outDuration: 225,
        constrainWidth: false,
        hover: true,
        gutter: 0,
        belowOrigin: true,
        alignment: 'right',
        stopPropagation: false
    });

    console.debug("Initial end");
});
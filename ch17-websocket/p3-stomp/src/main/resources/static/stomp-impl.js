/*
 * SockJS libary: provide transport selection
 * STOMP: communicate with server. Data is encoded in JSON
 */
var stomp = Stomp.over(new SockJS("/stomp/ws"));

function displayStockPrice(frame) {
    var prices = JSON.parse(frame.body);

    $('#price').empty();

    for (var i in prices) {
        var price = prices[i];

        $('#price').append(
            $('<tr>').append(
                $('<td>').html(price.code),
                $('<td>').html(price.price.toFixed(2)),
                $('<td>').html(price.dateFormatted)
            )
        );
    }
}

// subscribe to /topic/price
var connectCallback = function() {
    stomp.subscribe('topic/price', displayStockPrice);
};

var errorCallback = function(error) {
    alert(error.headers.message);
}

// stomp connection
stomp.connect("guest", "guest", connectCallback, errorCallback);

$(document).ready(function() {
    $('.addStockButton').click(function (e) {
        e.preventDefault();
        var jsonstr = JSON.stringify({ 'code': $('.addStock .code').val(),
            'price': Number($('.addStock .price').val()) });

        stomp.send("/app/addStock", {}, jsonstr);

        return false;
    });
});
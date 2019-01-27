var http = require('http'),
    express = require('express'),
    app = express();

app.get('/', function (req, res) {
    var obj = { random: Math.floor((Math.random() * 99) + 1) };
    res.json(obj);
});

var server = app.listen(8080, function () {
    console.log("Random server listening on port 8080");
});
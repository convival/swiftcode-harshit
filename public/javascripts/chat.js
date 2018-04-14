var app = angular.module('chatApp', ['ngMaterial']);

app.controller('chatController', function ($scope) {
    $scope.messages = [
        {
            'sender': 'USER',
            'text': 'Hello'
	},
        {
            'sender': 'BOT',
            'text': 'Hi, what can I do for you?'
	},
        {
            'sender': 'USER',
            'text': 'How are you?'
	},
        {
            'sender': 'BOT',
            'text': 'Im good, how are you?'
	},
        {
            'sender': 'USER',
            'text': 'Good'
	}
	];
});
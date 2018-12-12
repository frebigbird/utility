if (typeof firebase === 'undefined') throw new Error('hosting/init-error: Firebase SDK not detected. You must include it before /__/firebase/init.js');
firebase.initializeApp({
	"apiKey": "AIzaSyCDOEOqjYIWU7bAy7byt-CArua2KjGiVwc",
	"databaseURL": "https://fcmtest-2d819.firebaseio.com",
	"storageBucket": "fcmtest-2d819.appspot.com",
	"authDomain": "fcmtest-2d819.firebaseapp.com",
	"messagingSenderId": "528456287285",
	"projectId": "fcmtest-2d819"
});
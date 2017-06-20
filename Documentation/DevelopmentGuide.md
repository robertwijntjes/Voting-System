# Development Guide

**Name:** Eryk Szlachetka</br>
**Student number:** C14386641 DT282/3</br>
**Contact:** c14386641@mydit.ie

The purpose of this document is to get each member of the team familiar with the development resources. </br>


## Table Of Contents
* [Description](#desc) </br>
  * [What is Parse Server](#wiParse)</br>
  * [What is Heroku](#wiHeroku)</br>
* <a href="#htAPI">How to use Parse Server API</a> </br>
	* [Web Dev](#webDev)</br>
	* [Android](#mobile)</br>
* <a href="#access">Accessing Parse Dashboard</a></br>
* <a href="#resources">Resources</a></br>

<a id="desc"></a>

## Description

We are using **Parse Server** deployed on **Heroku**.

<a id="wiParse"></a>

### What is Parse Server?

Parse Server is an open source version of the Parse backend that can be deployed to any infrastructure that can run Node.js.</br>
It has a huge API that will allow us to access the database easily.</br>
Please refer to the accessing parse dashboard tutorial below, which will allow running your own database interface on your local machine, so therefore you will not need to access the MongoLab through the command line.

<a id="wiHeroku"></a>

### What is Heroku?

Heroku is a cloud Platform-as–a-Service (PaaS) supporting several languages and is used as a web application deployment model, supports Ruby, Java, Node.js, Python, PHP and few more languages.</br>
This is the service we are going to use and we can combine it with Parse Server using node.js.</br>
 
 <a id="htAPI"></a>
 
## How to use Parse Server API ?

Parse Server has a lot of built-in libraries which consist of made up functions in which we are free to use so therefore, we do not need to re-write the code ourselves.</br>
I do recommend visiting the official github: https://github.com/ParsePlatform/parse-server-example

 <a id="webDev"></a>
 
### Web-Dev

There are few options to access parse server, I am going to introduce you to two ways accessing the server.

#### JavaScript

Parse server has a library built-in for javascript, again please visit the ParsePlatform github for more information.

##### Example Code:
``` javascript
Parse.initialize('myAppId','second par');   	// Method to initialize the parse server
Parse.serverURL = 'ParseServerURL';        	 // Method to set the server URL

// Example of creating new GameScore object
var obj = new Parse.Object('GameScore');	

obj.set('score',1337);                       // Setting the ‘score’ document to value ‘1337’ 
obj.save().then(function(obj) {              // .save is used to save the object to the databse
   	console.log(obj.toJSON());               // logging the JSON to the console
// query the databse (look for ‘GameScore’ collection
var query = new Parse.Query('GameScore'); 
// Important: each object will have auto generated ID
// get the object with id that belong to our obj
query.get(obj.id).then(function(objAgain) {     
   console.log(objAgain.toJSON());            // log to the console
   }, function(err) {console.log(err); });    // log in case of error
 }, function(err) { console.log(err); });     // log in case of error
```

#### PHP

Parse can also be accessed using PHP instead of JavaScript using Parse-PHP-SDK, I recommend visiting the official parse-php-sdk github for more detail description and more examples including how to handle users, files, cloud functions, analytics security and queries.</br>
Github: https://github.com/ParsePlatform/parse-php-sdk </br>
API: http://parseplatform.org/parse-php-sdk/namespaces/Parse.html </br>
</br>
In order to get started with Parse-PHP developers need to include all the required files form the SDK (visit github).</br>
</br>
After all the files are included, initialization and setting server URL is required, which is simillar to the one in JavaScript, just using PHP syntax:
```php
ParseClient::initialize( $app_id, $rest_key, $master_key);
ParseClient::setServerURL($serverURL. ‘parse’);
```
##### Example Code: 

```php
$object = ParseObject::create("TestObject"); 
$objectId = $object->getObjectId(); 
$php = $object->get("elephant");  // Set values: 
$object->set("elephant", "php"); 
$object->set("today", new DateTime()); 
$object->setArray("mylist", [1, 2, 3]); 
$object->setAssociativeArray(     "languageTypes", array("php" => "awesome", "ruby" => "wt") );  // Save normally: 
$object->save();  // Or pass true to use the master key to override ACLs when saving: 
$object->save(true);
```
 <a id="mobile"></a>
 
### Android

To access the Parse Server on Android we are going to use the Parse SDK for Android.</br>
Github: https://github.com/ParsePlatform/Parse-SDK-Android</br>
Guide: http://parseplatform.org/docs/android/guide/</br>
API: http://parseplatform.org/Parse-SDK-Android/api/</br>
</br>
Simillary to JavaScript and PHP, first we have to import the sdk and initialize it.</br>
</br>
In order to import the sdk we have to add it to the dependencies in the gradle on the module level.</br>

```java
dependencies {
     (…)
    compile 'com.parse:parse-android:1.13.0' // Parse database
    (…)
}
```
After that we can create a class in which we can import the parse library and initialize it with our cridentials.</br>

```java
// Developed by Eryk Szlachetka
import com.parse.Parse;

public class ParseInitialize extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);

        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId("**ID**")
                .clientKey("clientKEY, can be NULL")
                .server("http://*.herokuapp.com/parse/")
                .build()
        );

        Log.i("Main"," Initialized");
    }
}
```
#### Example Codes

<b>ParseUser.getCurrentUser()</b> - checks if the user is currently logged.

```java
// Developed by Eryk Szlachetka
if(ParseUser.getCurrentUser() == null) {
    login();
}else{
    Log.i("Login: ", "Starting Main");
    Intent i = new Intent(getApplicationContext(),MainActivity.class);
    startActivity(i);
}
```

<b>ParseQuery</b> – Queries the database

```java
// Developed by Eryk Szlachetka
ParseQuery<ParseObject> posts_query = new ParseQuery<>(TAGS.CAR_POST);
posts_query.whereEqualTo(TAGS.OBJECTID_TAG,fav_id);
posts_query.findInBackground(new FindCallback<ParseObject>() {
    @Override
    public void done(List<ParseObject> objects, ParseException e) {
	// Method called when query is done.
}};
```

<b>LogInInBackground</b> – Logs in the user on a background thread.

```java
// Developed by Eryk Szlachetka
ParseUser.logInInBackground("login","password", new LogInCallback() {
    @Override
    public void done(ParseUser user, ParseException e) {
        if (e == null) {
            // Success
        } else {
            // Failed
            Toast.makeText(LoginActivity.this, R.string.loginFailed, Toast.LENGTH_SHORT).show();
        }
    }
});
```
<a id="access"></a>

## Accessing the parse-dashboard

**NOTE:** If you are copying the commands from this document on **mac**, make sure that the font styling is not copied, if you will copy with the font style it might not run correctly.

**1.**	Download node.js </br>
https://nodejs.org/en/download/

**2.**	Open Terminal/Command line

**3.**	Go to directory you want to install the parse server.</br>
(on a mac you may be requested to insert a password, installation will start, be patient)

```terminal
sudo npm install –g parse-dashboard
```
	
**4.**	After installation we can start up the interface using the command:

```cmd
parse-dashboard --appId “appId goes here” --masterKey “master key goes here” --serverURL "server url goes here" --appName E-Vote
```

**You only need to do the steps: 1 and 3 ONCE.**</br>
</br>
Afterwards whenever you want to access the interface in your browser, **open the command line** and put the command from **step 4**.</br>
After that **open your browser** and **go to the IP** specified in terminal e.g.  http://0.0.0.0.:4040/ 

<a id="resources"></a>

## Resources

### Services

**Realm**</br>
Official: https://realm.io/products/realm-mobile-database/</br>
**Heroku**</br>
Official: https://www.heroku.com</br>
Android with MYSQL</br>
Tutorial: https://www.youtube.com/watch?v=k3O3CY75ITY&list=PLshdtb5UWjSppGmM3IdygV6RusjU3KjlZ</br>
**Parse**</br>
Github: https://github.com/ParsePlatform/Parse-SDK-Android</br>
Guide: http://parseplatform.org/docs/android/guide/</br>
API: http://parseplatform.org/Parse-SDK-Android/api/</br>
Github: https://github.com/ParsePlatform/parse-php-sdk </br>
API: http://parseplatform.org/parse-php-sdk/namespaces/Parse.html </br>

### Security:

**Android**</br>
Best Practices: https://developer.android.com/training/best-security.html</br>
Ssl: https://developer.android.com/training/articles/security-ssl.html</br>
Security Config: https://developer.android.com/training/articles/security-config.html</br>


### OpenCV:

OpenCV: http://opencv.org/platforms/android.html</br>
Text Recognition Using Camera (Android): https://www.youtube.com/watch?v=xoTKpstv9f0</br>

### ZINX:

QR generator (Android):
</br>https://github.com/zxing/zxing</br>
http://stackoverflow.com/questions/8800919/how-to-generate-a-qr-code-for-an-android-application</br>
</br>
QR scanner (Android):</br>
Tutorial: https://www.numetriclabz.com/android-qr-code-scanner-using-zxingscanner-library-tutorial/</br>

### phpQRcode:

QR generator (PHP):</br>
http://phpqrcode.sourceforge.net</br>
https://sourceforge.net/projects/phpqrcode/</br>
http://phpqrcode.sourceforge.net/examples/index.php?example=001</br>
https://sourceforge.net/p/phpqrcode/wiki/Home/</br>
</br>
### REST Architecture:
https://en.wikipedia.org/wiki/Representational_state_transfer</br>
http://www.restapitutorial.com/lessons/whatisrest.html</br>

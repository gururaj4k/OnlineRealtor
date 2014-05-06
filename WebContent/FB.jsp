<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Facebook Users</title>
<script type="text/javascript">
var myTimer=null;
var timerRunning=false;
var isTimedout=false;
window.fbAsyncInit = function() {
    FB.init({
      appId      : '118933164930669', // App ID
      channelUrl : 'http://localhost:8080/TestWeb/fbtest.jsp', // Channel File
      status     : true, // check login status
      cookie     : true, // enable cookies to allow the server to access the session
      xfbml      : true  // parse XFBML
    });
    
  };
  
  function throwError(){
	  
  }
function getQueryStrings() { 
	  var assoc  = {};
	  var decode = function (s) { return decodeURIComponent(s.replace(/\+/g, " ")); };
	  var queryString = location.search.substring(1); 
	  var keyValues = queryString.split('&'); 
	  for(var i in keyValues) { 
	    var key = keyValues[i].split('=');
	    if (key.length > 1) {
	      assoc[decode(key[0])] = decode(key[1]);
	    }
	  } 

	  return assoc; 
	} 
	function getUsersFromFB() {
		//alert("11...");
		checkLoginStatus();
	}
</script>
<script type="text/javascript" language="javascript">

  function login() {
	  //alert('1');
    FB.login(function(response) {
        if (response.authResponse) {
            // connected
            pullData();  
        } else {
            // cancelled
        }
    }, {scope: 'friends_location,publish_stream'});
}

function pullData() {	
var obj=document.getElementById('userData');
obj.innerHTML='';
var qs = getQueryStrings();
var selectedCity = qs["c"];
    obj.innerHTML+='Wait..  Fetching your information.... ';
    obj.innerHTML+='<br/>';
    var query=FB.Data.query('SELECT name,uid,current_location FROM user WHERE uid IN (SELECT uid2 FROM friend WHERE uid1=me() )');
    query.wait(function(rows){
    	////alert(rows.length);
    	var count=1;
    	for (var i=0; i < rows.length; i++){
    	if(rows[i].current_location!=null && rows[i].current_location['city']==selectedCity){
    	obj.innerHTML+=count++ +'. <a href=http://facebook.com/'+rows[i].uid+'>'+rows[i].name+'</a>       Location : ' + rows[i].current_location['city'];
    	obj.innerHTML+='<br/>';
    	}
    	}
    	if(count==1)
    		obj.innerHTML+='No Friends found in this area.';
    	  });  
}

  // Load the SDK Asynchronously
 
  (function(d){
	//  alert('1');
     var js, id = 'facebook-jssdk', ref = d.getElementsByTagName('script')[0];
     if (d.getElementById(id)) {return;}
     js = d.createElement('script'); js.id = id; js.async = true;
     js.src = "//connect.facebook.net/en_US/all.js";
     ref.parentNode.insertBefore(js, ref);
   }(document));
  function checkLoginStatus() {
	   myTimer=setTimeout(function b() {isTimedout=true;document.getElementById('errorMsg').innerHTML='There is an error in accessing the Facebook. Please try again later.';},10000);
	   timerRunning=true;
	   document.getElementById('errorMsg').innerHTML='Connecting to Facebook...';
	   //alert('just before login chk');
		  FB.getLoginStatus(function(response) {
			 // alert('checking the login status');
			  if (response.status === 'connected') {
				  clearTime();
				  //alert('100');
				  if(!isTimedout)
			    pullData();
			  } else if (response.status === 'not_authorized') {
				  clearTime();
				  
				  //alert('200');
				  if(!isTimedout)
			    login();
			  } else {
				  clearTime();
				  //alert('300');
				  if(!isTimedout)
			    login();
			  }
			 });
}
  function clearTime(){
	  if(timerRunning)
		  {
		 // alert("function got compleyed");
		 document.getElementById('errorMsg').innerHTML='';
		  clearTimeout(myTimer);
		  }
  }
</script>
</head>
<body onload="getUsersFromFB()">
<h3><span id="errorMsg"></span> </h3>
<div id="userData">
</div>
</body>
</html>
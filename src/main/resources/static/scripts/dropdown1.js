


 function register()
 {
  var email=document.getElementById("email").value;
  var username=document.getElementById("username").value;
  var password=document.getElementById("password").value;
  
  
  fetch(`http://localhost:8080/api/stock/register/${username}/${password}/${email}`, {
  method: 'POST'
})
.then((response) => { return response.json();})
.then(result =>
	{
	console.log(result);
		if(result=="1")
 			{
 			 		alert("Registration success");
 			 			setTimeout(() => { console.log("success");}, 5000);

 
 					window.location.href='/login.html';	
 			}	
 			
 	else 
 		alert("Registration Failed Try other username");
	});
	
 }
 
 
 
 function login()
 {
  var username=document.getElementById("username").value;
  var password=document.getElementById("password").value;
  
  
  fetch(`http://localhost:8080/api/stock/login/${username}/${password}`, {
  method: 'GET',
  mode: 'same-origin', // no-cors, *cors, same-origin
    cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
    credentials: 'same-origin', // include, *same-origin, omit
    headers: {
      'Content-Type': 'application/json'
    },
  
})
.then((response) => { return response.text();})
.then(result =>
	{
	console.log(result);
		if(result=="1")
 			{
 			 		alert("Login success");
 					window.location.href='/stocksrestapi.html?name=' + encodeURIComponent(username);
 			}	
 			
 	else 
 		alert("Login Failed Try Again");
	})
.catch(error => {
		alert(error);
	});
	
 }
 
 
 
 function login2()
 {
  var username=document.getElementById("username").value;
  var password=document.getElementById("password").value;
  
  
  fetch(`http://localhost:8080/api/stock/login2/`, {
  method: 'POST',
  mode: 'same-origin', // no-cors, *cors, same-origin
    cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
    credentials: 'same-origin', // include, *same-origin, omit
    headers: {
      'Content-Type': 'application/json'
    },
  
})
.then((response) => { return response.text();})
.then(result =>
	{
	console.log(result);
		if(result=="1")
 			{
 			 		alert("Login success");
 					window.location.href='/stocksrestapi.html?name=' + encodeURIComponent(username);
 			}	
 			
 	else 
 		alert("Login Failed Try Again");
	})
.catch(error => {
		alert(error);
	});
	
 }
 
 function userdata1()
 {

	const params = (new URL (document.location)).searchParams;
	const userName= params.get('name');
	// document.getElementById("nameuser").innerHTML=userName;
	console.log(userName);
	
 	window.location.href='/stocksrestapi.html?name=' + encodeURIComponent(userName);
 }
 
 function userdata2()
 {

	const params = (new URL (document.location)).searchParams;
	const userName= params.get('name');
	// document.getElementById("nameuser").innerHTML=userName;
	console.log(userName);
	
 	window.location.href='/history.html?name=' + encodeURIComponent(userName);
 }
 
 function userdata3()
 {

	const params = (new URL (document.location)).searchParams;
	const userName= params.get('name');
	// document.getElementById("nameuser").innerHTML=userName;
	console.log(userName);
	
 	window.location.href='/holdings.html?name=' + encodeURIComponent(userName);
 }
 
 function getStocks()
{
	const url="http://localhost:8080/api/stock/";
	fetch(url)
		.then(response => { return response.json();}) 
		.then(stocks => {			
			if (stocks.length > 0) {
				 var temp = "";
				 stocks.forEach((itemData) => {
					
					 temp += "<tr>";
					 
					 temp += "<td>" + itemData.company + "</td>";
					 temp += "<td>" + itemData.stockTicker + "</td>";
					 temp += "<td>" + itemData.price + "</td>";
					 temp += "<td>" + itemData.volume + "</td>";
					 temp += "</tr>";
					
					});
				 document.getElementById('tbodyStocks').innerHTML = temp; 
				 }	
			})
		
}
function dropDown1() {
	const url="http://localhost:8080/api/stock/dropDown1";
	fetch(url)//promise object to return data from Rest API
		.then(response => { return response.json();}) //resolve , data from resolve is passed to next then
		.then(shippers => {			
			if (shippers.length > 0) {
				 var temp = "";
				 temp += "<option value=''>Select Stock</option>"
				 shippers.forEach((itemData) => {
					 temp += "<option value='";
					 temp += itemData.id;
					 temp += "'>";
					 temp += itemData.stockTicker + "</option>";
					});
				 document.getElementById('dropdown1').innerHTML = temp; //populate the html element with the ID of  tbodyShippers with TR tags
				 }	
			})
}


async function buy() {

	const params = (new URL (document.location)).searchParams;
	const userName= params.get('name');
	// document.getElementById("nameuser").innerHTML=userName;
	console.log(userName);
	
var stringTicker;
// var user=document.getElementById('nameuser').value;
var i = document.getElementById('dropdown1').value;
volum = document.getElementById('volume').value;

	console.log(i);
	const url=`http://localhost:8080/api/stock/getTicker/${i}`;
	const response = await fetch(url);
	var dat = await response.text();
	const data = { 
				 name:dat,
				 volume:volum
   };
	alert(JSON.stringify(data) + "Your order is getting processed") ;
	
	fetch(`http://localhost:8080/api/stock/buy/${userName}/${dat}/${data.volume}`, {
  method: 'POST'
})
.then((response) => response.text())
//Then with the data from the response in JSON...
.then((data) => {

  console.log(data);
  alert(data);
  getStocks();
});
}

async function sell() {


	const params = (new URL (document.location)).searchParams;
	const userName= params.get('name');
	// document.getElementById("nameuser").innerHTML=userName;
	console.log(userName);
	
var stringTicker;
i = document.getElementById('dropdown1').value;
volum = document.getElementById('volume').value;

	console.log(i);
	const url=`http://localhost:8080/api/stock/getTicker/${i}`;
	const response = await fetch(url);
	var dat = await response.text();
	const data = { 
					 name:dat,
					 volume:volum
					   };
	alert(JSON.stringify(data) + "Your order is getting processed");
	fetch(`http://localhost:8080/api/stock/sell/${dat}/${data.volume}/${userName}`, {
  method: 'GET'
})
.then((response) => response.text())
//Then with the data from the response in JSON...
.then((data) => {
  console.log(data);
  alert(data);
  getStocks();
});
}

async function display() {
	const selectElement = document.querySelector('#dropdown1');
	selectElement.addEventListener('change', (event) => {
  		const result = document.querySelector('.result');
  		const displayPrice = document.querySelector('.displayPrice');
  		
		const url=`http://localhost:8080/api/stock/get/${event.target.value}`;
		fetch(url)//promise object to return data from Rest API
			.then(response => { return response.json();}) //resolve , data from resolve is passed to next then
			.then(data => {
				var temp = `<i><b>Company name : </b></i>`;
				temp += `${data.company}<hr>`;
  				result.innerHTML = temp;
  				
  				temp = `<i><b>Price per stock : </b></i>`;
  				temp += `${data.price}`;
  				temp += `<br><i><b>Current available volume : </b></i>`;
  				temp += `${data.volume}<hr>`;
  				displayPrice.innerHTML = temp;
				})
	});
}

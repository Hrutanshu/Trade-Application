/**
 * 
 */
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
	alert(JSON.stringify(data));
	fetch(`http://localhost:8080/api/stock/buy/${dat}/${data.volume}`, {
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

async function sell() {
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
	alert(JSON.stringify(data));
	fetch(`http://localhost:8080/api/stock/sell/${dat}/${data.volume}`, {
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
  				temp += `<br><i><b>Current avaliable volume : </b></i>`;
  				temp += `${data.volume}<hr>`;
  				displayPrice.innerHTML = temp;
				})
	});
}

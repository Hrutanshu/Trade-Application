/**
 * 
 */
function dropDown1() {
	const url="http://localhost:8080/api/stock/dropDown1";
	fetch(url)//promise object to return data from Rest API
		.then(response => { return response.json();}) //resolve , data from resolve is passed to next then
		.then(shippers => {			
			if (shippers.length > 0) {
				 var temp = "";
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
const data = { 
					 id:document.getElementById('dropdown1').value, 
					 volume:document.getElementById('volume').value  };
	alert(JSON.stringify(data));
	console.log(data.id);
	const url=`http://localhost:8080/api/stock/getTicker/${data.id}`;
	const response = await fetch(url);
	var dat = await response.text();
	
	fetch(`http://localhost:8080/api/stock/buy/${dat}/${data.volume}`, {
  method: 'GET'
})
.then((response) => response.text())
//Then with the data from the response in JSON...
.then((data) => {
  console.log(data);
  alert(data);
});
}

async function sell() {
var stringTicker;
const data = { 
					 id:document.getElementById('dropdown1').value, 
					 volume:document.getElementById('volume').value  };
	alert(JSON.stringify(data));
	console.log(data.id);
	const url=`http://localhost:8080/api/stock/getTicker/${data.id}`;
	const response = await fetch(url);
	var dat = await response.text();
	
	fetch(`http://localhost:8080/api/stock/sell/${dat}/${data.volume}`, {
  method: 'GET'
})
.then((response) => response.text())
//Then with the data from the response in JSON...
.then((data) => {
  console.log(data);
  alert(data);
});
}

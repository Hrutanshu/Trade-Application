/**
 * 
 */
function getHistory()
{
	const url="http://localhost:8080/api/stock/history";
	fetch(url)//promise object to return data from Rest API

		.then(response => { return response.json();}) //resolve , data from resolve is passed to next then
		.then(history => {			
			if (history.length > 0) {
				 var temp = "";
				 var total;
				 history.forEach((itemData) => {
					 temp += "<tr>";
					 temp += "<td>" + itemData.id + "</td>";
					 temp += "<td>" + itemData.dateTime + "</td>";
					 temp += "<td>" + itemData.stockTicker + "</td>";
					 temp += "<td>" + itemData.price + "</td>";
					 temp += "<td>" + itemData.volume + "</td>";
					 total = itemData.price * itemData.volume;
					 temp += "<td>" + total + "</td>";
					 temp += "<td>";
					 if(itemData.buyOrSell == "Bought") {
					 	temp += "Order placed: Buy";
					 }
					 else {
					 	temp += "Order placed: Sell";
					 }
					 temp += "</td>";
					 temp += "<td>";
					 if(itemData.status_code == 0) {
					 	temp += "Order initiated";
					 }
					 else if(itemData.status_code == 1) {
					 	temp += "Order processing";
					 }
					 else if(itemData.status_code == 2) {
					 	temp += "order successful";
					 }
					 else if(itemData.status_code == 3) {
					 	temp += "Order failed";
					 }
					 temp += "</td>";
					
					
					});
				 document.getElementById('tbodyhistory').innerHTML = temp; //populate the html element with the ID of  tbodyShippers with TR tags
				 }	
			})
		
}
function getHoldings()
{
	const url="http://localhost:8080/api/stock/holdings";

	fetch(url)//promise object to return data from Rest API
		.then(response => { return response.json();}) //resolve , data from resolve is passed to next then
		.then(holdings => {			
			if (holdings.length > 0) {
				 var temp = "";
				 
				 holdings.forEach((itemData) => {
				 	
					 temp += "<tr>";
					 temp += "<td>" + itemData.id + "</td>";
					 temp += "<td>" + itemData.stockTicker + "</td>";
					 temp += "<td>" + itemData.price + "</td>";
					 temp += "<td>" + itemData.volume + "</td>";
					});
				 document.getElementById('tbodyholdings').innerHTML = temp; //populate the html element with the ID of  tbodyShippers with TR tags
				 }	
			})




}
function getdata(){
const url="http://localhost:8080/api/stock/holdings";
	var data = new google.visualization.DataTable();
  // Add columns
  data.addColumn('string', 'stockTicker');
  data.addColumn('number', 'volume');
	fetch(url)//promise object to return data from Rest API
		.then(response => { return response.json();}) //resolve , data from resolve is passed to next then
		.then(holdings => {			
			if (holdings.length > 0) {
				 var temp = "";
				 data.addRows(holdings.length);
				 var x=0;
				 holdings.forEach((itemData) => {
				 	data.setCell(x,0,itemData.stockTicker);
				 	data.setCell(x,1,itemData.volume);
				 	x++;
					
					});
					var options = {
  title:'Stock  Holdings (Volume)',
  is3D:true
};

var chart = new google.visualization.PieChart(document.getElementById('myChart'));
  chart.draw(data, options); 
				 }	
			})
				
}

function getline(){
const url="http://localhost:8080/api/stock/history";
	var data = new google.visualization.DataTable();
  // Add columns
  data.addColumn('string', 'Date');
  data.addColumn('number', '$buy');
  data.addColumn('number', '$sell');
	fetch(url)//promise object to return data from Rest API
		.then(response => { return response.json();}) //resolve , data from resolve is passed to next then
		.then(history => {			
			if (history.length > 0) {
				
				 var x=0;
				 var buy;
				 var sell;
				 let check="";
				 history.forEach((itemData) => {
				 x++;
				 if(check =="" || check.localeCompare((itemData.dateTime).slice(0,10))!=0){
				 	console.log((itemData.dateTime).slice(0,10));
				 	if (!(check=== "")){
				 	
				 	data.addRow([check,buy,sell]);
				 console.log(buy);
				 	}
				 	buy=0;sell=0;
				 	check=(itemData.dateTime).slice(0,10);
					}
					if(itemData.buyOrSell=='Bought'){
					
					buy +=((itemData.price)*(itemData.volume));}
					else{
					
					sell +=((itemData.price*itemData.volume));}
					if(x==history.length ){
					console.log(sell);
					data.addRow([check,buy,sell]);}
					});
				var options = {
        chart: {
          title: 'History Distribution',
          
        },
        width: 500,
        height: 300,
        hAxis: {
                  title: 'Date',         
               },
               vAxis: {
                  title: 'Price',        
               },
      };

      var chart = new google.charts.Line(document.getElementById('line_top_x'));

      chart.draw(data, options);
				 }	
			})
				
}

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
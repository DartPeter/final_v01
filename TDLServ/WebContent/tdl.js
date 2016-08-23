function newList() {
	var nln = prompt("Enter a name for new list");
	if (!checkInput(nln))
		return;
	var ntbl = document.createElement("table");
	ntbl.setAttribute("border", "1");
	var ntbd = document.createElement("tbody");
	// adding header
	var nr = document.createElement("tr");
	nr.setAttribute("class", "header");
	var nc = document.createElement("td");
	nc.setAttribute("colspan", "2");
	nc.setAttribute("align", "right");
	nc.setAttribute("style", "border-right:hidden;");
	var nspn = document.createElement("span");
	nspn.setAttribute("class", "glyphicon glyphicon-list-alt");
	nspn.setAttribute("style", "color:black;");
	nc.appendChild(nspn);
	nr.appendChild(nc);
	nc = document.createElement("td");
	nc.setAttribute("style", "border-right:hidden;");
	nc.innerHTML = nln;
	nr.appendChild(nc);
	nc = document.createElement("td");
	nc.setAttribute("colspan", "2");
	nc.setAttribute("align", "right");
	nspn = document.createElement("span");
	nspn.setAttribute("class", "glyphicon glyphicon-pencil");
	nspn.setAttribute("onClick", "renameList(this)");
	nc.appendChild(nspn);
	nr.appendChild(nc);
	nc = document.createElement("td");
	nspn = document.createElement("span");
	nspn.setAttribute("class", "glyphicon glyphicon-trash");
	nspn.setAttribute("onClick", "removeList(this)");
	nc.appendChild(nspn);
	nr.appendChild(nc);
	ntbd.appendChild(nr);

	// adding records adder
	nr = document.createElement("tr");
	nr.setAttribute("class", "adder");
	nc = document.createElement("td");
	nc.setAttribute("colspan", "2");
	nc.setAttribute("align", "right");
	nc.setAttribute("style", "border-right:hidden;");
	nspn = document.createElement("span");
	nspn.setAttribute("class", "glyphicon glyphicon-plus");
	nspn.setAttribute("style", "color:green;");
	nc.appendChild(nspn);
	nr.appendChild(nc);
	nc = document.createElement("td");
	nc.setAttribute("style", "border-right:hidden;");
	var ninp = document.createElement("input");
	ninp.setAttribute("type", "text");
	ninp.setAttribute("placeholder", "Start typing here to create a task...");
	ninp.setAttribute("size", "60");
	nc.appendChild(ninp);
	nr.appendChild(nc);
	nc = document.createElement("td");
	nc.setAttribute("colspan", "3");
	nc.setAttribute("width", "100");
	ninp = document.createElement("button");
	ninp.setAttribute("style", "background-color: green; color: white;");
	ninp.setAttribute("onClick", "newRec(this)");
	ninp.innerHTML = "Add Task";
	nc.appendChild(ninp);
	nr.appendChild(nc);
	ntbd.appendChild(nr);
	ntbl.appendChild(ntbd);
	var body1 = document.getElementsByTagName("body")[0];
	var btn = document.getElementById("ATDLButton");
	body1.insertBefore(ntbl, btn);
	// go to server
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		ntbl.setAttribute("id", xhttp.responseText);
	}
	var url = "http://localhost:8080/TDLServ/AddTDL?";
	url += "lname=" + nln;
	xhttp.open("GET", url, true);
	xhttp.send();
}

function newRec(me) {
	var inpVal = me.parentNode.parentNode.cells[1].children[0].value;
	if (inpVal === '') {
		alert("Input must be non empty.");
		return;
	}
	var newRow = document.createElement("tr");
	newRow.setAttribute("class", "record");
	var ntd = document.createElement("td");
	ntd.setAttribute("align", "middle");
	var nicb = document.createElement("input");
	nicb.setAttribute("type", "checkbox");
	
	nicb.setAttribute("onchange", "changeStats(this)");
	
	ntd.appendChild(nicb);
	newRow.appendChild(ntd);
	ntd = document.createElement("td");
	newRow.appendChild(ntd);
	ntd = document.createElement("td");
	ntd.appendChild(document.createTextNode(inpVal));
	newRow.appendChild(ntd);
	ntd = document.createElement("td");
	ntd.setAttribute("align", "right");
	var nicon = document.createElement("span");
	nicon.setAttribute("class", "glyphicon glyphicon-resize-vertical");
	nicon.setAttribute("onClick", "swapRecs(this)");
	ntd.appendChild(nicon);
	newRow.appendChild(ntd);
	ntd = document.createElement("td");
	ntd.setAttribute("align", "middle");
	nicon = document.createElement("span");
	nicon.setAttribute("class", "glyphicon glyphicon glyphicon-pencil");
	nicon.setAttribute("onClick", "renameRec(this)");
	ntd.appendChild(nicon);
	newRow.appendChild(ntd);
	ntd = document.createElement("td");
	nicon = document.createElement("span");
	nicon.setAttribute("class", "glyphicon glyphicon-trash");
	nicon.setAttribute("onClick", "removeRec(this)");
	ntd.appendChild(nicon);
	newRow.appendChild(ntd);
	var nti = me.parentNode.parentNode.parentNode;
	nti.appendChild(newRow);
	// go to server
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		newRow.setAttribute("id", xhttp.responseText);
	}
	var url = "http://localhost:8080/TDLServ/AddRec?";
	url += "rname=" + inpVal;
	url += "&" + "pr_id=" + me.parentNode.parentNode.parentNode.parentNode.getAttribute("id");
	xhttp.open("GET", url, true);
	xhttp.send();
}

function renameList(me) {
	var nn = prompt("Enter new name for To Do list");
	if (!checkInput(nn))
		return;
	me.parentNode.parentNode.cells[1].innerHTML = nn;
	var id = me.parentNode.parentNode.parentNode.parentNode.getAttribute("id");
	// go to server
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
	}
	var url = "http://localhost:8080/TDLServ/RenTDL?";
	url += "lname=" + nn + "&" + "id=" + id;
	xhttp.open("GET", url, true);
	xhttp.send();
}

function removeList(me) {
	var id = me.parentNode.parentNode.parentNode.parentNode.getAttribute("id");
	var tbl = me.parentNode.parentNode.parentNode.parentNode;
	tbl.parentNode.removeChild(tbl);
	// go to server
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
	}
	var url = "http://localhost:8080/TDLServ/DelTDL?";
	url += "id=" + id;
	xhttp.open("GET", url, true);
	xhttp.send();
}

function swapRecs(me) {
	var i = me.parentNode.parentNode.rowIndex;
	if (i == 2)
		return;
	var table = me.parentNode.parentNode.parentNode.parentNode;
	table.rows[i - 1].parentNode.insertBefore(table.rows[i], table.rows[i - 1]);
	
	var id1 = table.rows[i - 1].getAttribute("id");
	var id2 = table.rows[i].getAttribute("id");
	table.rows[i - 1].setAttribute("id", id2);
	table.rows[i].setAttribute("id", id1);
	
	// go to server
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
	}
	var url = "http://localhost:8080/TDLServ/SwpRec?";
	url += "id1=" + id1;
	url += "&id2=" + id2;
	xhttp.open("GET", url, true);
	xhttp.send();

	/*
	 * r1 = me.parentNode.parentNode; if(r1.rowIndex == 2) return; r2 =
	 * r1.parentNode.children[r1.rowIndex - 1]; clonEl1 = r1.cloneNode(true);
	 * clonEl2 = r2.cloneNode(true); r1.parentNode.replaceChild(clonEl1, r2);
	 * r2.parentNode.replaceChild(clonEl2, r1);
	 */

	/*
	 * r1 = me.parentNode.parentNode; alert(r1.rowIndex); r2 =
	 * r1.previousSibling; alert(r2); alert(r1.parentNode.firstChild);
	 */
}

function renameRec(me) {
	var nr = prompt("Enter new name for action");
	if (!checkInput(nr))
		return;
	me.parentNode.parentNode.children[2].innerHTML = nr;	
	// go to server
	var r_id = me.parentNode.parentNode.getAttribute("id");
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
	}
	var url = "http://localhost:8080/TDLServ/RenRec?";
	url += "r_id=" + r_id;
	url += "&" + "rname=" + nr;
	xhttp.open("GET", url, true);
	xhttp.send();
}

function removeRec(me) {
	var rrr = me.parentNode.parentNode;	
	var r_id = rrr.getAttribute("id");	
	rrr.parentNode.removeChild(rrr);	
	// go to server
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
	}
	var url = "http://localhost:8080/TDLServ/DelRec?";
	url += "r_id=" + r_id;
	xhttp.open("GET", url, true);
	xhttp.send();
}

function changeStats(me) {
	// go to server
	var r_id = me.parentNode.parentNode.getAttribute("id");
	var st = me.checked;
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
	}
	var url = "http://localhost:8080/TDLServ/CscRec?";
	url += "r_id=" + r_id;
	url += "&" + "st=" + st;
	xhttp.open("GET", url, true);
	xhttp.send();
}

function checkInput(inp) {
	if (inp === '') {
		alert("Input must be non empty.");
		return false;
	}
	return true;
}

function readData() {
	readProjects();
	readRecords();
}

function readProjects() {
	var result;
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		result = xhttp.responseXML;
		if (result == null)
			return;
		x = result.getElementsByTagName("id");
		y = result.getElementsByTagName("name");
		for (i = 0; i < x.length; i++) {
			var nln = y[i].childNodes[0].nodeValue;
			var ntbl = document.createElement("table");
			ntbl.setAttribute("border", "1");
			var ntbd = document.createElement("tbody");
			// adding header
			var nr = document.createElement("tr");
			nr.setAttribute("class", "header");
			var nc = document.createElement("td");
			nc.setAttribute("colspan", "2");
			nc.setAttribute("align", "right");
			nc.setAttribute("style", "border-right:hidden;");
			var nspn = document.createElement("span");
			nspn.setAttribute("class", "glyphicon glyphicon-list-alt");
			nspn.setAttribute("style", "color:black;");
			nc.appendChild(nspn);
			nr.appendChild(nc);
			nc = document.createElement("td");
			nc.setAttribute("style", "border-right:hidden;");
			nc.innerHTML = nln;
			nr.appendChild(nc);
			nc = document.createElement("td");
			nc.setAttribute("colspan", "2");
			nc.setAttribute("align", "right");
			nspn = document.createElement("span");
			nspn.setAttribute("class", "glyphicon glyphicon-pencil");
			nspn.setAttribute("onClick", "renameList(this)");
			nc.appendChild(nspn);
			nr.appendChild(nc);
			nc = document.createElement("td");
			nspn = document.createElement("span");
			nspn.setAttribute("class", "glyphicon glyphicon-trash");
			nspn.setAttribute("onClick", "removeList(this)");
			nc.appendChild(nspn);
			nr.appendChild(nc);
			ntbd.appendChild(nr);
			
			// adding records adder
			nr = document.createElement("tr");
			nr.setAttribute("class", "adder");
			nc = document.createElement("td");
			nc.setAttribute("colspan", "2");
			nc.setAttribute("align", "right");
			nc.setAttribute("style", "border-right:hidden;");
			nspn = document.createElement("span");
			nspn.setAttribute("class", "glyphicon glyphicon-plus");
			nspn.setAttribute("style", "color:green;");
			nc.appendChild(nspn);
			nr.appendChild(nc);
			nc = document.createElement("td");
			nc.setAttribute("style", "border-right:hidden;");
			var ninp = document.createElement("input");
			ninp.setAttribute("type", "text");
			ninp.setAttribute("placeholder", "Start typing here to create a task...");
			ninp.setAttribute("size", "60");
			nc.appendChild(ninp);
			nr.appendChild(nc);
			nc = document.createElement("td");
			nc.setAttribute("colspan", "3");
			nc.setAttribute("width", "100");
			ninp = document.createElement("button");
			ninp.setAttribute("style", "background-color: green; color: white;");
			ninp.setAttribute("onClick", "newRec(this)");
			ninp.innerHTML = "Add Task";
			nc.appendChild(ninp);
			nr.appendChild(nc);
			ntbd.appendChild(nr);
			
			ntbl.appendChild(ntbd);
			ntbl.setAttribute("id", "tbl" + x[i].childNodes[0].nodeValue);
			// add table to doc
			var body1 = document.getElementsByTagName("body")[0];
			var btn = document.getElementById("ATDLButton");
			body1.insertBefore(ntbl, btn);
		}
	}
	var url = "http://localhost:8080/TDLServ/LoadTDLs";
	xhttp.open("GET", url, true);
	xhttp.send();
}

///////////////////////////

function readRecords() {
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		result = xhttp.responseXML;
		if (result == null)
			return;
		var ids = result.getElementsByTagName("id");
		var names = result.getElementsByTagName("name");
		var stats = result.getElementsByTagName("stat");
		var prids = result.getElementsByTagName("prid");
		
		for (i = 0; i < ids.length; i++) {
			var insTbl = document.getElementById("tbl" + prids[i].childNodes[0].nodeValue);
			if(insTbl == null) continue;
			var inpVal = names[i].childNodes[0].nodeValue;
			var newRow = document.createElement("tr");
			newRow.setAttribute("class", "record");
			var ntd = document.createElement("td");
			ntd.setAttribute("align", "middle");
			var nicb = document.createElement("input");
			nicb.setAttribute("type", "checkbox");
			if(stats[i].childNodes[0].nodeValue == "true") {
				nicb.checked = true;
			}
			nicb.setAttribute("onchange", "changeStats(this)");			
			ntd.appendChild(nicb);
			newRow.appendChild(ntd);
			ntd = document.createElement("td");
			newRow.appendChild(ntd);
			ntd = document.createElement("td");
			ntd.appendChild(document.createTextNode(inpVal));
			newRow.appendChild(ntd);
			ntd = document.createElement("td");
			ntd.setAttribute("align", "right");
			var nicon = document.createElement("span");
			nicon.setAttribute("class", "glyphicon glyphicon-resize-vertical");
			nicon.setAttribute("onClick", "swapRecs(this)");
			ntd.appendChild(nicon);
			newRow.appendChild(ntd);
			ntd = document.createElement("td");
			ntd.setAttribute("align", "middle");
			nicon = document.createElement("span");
			nicon.setAttribute("class", "glyphicon glyphicon glyphicon-pencil");
			nicon.setAttribute("onClick", "renameRec(this)");
			ntd.appendChild(nicon);
			newRow.appendChild(ntd);
			ntd = document.createElement("td");
			nicon = document.createElement("span");
			nicon.setAttribute("class", "glyphicon glyphicon-trash");
			nicon.setAttribute("onClick", "removeRec(this)");
			ntd.appendChild(nicon);
			newRow.appendChild(ntd);
			newRow.setAttribute("id", "rec" + ids[i].childNodes[0].nodeValue);
			insTbl.firstChild.appendChild(newRow);
		}
	}
	var url = "http://localhost:8080/TDLServ/LoadRecs";
	xhttp.open("GET", url, true);
	xhttp.send();
}
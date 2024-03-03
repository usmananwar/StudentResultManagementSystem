const serverUrl="http://localhost:9090"


function showHomePage() {
	document.getElementById('content').innerHTML = '<h1>WELCOME</h1>';
}

function showAddStudentsPage() {
	const studentForm = document.getElementById('addStudentFormTemplate');
	const clone = studentForm.cloneNode(true);
	clone.id = "addStudentForm";
	showContent(clone);
}

function showContent(clone) {
	clearMsgBar();
	document.getElementById("content").innerHTML = '';
	document.getElementById("content").appendChild(clone);
	clone.style.display = "block";
}



function showAddCoursesPage() {	
	const form = document.getElementById('addCourseFormTemplate');
	const clone = form.cloneNode(true);
	clone.id = "addCourseForm";
	showContent(clone);
}

function clearMsgBar() {
	$("#msgBar").text('');
}


function showStudentsListPage() {
	clearMsgBar();
	
	$.ajax({
		type : "GET",
		url : serverUrl+"/api/student/getAllStudents", 
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data) {
			console.log("Success:", data);
			
			var table = '<table><th> Name & Family name</th> <th> DOB</th> <th> Email</th><th> Delete</th>';
			for (var student of data) {			    
			        table += '<tr>';
			        table += '<td>' +  student.firstName + ' '+ student.familyName +'</td>';
			        table += '<td>' +  student.dob +'</td>';
			        table += '<td>' +  student.email +'</td>';
			        table += '<td> <a href="#" onclick="deleteStudent('+student.id+');"> X </a> </td>';
			        table += '</tr>';			    
			}
			table += '</table >';
			
			document.getElementById("content").innerHTML = table;
			
			
		},
		error : function(error) {	
			
			var formattedMsg = '<ul>';
			for (var line of error.responseJSON.errors) {			    
			        formattedMsg += '<li>' + line.trim() + '</li>';			    
			}
			formattedMsg += '</ul>';
			
			$("#msgBar").html(formattedMsg);			
			
		}
	});
	
}


function deleteStudent(id) {	
	
	$.ajax({
		type : "GET",
		url : serverUrl+'/api/student/deleteStudent?id='+id, 
		contentType : "application/json; charset=utf-8",
		success : function() {
			document.getElementById("content").innerHTML = '';
			showStudentsListPage();	
					
		},
		error : function(error) {
			console.log(error);
		}
	});
	
}

function showCoursesListPage() {
	clearMsgBar();
	
	$.ajax({
		type : "GET",
		url : serverUrl+"/api/course/getAllCourses", 
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data) {
			console.log("Success:", data);
			
			var table = '<table><th>Course Name</th> <th>Delete</th>';
			for (var course of data) {			    
			        table += '<tr>';
			        
			        table += '<td>' + course.name  +'</td>';
			        
			        table += '<td> <a href="#" onclick="deleteCourse('+course.id+');"> X </a> </td>';
			        table += '</tr>';			    
			}
			table += '</table >';
			
			document.getElementById("content").innerHTML = table;
			
			
		},
		error : function(error) {	
			
			var formattedMsg = '<ul>';
			for (var line of error.responseJSON.errors) {			    
			        formattedMsg += '<li>' + line.trim() + '</li>';			    
			}
			formattedMsg += '</ul>';
			
			$("#msgBar").html(formattedMsg);			
			
		}
	});
	
}


function deleteCourse(id) {	
	
	$.ajax({
		type : "GET",
		url : serverUrl+'/api/course/deleteCourse?id='+id, 
		contentType : "application/json; charset=utf-8",
		success : function() {
			document.getElementById("content").innerHTML = '';
			showCoursesListPage();	
					
		},
		error : function(error) {
			console.log(error);
		}
	});
	
}


function submitAddNewStudent() {

	var formData = {
		firstName : $("#firstName").val(),
		familyName : $("#familyName").val(),
		dob : $("#dob").val(),
		email : $("#email").val()
	};


	$.ajax({
		type : "POST",
		url : serverUrl+"/api/student/add", 
		data : JSON.stringify(formData),
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data) {
			console.log("Success:", data);
			$("#firstName").val('');
			$("#familyName").val('');
			$("#dob").val('');
			$("#email").val('');			
			
			
			$("#msgBar").text(data.message);
		},
		error : function(error) {	
			
			var formattedMsg = '<ul>';
			for (var line of error.responseJSON.errors) {			    
			        formattedMsg += '<li>' + line.trim() + '</li>';			    
			}
			formattedMsg += '</ul>';
			
			$("#msgBar").html(formattedMsg);		
			
		}
	});
}


function submitAddNewCourse() {

	var formData = {
		name : $("#courseName").val(),
	};


	$.ajax({
		type : "POST",
		url : serverUrl+"/api/course/add", 
		data : JSON.stringify(formData),
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data) {
			console.log("Success:", data);
			$("#courseName").val('');
			
			
			$("#msgBar").text(data.message);
		},
		error : function(error) {
		
			
			var formattedMsg = '<ul>';
			for (var line of error.responseJSON.errors) {			    
			        formattedMsg += '<li>' + line.trim() + '</li>';			    
			}
			formattedMsg += '</ul>';
			
			$("#msgBar").html(formattedMsg);			
			
		}
	});
}


function showAddResultsPage() {
	
	
	var promises = [];
	
	promises.push(
		$.ajax({
	      url: serverUrl+"/api/student/getAllStudents",
	      method: 'GET',
	      dataType: 'json'
	    })		
	);
	
	promises.push(
		$.ajax({
	      url: serverUrl+"/api/course/getAllCourses",
	      method: 'GET',
	      dataType: 'json'
	    })		
	);
	
	
	
	Promise.all(promises)
    	.then(function(results) {
		  
		  	fillStudentDropdown(results[0]);
		  	fillCourseDropdown(results[1]);
		  
		  	const studentForm = document.getElementById('addResultFormTemplate');
			const clone = studentForm.cloneNode(true);
			clone.id = "addResultForm";
			showContent(clone);      
     });
	
}

function fillStudentDropdown(data) {
    var dropdown = document.getElementById('studentDropdown');

    dropdown.innerHTML = '';
    
    
     var option = document.createElement('option');
      option.value = "";
      option.text = "--- Select ---";
      dropdown.appendChild(option);
    
    data.forEach(function(student) {
      var option = document.createElement('option');
      option.value = student.id;
      option.text = student.firstName +' ' +student.familyName;
      dropdown.appendChild(option);
    });
}

function fillCourseDropdown(data) {
    var dropdown = document.getElementById('courseDropdown');

    dropdown.innerHTML = '';
    
      var option = document.createElement('option');
      option.value = "";
      option.text = "--- Select ---";
      dropdown.appendChild(option);

    data.forEach(function(course) {
      var option = document.createElement('option');
      option.value = course.id;
      option.text = course.name;
      dropdown.appendChild(option);
    });
}







function submitAddNewResult() {
	
	var s = null;
	
	if ($("#studentDropdown").val() != '') {
		s = {
			id: $("#studentDropdown").val()
		};
	}
	
	var c = null;
	
	if ($("#studentDropdown").val() != '') {
		c = {
			id: $("#courseDropdown").val()
		};
	}

	var formData = {
		student: s,
		course: c,
		score : $("#scroreDropdown").val(),
		
	};


	$.ajax({
		type : "POST",
		url : serverUrl+"/api/result/add", 
		data : JSON.stringify(formData),
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data) {
			console.log("Success:", data);
			$("#studentDropdown").val('');
			$("#courseDropdown").val('');
			$("#scroreDropdown").val('');
			
			
			$("#msgBar").text(data.message);
		},
		error : function(error) {		
			
			var formattedMsg = '<ul>';
			for (var line of error.responseJSON.errors) {			    
			        formattedMsg += '<li>' + line.trim() + '</li>';			    
			}
			formattedMsg += '</ul>';
			
			$("#msgBar").html(formattedMsg);			
			
		}
	});
}







function showResultsListPage() {
	clearMsgBar();
	
	$.ajax({
		type : "GET",
		url : serverUrl+"/api/result/getAllResults", 
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data) {
			console.log("Success:", data);
			
			var table = '<table><th>Course</th> <th>Student</th> <th>Score</th>';
			for (var item of data) {			    
			        table += '<tr>';
			        
			        table += '<td>' + item.course.name  +'</td>';
			        table += '<td>' + item.student.firstName + ' ' + item.student.familyName + '</td>';
			        table += '<td>' + item.score  +'</td>';
			        
			        
			        table += '</tr>';			    
			}
			table += '</table >';
			
			document.getElementById("content").innerHTML = table;
			
			
		},
		error : function(error) {	
			
			var formattedMsg = '<ul>';
			for (var line of error.responseJSON.errors) {			    
			        formattedMsg += '<li>' + line.trim() + '</li>';			    
			}
			formattedMsg += '</ul>';
			
			$("#msgBar").html(formattedMsg);			
			
		}
	});
}






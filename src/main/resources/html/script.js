let ajax = new XMLHttpRequest();
let result;

window.onload = () => {
    document.getElementById("default").click();
    listEmployee();
}

function deleteEmployee() {
    let ajax = new XMLHttpRequest();
    
    ajax.onreadystatechange = () => {
        if (ajax.readyState === 4 && ajax.status === 200) {
            let output = '';
            
            result = JSON.parse(ajax.responseText);
            
            if (result['status'].toLowerCase() === 'deleted') {
                alert('User successfull deleted!');
            } else if (result['status'].toLowerCase() === 'unknown') {
                alert('User ID not found in database!');
            } else {
                alert('Unknown error, please try again...')
            }
            
            document.getElementById('employee_delete_id').value = '';
            
            listEmployee();
        }
    }
    
    let id = Number.parseInt(document.getElementById('employee_delete_id').value);

    if (Number.isNaN(id)) {
        alert('Please provide a valid User-ID!');
        return;
    }

    let url = `http://localhost:4567/users/${id}`;
    
    ajax.open("DELETE", url, true);
    ajax.send();
}

function listEmployee() {
    let ajax = new XMLHttpRequest();

    ajax.onreadystatechange = () => {
        if (ajax.readyState === 4 && ajax.status === 200) {
            let output = '';

            result = JSON.parse(ajax.responseText);

            document.getElementById('output').innerHTML = '';

            for (i in result.msg) {
                output += (i % 2 === 0) ? '<tr class="row">' : '<tr>';
                for (j in result.msg[i]) {
                    if (typeof result.msg[i][j] === 'object') {
                        output += `<td>${result.msg[i][j]['year']}-${result.msg[i][j]['month']}-${result.msg[i][j]['day']}</td>`;
                    } else {
                        output += `<td>${result.msg[i][j]}</td>`;
                    }
                }
                output += '</tr>';
            }

            document.getElementById('output').innerHTML = output;

            document.getElementById('employee_list_id').value = '';
        }
    }

    let id = Number.parseInt(document.getElementById('employee_list_id').value);

    // if (Number.isNaN(id)) {
    //     alert('Please enter a valid ID!');
    //     return;
    // }

    let url = '';

    if (id >= 1) {
        url = `http://localhost:4567/users/${id}`
    } else {
        url = 'http://localhost:4567/users';
    }

    ajax.open("GET", url, true);
    ajax.send();
}

function addEmployee() {
    let ajax = new XMLHttpRequest();

    ajax.onreadystatechange = () => {
        if (ajax.readyState === 4 && ajax.status === 200) {
            let output = '';

            result = JSON.parse(ajax.responseText);

            document.getElementById('output').innerHTML = '';

            for (i in result.msg) {
                output += (i % 2 === 0) ? '<tr class="row">' : '<tr>';
                for (j in result.msg[i]) {
                    if (typeof result.msg[i][j] === 'object') {
                        output += `<td>${result.msg[i][j]['year']}-${result.msg[i][j]['month']}-${result.msg[i][j]['day']}</td>`;
                    } else {
                        output += `<td>${result.msg[i][j]}</td>`;
                    }
                }
                output += '</tr>';
            }

            document.getElementById('output').innerHTML = output;

            document.getElementById('employee_list_id').value = '';
        }
    }

    let user = {};

    user['firstName'] = document.getElementById('add_input_firstname').value;
    user['lastName'] = document.getElementById('add_input_lastname').value;
    user['birthDate'] = {};
    user['birthDate']['year'] = document.getElementById('add_input_birthdate').value.split('-')[0];
    user['birthDate']['month'] = document.getElementById('add_input_birthdate').value.split('-')[1];
    user['birthDate']['day'] = document.getElementById('add_input_birthdate').value.split('-')[2];
    user['gender'] = document.getElementById('add_input_gender').value;
    user['company'] = document.getElementById('add_input_company').value;
    user['department'] = document.getElementById('add_input_department').value;
    user['employerId'] = document.getElementById('add_input_employerid').value;
    user['email'] = document.getElementById('add_input_email').value;

    let url = 'http://localhost:4567/users';

    ajax.open("POST", url, true);
    ajax.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    ajax.send(JSON.stringify(user));
}

function showInput(event, element) {
    // Declare all variables
    var i, input, tablinks;

    input = document.getElementsByClassName("input");
    for (i = 0; i < input.length; i++) {
        input[i].style.display = "none";
    }

    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }

    document.getElementById(element).style.display = "block";
    event.currentTarget.className += " active";
}
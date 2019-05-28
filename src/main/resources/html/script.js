let ajax = new XMLHttpRequest();
let result;

window.onload = () => {
    ajax.onreadystatechange = () => {
        if (ajax.readyState === 4 && ajax.status === 200) {
            let output = '';

            result = JSON.parse(ajax.responseText);

            document.getElementById('content').innerHTML = '';

            output += '<thead><tr class="header">';
            output += '<td>id</td><td>firstname</td><td>lastname</td><td>birthdate</td><td>gender</td><td>company</td><td>department</td><td>employerid</td><td>email</td>';
            output += '</tr></thead>';
            output += '<tbody>';

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

            output += '</tbody>'

            document.getElementById('content').innerHTML = output;
        }
    }

    ajax.open("GET", "http://localhost:4567/users", true);
    ajax.send();
}
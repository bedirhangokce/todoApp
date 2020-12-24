$(document).ready(function () {
    //CSRF protection
    var logged = $('#todoUsername').val();
    console.log(logged)
    var role = $('#user-role').val();
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    //Sort table
    if (role === "[ROLE_USER]") {
        console.log("user")
        $('#todoTable').DataTable({
            columnDefs: [{
                orderable: false,
                targets: [5, 6, 7]
            }]
        });
    }
    else {
        $('#todoTable').DataTable({
            columnDefs: [{
                orderable: true,
            }]
        });
    }
    //Date Filter
    $('.dataTables_length').addClass('bs-select');

    $.fn.dataTable.ext.search.push(
        function (settings, data) {
            var one = $('#datepicker-min').val();
            var min = new Date(one);
            var two = $('#datepicker-max').val();
            var max = new Date(two);
            var date = new Date(data[3]); // use data for the date column
            if ((isNaN(min) && isNaN(max)) ||
                (isNaN(min) && date <= max) ||
                (min <= date && isNaN(max)) ||
                (min <= date && date <= max)) {
                return true;
            }
            return false;
        }
    );

    $(document).ready(function () {
        var table = $('#todoTable').DataTable();
        // Event listener to the two range filtering
        $('#datepicker-min, #datepicker-max').change(function () {
            table.draw();
        });
    });

    //Add entity
    $('.todo-button').click(function () {
        var description = $('#description').val();
        var date = new Date($('#date-value').val());
        var status = $('#statusSelect').val();
        var owner = $('#todoUsername').val();
        var createDate = new Date(Date.now());

        date = formatDate(date);
        createDate = formatDate(createDate)
        console.log(owner)
        if (!$.trim(description)) {
            alert("You have to define a TODO");
        }
        else if (date === "NaN-NaN-NaN") {
            alert("You have to define a date");
        }
        else if (date < createDate) {
            alert("You cannot define date before today")
        }
        else {
            var obj = {
                description: description,
                date: date,
                createDate: createDate,
                owner: owner,
                status: status

            };
            $.ajax({
                data: JSON.stringify(obj),
                url: '/addTodo',
                method: 'POST',
                contentType: 'application/json',
                //CSRF Protection
                beforeSend: function (xhr) {
                    xhr.setRequestHeader(header, token);
                },
                success: function (data) {
                    console.log(data)
                    setTimeout(function () {
                        location.reload();
                    }, 100);
                }
                ,
                error: function (id) {
                    console.log("cannot add" + this.data);
                    alert("TODO couldn't add");
                }
            });
        }
    });
    //Change status to done
    $('.done-button').click(function () {
        const id = $(this).val();
        $.ajax({
            url: '/todo/' + id,
            method: 'PUT',
            //CSRF Protection
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            success: function () {
                console.log("done: " + id);
                setTimeout(function () {
                    location.reload();
                }, 100);
            },
            error: function (id) {
                console.log("cannot done" + id);
            }
        })
    })
    //Change status to delayed
    $('.delay-button').click(function () {
        const id = $(this).val();
        $.ajax({
            url: '/todoDelay/' + id,
            async: false,
            method: 'PUT',
            //CSRF Protection
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            success: function () {
                console.log("delayed: " + id);
                setTimeout(function () {
                    location.reload();
                }, 100);
            },
            error: function (id) {
                console.log("cannot delayed" + id);
            }
        })
    })
    //Delete entity
    $('.delete-button').click(function () {
        const id = $(this).val();
        $.ajax({
            url: '/todoDelete/' + id,
            method: 'DELETE',
            contentType: 'application/json',
            //CSRF Protection
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            success: function () {
                console.log("deleted: " + id);
                setTimeout(function () {
                    location.reload();
                }, 100);
            },
            error: function (id) {
                console.log("cannot deleted" + id);
            }
        })
    })
    //Add User
    $('#user-button').click(function () {
        const username = $('#addUserUsername').val();
        const password = $('#addUserPassword').val();
        const role = $('#addUserRole').val();
        var obj = {
            username: username,
            password: password,
            role: role
        };
        if (!$.trim(username)) {
            alert("Enter a valid username");
        }
        else if (!$.trim(password)) {
            alert("Enter a valid password");
        }
        else {
            $.ajax({
                url: '/addUser',
                method: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(obj),
                //CSRF Protection
                beforeSend: function (xhr) {
                    xhr.setRequestHeader(header, token);
                },
                success: function (data) {
                    console.log(data)
                    setTimeout(function () {
                        location.reload(); 
                    }, 100);
                }
                ,
                error: function (id) {
                    console.log("cannot add" + this.data);
                    alert("User couldn't add");
                }
            });
        }
    })
    //Change role of User
    $('.change-role-button').click(function () {
        const username = $(this).val();
        const logged = $('#todoUsername').val();
        if (username != logged) {
            $.ajax({
                url: '/changeRole/' + username,
                method: 'PUT',
                //CSRF Protection
                beforeSend: function (xhr) {
                    xhr.setRequestHeader(header, token);
                },
                success: function () {
                    console.log("changed role of : " + username);
                    setTimeout(function () {
                        location.reload();
                    }, 100);
                },
                error: function (id) {
                    console.log("cannot change role of " + username);
                }
            })
        } else {
            alert("You cannot change your role!");
        }

    })
    //Delete user
    $('.delete-user-button').click(function () {
        const username = $(this).val();
        const logged = $('#todoUsername').val();
        console.log(logged)
        if (username.localeCompare(logged) !== 0) {
            $.ajax({
                url: '/userDelete/' + username,
                method: 'DELETE',
                contentType: 'application/json',
                //CSRF Protection
                beforeSend: function (xhr) {
                    xhr.setRequestHeader(header, token);
                },
                success: function () {
                    console.log("deleted: " + username);
                    setTimeout(function () {
                        location.reload(); 
                    }, 100);
                },
                error: function (id) {
                    console.log("cannot deleted" + username);
                }
            })
        } else {
            alert("You cannot delete yourself!")
        }

    })
});
//Format transition for Java LocalDate
function formatDate(date) {
    var day = date.getDate();
    var month = date.getMonth();
    var year = date.getFullYear();
    month++;
    if (month < 10)
        month = '0' + month;
    if (day < 10)
        day = '0' + day;
    return [year, month, day].join('-').toString();
};





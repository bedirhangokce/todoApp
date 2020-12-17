$(document).ready(function () {

    //Sort table
    $('#dtBasicExample').DataTable({
        "aaSorting": [],
        columnDefs: [{
            orderable: false,
            targets: [6,7,8]
        }]
    });
    $('.dataTables_length').addClass('bs-select');
    //Add entity
    $('.todo-button').click(function () {
        var description = $('#description').val();
        var date = new Date($('#date-value').val());
        var status = $('#statusSelect').val();
        var createDate = new Date(Date.now())
        date = formatDate(date);
        createDate = formatDate(createDate)
        if (!$.trim(description)){
            alert("You have to define a TODO");
        }
        else if(date === "NaN-NaN-NaN"){
            alert("You have to define a date");
        }
        else if(date < createDate){
            alert("You cannot define date before today")
        }
        else {
            var obj = {
                description:description,
                date:date,
                createDate:createDate,
                status:status
            };
            $.ajax( {
                url:'/addTodo',
                method:'POST',
                contentType:'application/json',
                data:JSON.stringify(obj),
                success: function (data) {
                    console.log(data)
                    setTimeout(function(){
                        location.reload();
                    }, 300);
                }
                ,
                error:function (id) {
                    console.log("cannot add" +this.data);
                   alert("TODO couldn't add");
                }
            });
        }
    });
    //Change status to done
    $('.done-button').click(function () {
        const id = $(this).val();
        $.ajax({
            url:'/todo/' + id,
            method: 'PUT',
            success: function () {
                console.log("done: " + id);
                setTimeout(function(){
                    location.reload();
                }, 300);
            },
            error:function (id) {
                console.log("cannot done" + id);
            }
        })
    })
    //Change status to delayed
    $('.delay-button').click(function () {
        const id = $(this).val();
        $.ajax({
            url:'/todoDelay/' + id,
            async: false,
            method: 'PUT',
            success: function () {
                console.log("delayed: " + id);
                setTimeout(function(){
                    location.reload();
                }, 300);
            },
            error:function (id) {
                console.log("cannot delayed" + id);
            }
        })
    })
    //Delete entity
    $('.delete-button').click(function () {
        const id = $(this).val();
        $.ajax({
            url:'/todoDelete/' + id,
            method: 'DELETE',
            contentType: 'application/json',
            success: function () {
                console.log("deleted: " + id);
                setTimeout(function(){
                    location.reload();
                }, 300);
            },
            error:function (id) {
                console.log("cannot deleted" + id);
            }
        })
    })

    $('#user-button').click(function () {
        const username = $('#addUserUsername').val();
        const password = $('#addUserPassword').val();
        const role = $('#addUserRole').val();
        var obj = {
            username:username,
            password:password,
            role:role
        };
        if (!$.trim(username)){
            console.log("Enter a valid username")
        }
        else if(!$.trim(password)){
            console.log("Enter a valid password")
        }
        else {

            $.ajax({
                url: '/addUser',
                method: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(obj),
                success: function (data) {
                    console.log(data)
                    setTimeout(function () {// wait for 5 secs(2)
                        location.reload(); // then reload the page.(3)
                    }, 300);
                }
                ,
                error: function (id) {
                    console.log("cannot add" + this.data);
                    alert("User couldn't add");
                }
            });
        }
    })
    //Delete user
    $('.delete-user-button').click(function () {
        const username = $(this).val();
        $.ajax({
            url:'/userDelete/' + username,
            method: 'DELETE',
            contentType: 'application/json',
            success: function () {
                console.log("deleted: " + username);
                setTimeout(function(){// wait for 5 secs(2)
                    location.reload(); // then reload the page.(3)
                }, 300);
            },
            error:function (id) {
                console.log("cannot deleted" + username);
            }
        })
    })
});
function formatDate(date) {
    var day = date.getDate();
    var month =date.getMonth();
    var year = date.getFullYear();
    month++;
    if (month < 10)
        month = '0' + month;
    if (day < 10)
        day = '0' + day;
    return [year, month, day].join('-').toString();
};
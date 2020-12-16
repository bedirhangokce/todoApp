$(document).ready(function () {
    //Sort table
    $('#dtBasicExample').DataTable({
        "aaSorting": [],
        columnDefs: [{
            orderable: false,
            targets: [4,5,6]
        }]
    });
    $('.dataTables_length').addClass('bs-select');
    //Add entity
    $('.todo-button').click(function () {
        var description = $('#description').val();
        var date = new Date($('#date-value').val());
        var createDate = new Date(Date.now())
        var date = formatDate(date);
        var createDate = formatDate(createDate)
        if (!$.trim(description)){
            alert("You have to define a TODO");
        }else {
            var obj = {
                description:description,
                date:date,
                createDate:createDate
            };
            $.ajax( {
                url:'/todo',
                method:'POST',
                contentType:'application/json',
                data:JSON.stringify(obj),
                success: function (data) {
                    console.log(data)
                    setTimeout(function(){// wait for 5 secs(2)
                        location.reload(); // then reload the page.(3)
                    }, 300);
                }
                ,
                error:function (id) {
                    console.log("cannot add" +this.data);
                    alert("You have to define a date")
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
            success: function (id) {
                console.log("done" + id);
                setTimeout(function(){// wait for 5 secs(2)
                    location.reload(); // then reload the page.(3)
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
            success: function (id) {
                console.log("delayed" + id);
                setTimeout(function(){// wait for 5 secs(2)
                    location.reload(); // then reload the page.(3)
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
            success: function (id) {
                console.log("deleted" +id);
                setTimeout(function(){// wait for 5 secs(2)
                    location.reload(); // then reload the page.(3)
                }, 300);
            },
            error:function (id) {
                console.log("cannot deleted" + id);
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
}
$(document).ready(function () {
    //Add entity
    $('.todo-button').click(function () {
        var description = $('#description').val();
        var date =$('#date');
        var obj = {
            description:description,
            date:date
        };
        $.ajax( {
            url:'/todo',
            method:'POST',
            contentType:'application/json',
            data:JSON.stringify(obj),
            success: function () {
                setTimeout(function(){// wait for 5 secs(2)
                    location.reload(); // then reload the page.(3)
                }, 500);
            }
        });

    });
    //Change status to done
    $('.done-button').click(function () {
        const id = $(this).val();
        $.ajax({
            url:'/todo/' + id,
            method: 'PUT',
            success: function () {
                setTimeout(function(){// wait for 5 secs(2)
                    location.reload(); // then reload the page.(3)
                }, 5000);
            }
        })
    })
    //Change status to delayed
    $('.delay-button').click(function () {
        const id = $(this).val();
        $.ajax({
            url:'/todoDelay/' + id,
            method: 'PUT',
            success: function () {
                setTimeout(function(){// wait for 5 secs(2)
                    location.reload(); // then reload the page.(3)
                }, 5000);
            }
        })
    })
    //Delete entity
    $(document).on('click','.delete-button',function () {
        const id = $(this).val();
        $.ajax({
            url:'/todoDelete/' + id,
            method: 'DELETE',
            contentType: 'application/json',
            success: function () {
                setTimeout(function(){// wait for 5 secs(2)
                    location.reload(); // then reload the page.(3)
                }, 500);
            }
        })
    })


});
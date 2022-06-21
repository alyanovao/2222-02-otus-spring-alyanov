function authorList() {
    $.get('/api/authors').done(function (authors) {
    authors.forEach(function (author) {
        $('tbody').append(`
                <tr>
                    <td>${author.firstName}</td>
                    <td>${author.lastName}</td>
                    <td>${author.patronymic}</td>
                    <td><a href="/author/edit/${author.id}">Редактировать</a></td>
                    <td><a href="/author/edit">Создать</a></td>
                </tr>
            `)
        });
    })
}

function getAuthor() {
    var url
    if ($('#id-input').length) {
        url = '/api/author/' + $('#id-input').val()
    }
    else {
        url = '/api/author'
    }
    $.get(url).done(function (author) {
        $('#author-firstname-input').val(author.firstName)
        $('#author-lastName-input').val(author.lastName)
        $('#author-patronymic-input').val(author.patronymic)
    })
}

function saveAuthor() {
    $.ajax(
        "/api/author",
        {
            type:"POST",
            async: false,
            cache: false,
            data: JSON.stringify({
                id:$('#id-input').val(),
                firstName:$('#author-firstname-input').val(),
                lastName:$('#author-lastName-input').val(),
                patronymic:$('#author-patronymic-input').val()
            }),
            contentType: 'application/json; charset=UTF-8',
            dataType: 'json',
            success: function (response) {
                window.location.href = "/authors";
            }
        }
    )
}
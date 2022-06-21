function bookList() {
    $.get('/api/books').done(function (books) {
        books.forEach(function (book) {
            $('tbody').append(`
                <tr>
                    <td>${book.name}</td>
                    <td>${book.author}</td>
                    <td>${book.kind}</td>
                    <td>${book.commentary}</td>
                    <td><a href="/book/edit/${book.id}">Редактировать</a></td>
                    <td><a href="/book/edit">Создать</a></td>
                    <td><a href="#" onclick="deleteBook('${book.id}')">Удалить</a></td>
                </tr>
            `)
        });
    })
}

function getBook() {
    console.log("!!!" + $('#book-id-input').val())
    var url = '/api/book'
    if ($('#book-id-input').length) {
        url = url + '/' + $('#book-id-input').val()
    }
    $.get(url).done(function (book) {
        var commentary = ""
        $.each(book.commentary, function (index, name) {
            console.log("array:" + name.value)
            if (commentary == "") {
                commentary = name.value
            } else {
                commentary = commentary + ', ' + name.value
            }
        })
        $('#div-input-id').val(book.id)
        $('#bookEdit-input').val(book.name)
        $('#commentary-input').val(commentary)
    })
    $.get('/api/authors').done(function (authors) {
        authors.forEach(function (author) {
            $('#form-input').append(`
            <option value=${author.id}>${author.firstName} ${author.lastName} ${author.patronymic}</option>
            `)
        })
    })
    $.get('/api/kindBooks').done(function (kinds) {
        kinds.forEach(function (kind) {
            $('#kindbook-input').append(`
            <option value=${kind.id}>${kind.name}</option>
            `)
        })
    })
}

function deleteBook(id) {
    $.ajax({
        url: '/api/book/' + id,
        type: 'DELETE',
        success: function () {
            window.location.href = "/book";
        }
    })
}

function saveBook() {
    $.ajax(
        "/api/book",
        {
            type: "POST",
            async: false,
            cache: false,
            data: JSON.stringify({
                id: $('#book-id-input').val(),
                name: $('#bookEdit-input').val(),
                authorId: $('#form-input').val(),
                kindId: $('#kindbook-input').val(),
                commentary: $('#commentary-input').val()
            }),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function () {
                window.location.href = "/book";
            }
        }
    )
}
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
                    <td><a href="#" onclick="deleteBook(${book.id})">Удалить</a></td>
                </tr>
            `)
        });
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
                id:$('#book-id-input').val(),
                name: $('#bookEdit-input').val(),
                authorId: $('#form-input').val(),
                kindId: $('#kindbook-input').val(),
                commentary: $('#commentary-input').val()
            }),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function () {
                window.location.href="/book";
            }
        }
    )
}
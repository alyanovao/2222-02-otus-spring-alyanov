function kindBookList() {
    $.get('/api/kindBooks').done(function (kinds) {
        kinds.forEach(function (kind) {
            $('tbody').append(`
            <tr>
                <td>${kind.name}</td>
            </tr>
            `)
        });
    })
}
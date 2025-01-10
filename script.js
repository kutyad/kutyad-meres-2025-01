document.getElementById('searchForm').addEventListener('submit', function(e) {
    e.preventDefault();

    const datum = document.getElementById('datum').value.trim();
    const nev = document.getElementById('nev').value.trim();
    let apiUrl = 'http://localhost/api/nevnapok/';

    if (datum) {
        apiUrl += `?nap=${datum}`;
    } else if (nev) {
        apiUrl += `?nev=${nev}`;
    } else {
        document.getElementById('result').innerHTML = "<p>Használja az API-t! Példa: /?nap=12-31 vagy /?nev=Katalin</p>";
        return;
    }

    fetch(apiUrl)
        .then(response => response.json())
        .then(data => {
            if (data.hiba) {
                document.getElementById('result').innerHTML = `<p>${data.hiba}</p>`;
            } else {
                let resultHTML = `<p>${data.datum} - Névnapi ünneplők: ${data.nevnap1}`;
                if (data.nevnap2) {
                    resultHTML += ` és ${data.nevnap2}`;
                }
                resultHTML += `</p>`;
                document.getElementById('result').innerHTML = resultHTML;
            }
        })
        .catch(error => {
            document.getElementById('result').innerHTML = "<p>Hiba történt a keresés során.</p>";
        });
});

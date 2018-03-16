function sendInputDataToServer() {
    let searchFormDto = createFormObject('#search-form');
    searchFormDto.page = document.activeElement.getAttribute('value');
    $.ajax('/credit_applications', {
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(searchFormDto)
    }).done(function(data) {
        let resultPage = data.resultPage;
        if (resultPage[0] == null) {
            document.getElementById("search-result").innerHTML = 'No such applications';
            return;
        }
        document.getElementById("search-result").innerHTML = '';

        let table = createElementWithClasses("table", ["table", "table-striped"]);
        let tHead = createElementWithClasses("thead", ["thead-light"]);

        let trHead = document.createElement("tr");
        fillRowWithValues(trHead, Object.keys(resultPage[0]));

        tHead.appendChild(trHead);
        table.appendChild(tHead);

        let tbody = document.createElement("tbody");

        for (let i = 0; i < resultPage.length; i++) {
            let tr = document.createElement("tr");

            fillRowWithObjectFieldsValues(tr, resultPage[i]);

            tbody.appendChild(tr);
        }

        table.appendChild(tbody);

        let pages = createPageButtons(searchFormDto.page, data.lastPage);
        document.getElementById("search-result").appendChild(pages);
        document.getElementById("search-result").appendChild(table);
    });
}

function fillRowWithValues(tr, list) {
    list.forEach(function(item) {
        let td = document.createElement("td");
        td.appendChild(document.createTextNode(item.toString()));
        tr.appendChild(td);
    });
}

function fillRowWithObjectFieldsValues(tr, object) {
    let fields = Object.keys(object);
    fields.forEach(function(item) {
        let td = document.createElement("td");
        td.appendChild(document.createTextNode(object[item.toString()]));
        tr.appendChild(td);
    });
}

function createElementWithClasses(elementName, classes) {
    let element = document.createElement(elementName);
    classes.forEach(function(item) {
        element.classList.add(item);
    });
    return element;
}

function createPageButtons(page, lastPage) {
    let ul = createElementWithClasses("ul", ["pagination", "justify-content-center"]);

    for (let i = 1; i < lastPage + 1; i++) {
        let li = document.createElement("li");
        li.classList.add("page-item");

        let pageButton = createElementWithClasses("button", ["btn", "btn-light"]);
        pageButton.appendChild(document.createTextNode(i.toString()));
        pageButton.value = i;
        pageButton.onclick = sendInputDataToServer;

        if (i == page) {
            pageButton.classList.add("active");
        }

        li.appendChild(pageButton);
        ul.appendChild(li);
    }
    return ul;
}

function createFormObject(formId) {
    let searchFormDto = {};
    $.each($(formId).serializeArray(), function (_, initialForm) {
        if (initialForm.name.substring(0, 1) !== '_') {
            if (searchFormDto.hasOwnProperty(initialForm.name)) {
                searchFormDto[initialForm.name] = $.makeArray(searchFormDto[initialForm.name]);
                searchFormDto[initialForm.name].push(initialForm.value);
            }
            else {
                searchFormDto[initialForm.name] = initialForm.value;
            }
        }
    });
    return searchFormDto;
}
//
// function getCheckboxInputs(checkboxName) {
//     let result = $("input[name=" + checkboxName + "]:checked").map(function(){
//         return this.id;
//     }).get();
//
//     if (result.length === 0) {
//         result = $("input[name=" + checkboxName + "]:not(checked)").map(function(){
//             return this.id;
//         }).get();
//     }
//     return result;
// }
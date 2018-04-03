let dtoLists = ["marital status", "gender", "clientRating", "creditId", "applicationQuality", "scoringSystemResolution"];

function sendInputDataToServer() {
    let searchFormDto = createFormObject('#search-form', dtoLists);
    searchFormDto.page = document.activeElement.getAttribute('value');
    $.ajax('/admin/creditApplications', {
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(searchFormDto)
    }).done(function(data) {
        let resultPage = data.resultPage;
        let table = createTableFromList(resultPage, "search-result");
        let pages = createPageButtons(searchFormDto.page, data.lastPage);
        document.getElementById("search-result").appendChild(pages);
        document.getElementById("search-result").appendChild(table);
    });
}

function generateApplications() {
    let dto = createFormObject('#search-form', dtoLists);
    $.ajax('/god/generator', {
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(dto)
    }).done(function(data) {
        let table = createTableFromList(data, "search-result");
        document.getElementById("search-result").appendChild(table);
    });
}

function createTableFromList(list, resultDivId) {
    if (list[0] == null) {
        document.getElementById(resultDivId).innerHTML = 'No such applications';
        return null;
    }

    document.getElementById(resultDivId).innerHTML = '';

    let table = createElementWithClasses("table", ["table", "table-striped"]);
    let tHead = createElementWithClasses("thead", ["thead-light"]);

    let trHead = document.createElement("tr");
    fillRowWithValues(trHead, beautifyCamelCaseArray(Object.keys(list[0])));

    tHead.appendChild(trHead);
    table.appendChild(tHead);

    let tbody = document.createElement("tbody");

    for (let i = 0; i < list.length; i++) {
        let tr = document.createElement("tr");
        fillRowWithObjectFieldsValues(tr, list[i]);
        tbody.appendChild(tr);
    }

    table.appendChild(tbody);

    return table;
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

function createFormObject(formId, lists) {
    let searchFormDto = {};
    $.each($(formId).serializeArray(), function (_, property) {
        if (property.name.substring(0, 1) !== '_') {
            if ((searchFormDto.hasOwnProperty(property.name)) || (lists.includes(property.name))) {
                searchFormDto[property.name] = $.makeArray(searchFormDto[property.name]);
                searchFormDto[property.name].push(property.value);
            }
            else {
                searchFormDto[property.name] = property.value;
            }
        }
    });
    return searchFormDto;
}
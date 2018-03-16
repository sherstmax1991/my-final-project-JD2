function addChildFieldsToRegistrationForm() {
    let firstFormRow = createElementWithClasses("div", ["form-row"]);
    let secondFormRow = createElementWithClasses("div", ["form-row"]);

    let firstNameDiv = createInputDivForField("firstName", 1, "text");
    let lastNameDiv = createInputDivForField("lastName", 1, "text");

    firstFormRow.appendChild(firstNameDiv);
    firstFormRow.appendChild(lastNameDiv);

    let birthDayDiv = createInputDivForField("birthDay", 1, "date");
    let selectDiv = createSelectDivForField("gender", 1, ["Male", "Female"]);

    secondFormRow.appendChild(birthDayDiv);
    secondFormRow.appendChild(selectDiv);
    secondFormRow.marginBottom = '20px';

    let regFormChildrenList = document.getElementById("children");

    regFormChildrenList.appendChild(firstFormRow);
    regFormChildrenList.appendChild(secondFormRow);
}

function createInputDivForField(fieldName, fieldDiscriminatorValue, fieldType) {
    let div = createElementWithClasses("div", ["col"]);
    let id = fieldName + fieldDiscriminatorValue.toString();
    let input = document.createElement("input");
    input.id = id;
    input.type = fieldType;
    let label = document.createElement("label");
    label.setAttribute("for", id);
    div.appendChild(input);
    div.appendChild(label);
    return div;
}

function createSelectDivForField(fieldName, fieldDiscriminatorValue, optionsList) {
    let div = createElementWithClasses("div", ["col"]);
    let id = fieldName + fieldDiscriminatorValue.toString();
    let select = createElementWithClasses("select", ["custom-select"]);
    select.id = id;
    let label = document.createElement("label");
    label.setAttribute("for", id);

    optionsList.forEach(function (value) {
        let option = document.createElement("option");
        option.innerHTML = value.toString();
        select.appendChild(option);
    });

    div.appendChild(select);
    div.appendChild(label);
    return div;
}

function submitRegistrationForm()() {

}
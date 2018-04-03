let childrenFieldName = 'children';
let rolesFieldName = 'roles';

function addChildFieldsToRegistrationForm() {
    let childrenCounter = +document.getElementById("children-counter").getAttribute('value');
    childrenCounter++;
    document.getElementById("children-counter").setAttribute('value', childrenCounter.toString());
    document.getElementById("children-counter").innerText = 'Children(' + childrenCounter + ')';
    let discriminator = childrenCounter;
    let divRow = createElementWithClasses("div", ["row"]);
    let divCol = createElementWithClasses("div", ["col"]);
    let firstFormRow = createElementWithClasses("div", ["form-row"]);
    let secondFormRow = createElementWithClasses("div", ["form-row"]);

    let firstNameDiv = createInputDivForField("firstName", discriminator, "text");
    let lastNameDiv = createInputDivForField("lastName", discriminator, "text");

    firstFormRow.appendChild(firstNameDiv);
    firstFormRow.appendChild(lastNameDiv);

    let birthDayDiv = createInputDivForField("birthday", discriminator, "date");
    let selectDiv = createSelectDivForField("gender", discriminator, ["MALE", "FEMALE"]);

    secondFormRow.appendChild(birthDayDiv);
    secondFormRow.appendChild(selectDiv);
    secondFormRow.style.marginBottom = '20px';

    let regFormChildrenList = document.getElementById("children-fieldset");

    divCol.appendChild(firstFormRow);
    divCol.appendChild(secondFormRow);

    divRow.appendChild(divCol);

    let removeButton = createElementWithClasses("button", ["btn", "btn-warning"]);
    removeButton.onclick = removeChild;
    removeButton.innerHTML = "-";
    removeButton.style = "height: 50%";

    divRow.appendChild(removeButton);

    let div = document.createElement("div");

    div.appendChild(divRow);
    div.appendChild(document.createElement("hr"));

    regFormChildrenList.appendChild(div);
}

function createInputDivForField(fieldName, fieldDiscriminatorValue, fieldType) {
    let div = createElementWithClasses("div", ["col"]);
    let id = fieldName + fieldDiscriminatorValue;
    let input = document.createElement("input");
    input.id = id;
    input.type = fieldType;
    input.name = buildJsPropertyNameForEmbeddedEntity(childrenFieldName, fieldDiscriminatorValue, fieldName);
    let label = document.createElement("label");
    label.htmlFor = id;
    label.innerHTML = beautifyCamelCaseString(fieldName);
    div.appendChild(label);
    div.appendChild(input);
    return div;
}

function createSelectDivForField(fieldName, fieldDiscriminatorValue, optionsList) {
    let div = createElementWithClasses("div", ["col"]);
    let id = fieldName + fieldDiscriminatorValue;
    let select = createElementWithClasses("select", ["custom-select"]);
    select.id = id;
    select.name = buildJsPropertyNameForEmbeddedEntity(childrenFieldName, fieldDiscriminatorValue, fieldName);
    let label = document.createElement("label");
    label.htmlFor = id;
    label.innerHTML = beautifyCamelCaseString(fieldName);

    optionsList.forEach(function (value) {
        let option = document.createElement("option");
        option.innerHTML = value.toString();
        select.appendChild(option);
    });

    div.appendChild(label);
    div.appendChild(select);
    return div;
}

function createRegistrationFormDto(formId) {
    let registrationFormDto = {};
    let childrenCount = document.getElementById("children-counter").getAttribute("value");
    let children = [];
    for(var i = 0; i < +childrenCount; i++) {
        let child = {};
        children.push(child);
    }
    childrenCount--;
    let roles = [];
    $.each($(formId).serializeArray(), function (_, property) {
        if (property.name.startsWith(childrenFieldName)) {
            let childFieldName = getEmbeddedObjectFieldName(property.name);
            if (children[childrenCount].hasOwnProperty(childFieldName)) {
                childrenCount--;
            }
                children[childrenCount][childFieldName] = property.value;
            delete registrationFormDto[property.name];
        } else if (property.name.startsWith(rolesFieldName)) {
            roles.push(property.value);
            delete registrationFormDto[property.name];
        } else {
            registrationFormDto[property.name] = property.value;
        }
        if (property.name.startsWith('_')) {
            delete registrationFormDto[property.name];
        }
    });
    registrationFormDto.children = children;
    registrationFormDto.roles = roles;
    return registrationFormDto;
}

function submitRegistrationForm(){
    let json = JSON.stringify(createRegistrationFormDto("#client-form"));
    $.ajax('/registration', {
        method: 'POST',
        contentType: 'application/json',
        data: json
    }).done(function(data) {
        document.getElementById('client-form').innerHTML = data;
    });
}

function buildJsPropertyNameForEmbeddedEntity(collectionName, discriminatorValue, itemFieldName) {
    return collectionName + '[' + discriminatorValue + '].' + itemFieldName;
}

function getEmbeddedObjectFieldName(property) {
    let propertiesArray = property.split(".");
    return propertiesArray[propertiesArray.length - 1];
}

function removeChild() {
    let childrenCounter = +document.getElementById("children-counter").getAttribute('value');
    childrenCounter--;
    document.getElementById("children-counter").setAttribute('value', childrenCounter.toString());
    document.getElementById("children-counter").innerText = 'Children(' + childrenCounter + ')';
    removeGrandparentElement(document.activeElement);
}

function removeChildOnButton(button) {
    let childrenCounter = +document.getElementById("children-counter").getAttribute('value');
    childrenCounter--;
    document.getElementById("children-counter").setAttribute('value', childrenCounter.toString());
    document.getElementById("children-counter").innerText = 'Children(' + childrenCounter + ')';
    removeGrandparentElement(button);
}

function removeGrandparentElement(element) {
    let grandParent = element.parentElement.parentElement;
    grandParent.parentElement.removeChild(grandParent);
}

function beautifyCamelCaseArray(array) {
    return array.map(function (value) {
        return beautifyCamelCaseString(value);
    });
}

function beautifyCamelCaseString(string) {
    return firstWordUpperCase(splitCamelCaseString(string));
}

function splitCamelCaseString(string) {
    return string.replace(/([a-zA-Z])(?=[A-Z])/g, '$1 ').toLowerCase();
}

function firstWordUpperCase(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}
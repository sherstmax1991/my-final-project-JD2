let layersFieldName = 'layers';

function getClientApplications() {
    $.ajax('/profile/applications', {
        method: 'POST',
        contentType: 'application/json'
    }).done(function(resultPage) {
        let table = createTableFromList(resultPage, 'credits-search-result');
        document.getElementById('credits-search-result').appendChild(table);
    });
}

function updateClientInfo() {
    let json = JSON.stringify(createRegistrationFormDto("#client-form"));
    $.ajax('/profile/update', {
        method: 'POST',
        contentType: 'application/json',
        data: json
    }).done(function(data) {
        document.getElementById('client-form').innerHTML = data;
    });
}

function refreshNeuronNetwork() {
    let dto = createNeuronNetworkDto('#create-network');
    $.ajax('/admin/neuronNetwork/teach', {
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(dto)
    }).done(function(data) {
        alert(data);
    });
}

function createNeuronNetworkDto(formId) {
    let dto = {};
    let layers = [];
    $.each($(formId).serializeArray(), function (_, property) {
        if (property.name.startsWith(layersFieldName)) {
            layers.push(property.value);
        } else {
            dto[property.name] = property.value;
        }
    });
    dto.layers = layers;
    return dto;
}
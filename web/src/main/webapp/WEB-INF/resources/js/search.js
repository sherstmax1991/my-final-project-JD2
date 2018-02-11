function sendInputDataToServer() {
    let dateFrom = $("#dateFrom").val();
    let dateTo = $("#dateTo").val();
    let ageTo = $("#ageTo").val();
    let ageFrom = $("#ageFrom").val();
    let childrenTo = $("#childrenTo").val();
    let childrenFrom = $("#childrenFrom").val();
    let gender = $("#gender").val();
    let maritalStatus = $("#maritalStatus").val();
    let clientRating = $("#clientRating").val();
    let creditId = $("#creditId").val();
    let incomeFrom = $("#incomeFrom").val();
    let incomeTo = $("#incomeTo").val();
    let pledgeFrom = $("#pledgeFrom").val();
    let pledgeTo = $("#pledgeTo").val();
    let sumFrom = $("#sumFrom").val();
    let sumTo = $("#sumTo").val();
    let loanPeriodFrom = $("#loanPeriodFrom").val();
    let loanPeriodTo = $("#loanPeriodTo").val();
    let applicationQuality = $("#applicationQuality").val();
    let scoringSystemResolution = $("#scoringSystemResolution").val();
    let page = $("#page").val();
    let applicationsPerPage = $("#applicationsPerPage").val();
    $.ajax({
        url: '/credit_applications',
        method: 'POST',
        data: JSON.stringify({
            dateFrom: dateFrom,
            dateTo: dateTo,
            ageFrom: ageFrom,
            ageTo: ageTo,
            childrenFrom: childrenFrom,
            childrenTo: childrenTo,
            gender: gender,
            maritalStatus: maritalStatus,
            clientRating: clientRating,
            creditId: creditId,
            incomeFrom: incomeFrom,
            incomeTo: incomeTo,
            pledgeFrom: pledgeFrom,
            pledgeTo: pledgeTo,
            sumFrom: sumFrom,
            sumTo: sumTo,
            loanPeriodFrom: loanPeriodFrom,
            loanPeriodTo: loanPeriodTo,
            applicationQuality: applicationQuality,
            scoringSystemResolution: scoringSystemResolution,
            page: page,
            applicationsPerPage: applicationsPerPage
        })
    }).done(function(data) {
        $("#search-result").text(data.outputMessage);
    });
}
    var benefits = document.querySelector("#textArea #bF");
    var requirement = document.querySelector("#textArea #reqs");
    var qualification = document.querySelector("#textArea #qual");
    var preferred = document.querySelector("#textArea #pQual");
    var stringSplit = benefits.innerText.split(",");
    var requirementSplit = requirement.innerText.split(",");
    var qualificationSplit = qualification.innerText.split(",");
    var preferredSplit = preferred.innerText.split(",");

    document.querySelector("#textArea #bF").textContent = "";
    document.querySelector("#textArea #reqs").textContent = "";
    document.querySelector("#textArea #qual").textContent = "";
    document.querySelector("#textArea #pQual").textContent = "";

    function makeUL(paragraph, list){
    var a = '<ul>',
        b = '</ul>',
        m = [];

    for (i = 0; i < list.length; i += 1){
        m[i] = '<li>' + list[i] + '</li>';
        console.log(list[i]);

        document.createTextNode(m[i]);



        paragraph.innerHTML += a + m[i] + b;

    }
    console.log(stringSplit);
}

makeUL(benefits, stringSplit);
makeUL(requirement, requirementSplit);
makeUL(qualification, qualificationSplit);
makeUL(preferred, preferredSplit);

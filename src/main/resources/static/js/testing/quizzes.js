function setPageFilters() {
    const urlParams = new URLSearchParams(window.location.search);
    const type = urlParams.get("type");
    const subject = urlParams.get("subject");
    const sortBy = urlParams.get("sortBy");
    const order = urlParams.get("order");
    const searchValue=urlParams.get("searchValue");
    if (type) {
        document.getElementById("typeSelect").value = type;
    }
    if (subject) {
        document.getElementById("subjectSelect").value = subject;
    }

    if (sortBy) {
        document.getElementById("sortBySelect").value = sortBy;
    }
    if (order) {
        document.getElementById("orderSelect").value = order;
    }
    if(searchValue){
        document.getElementById("search").value=searchValue;
    }
}

window.addEventListener("load", setPageFilters);
function navigateToInteract() {
    var type = document.getElementById("typeSelect").value;
    var subject = document.getElementById("subjectSelect").value;
    var sortBy=document.getElementById("sortBySelect").value;
    var order=document.getElementById("orderSelect").value;
    var searchValue=document.getElementById("search").value;

    var url = "/testingcontent/quizzeslist?";
    var filters = [];

    if(searchValue){
        filters.push("searchValue=" + searchValue);
    }
    if (type) {
        filters.push("type=" + type);
    }

    if (subject) {
        filters.push("subject=" + subject);
    }
    if(sortBy) {
        filters.push("sortBy="+sortBy);
    }
    if(order) {
        filters.push("order="+order);
    }
    url += filters.join("&");

    window.location.href = url;

}
function moveOn(pageNo){

    var type = document.getElementById("typeSelect").value;
    var subject = document.getElementById("subjectSelect").value;
    var sortBy=document.getElementById("sortBySelect").value;
    var order=document.getElementById("orderSelect").value;
    var searchValue=document.getElementById("search").value;

    var url = "/testingcontent/quizzeslist?pageNo=" + (parseInt(pageNo) + 1)+"&";

    var filters = [];

    if(searchValue){
        filters.push("searchValue=" + searchValue);
    }
    if (type) {
        filters.push("type=" + type);
    }

    if (subject) {
        filters.push("subject=" + subject);
    }
    if(sortBy) {
        filters.push("sortBy="+sortBy);
    }
    if(order) {
        filters.push("order="+order);
    }
    url += filters.join("&");

    window.location.href = url;
}

function moveBack(pageNo){
    var type = document.getElementById("typeSelect").value;
    var subject = document.getElementById("subjectSelect").value;
    var sortBy=document.getElementById("sortBySelect").value;
    var order=document.getElementById("orderSelect").value;
    var searchValue=document.getElementById("search").value;

    var url = "/testingcontent/quizzeslist?pageNo=" + (parseInt(pageNo) + 1)+"&";

    var filters = [];

    if(searchValue){
        filters.push("searchValue=" + searchValue);
    }
    if (type) {
        filters.push("type=" + type);
    }

    if (subject) {
        filters.push("subject=" + subject);
    }
    if(sortBy) {
        filters.push("sortBy="+sortBy);
    }
    if(order) {
        filters.push("order="+order);
    }
    url += filters.join("&");

    window.location.href = url;
}
function showPopup() {
    document.getElementById("popupForm").style.display = "block";
}

function hidePopup() {
    document.getElementById("popupForm").style.display = "none";
}
$('.js-input' ).keyup(function() {
    if( $(this).val() ) {
        $(this).addClass('not-empty');
    } else {
        $(this).removeClass('not-empty');
    }
});

function setNoQuestion(value) {
    document.getElementById("noQuestionId").value = value
}

async function fetchNewData() {
    let curr_id = document.getElementById('currentSubject');
    let origin_id = document.getElementById('originSubject');
    let id = document.getElementById('subjectSelect').value;
    if (curr_id.value !== id) {
        let lessonId = document.getElementById("lessonId").value;
        let lessonProp_url = `http://localhost:8080/api/get-lesson-prop?lessonId=${lessonId}`
        const lessonProps = (await (await fetch(lessonProp_url)).json()).lessonProperties;
        let subCate_url = `http://localhost:8080/api/get-subcategories?subjectId=${id}`;
        const subcategories = (await (await fetch(subCate_url)).json()).subCategories;
        document.querySelectorAll(".close-button").forEach(o => o.click());
        if (lessonProps.length > 0 && origin_id.value === id) {
            let dimen_url = `http://localhost:8080/api/get-dimensions?subjectId=${id}&sortBy=${lessonProps[0].sortBy}`;
            const dimensiones = (await (await fetch(dimen_url)).json()).dimensiones;
            document.getElementById(lessonProps[0].sortBy).click();
            lessonProps.forEach(prop => {
                switch (prop.sortBy) {
                    case "TOPIC":
                        addTopicDropdown(subcategories, prop.subCategoryId, prop.noQuestion);
                        break;
                    case "DOMAIN":
                        addDomainDropdown(dimensiones, prop.dimensionId, prop.noQuestion);
                        break;
                    case "GROUP":
                        addGroupDropdown(dimensiones, prop.dimensionId, prop.noQuestion);
                        break;
                }
            })
            setNoQuestion(lessonProps.map(prop => +prop.noQuestion).reduce((sum, v) => sum + v, 0));
        } else {            origin_id.value = -1;

            document.getElementById("TOPIC").click();
            subcategories.forEach(o => addTopicDropdown(subcategories, o.id, 0));
            setNoQuestion(0);
        }
        curr_id.value = id;
    }
}

async function addTopicDropdown(subcategories, index, noQuestion) {

        let id = document.getElementById('subjectSelect').value;
        let url = `http://localhost:8080/api/get-subcategories?subjectId=${id}`;
        subcategories = (await (await fetch(url)).json()).subCategories;


    if (subcategories.length <= 0) {
        return;
    }
    let contentId = "topic-content";
    const topicContent = document.getElementById(contentId);

    const newDropdown = document.createElement('div');
    newDropdown.classList.add('form-field', 'col', 'x-100');

    newDropdown.innerHTML = `
  <div class="form-field col x-20">
    <select class="form-select input-text not-empty" name="topicSelect" >
      ${subcategories.map(type => `<option value="${type.id}" ${+type.id === +index? "selected" : ""}>${type.name}</option>`).join('')}
    </select>
    <label class="label" for="topSelect">Sub category</label>
  </div>
  <div class="form-field col x-20">
     <input name="noTopicQuestion" class="input-text not-empty js-input noQuestion" type="number" value="${noQuestion}" onchange="setNoQuestion(calcNoQuestion('${contentId}'))" required>
    <label class="label" for="noTopicQuestion">Number Questions</label>
  </div>
  <button class="close-button">Close</button>
`;

    const closeButton = newDropdown.querySelector('.close-button');
    closeButton.addEventListener('click', () => {
        topicContent.removeChild(newDropdown);
        setNoQuestion(calcNoQuestion(contentId));
    });

    topicContent.appendChild(newDropdown);
}

async function addDomainDropdown(dimensiones, index, noQuestion) {

        let id = document.getElementById('subjectSelect').value;
        let url = `http://localhost:8080/api/get-dimensions?subjectId=${id}&sortBy=DOMAIN`;
        dimensiones = (await (await fetch(url)).json()).dimensiones;
        if (dimensiones !== null)


    if (dimensiones.length <= 0) {
        return;
    }
    const contentId = "domain-content";
    const topicContent = document.getElementById(contentId);

    const newDropdown = document.createElement('div');
    newDropdown.classList.add('form-field', 'col', 'x-100');
    newDropdown.innerHTML = `
  <div class="form-field col x-20">
    <select class="form-select input-text not-empty" name="domainSelect">
      ${dimensiones.map(type => `<option value="${type.id}" ${+type.id === +index? "selected" : ""}>${type.name}</option>`).join('')}
    </select>
    <label class="label" for="domainSelect">Domain</label>
  </div>
  <div class="form-field col x-20">
    <input class="input-text not-empty js-input noQuestion" name="noDomainQuestion" type="number" value="${noQuestion}" onchange="setNoQuestion(calcNoQuestion('${contentId}'))" required>
    <label class="label" for="noDomainQuestion">Number of question</label>
  </div>
 <button class="close-button">Close</button>
`;

    const closeButton = newDropdown.querySelector('.close-button');
    closeButton.addEventListener('click', () => {
        topicContent.removeChild(newDropdown);
        setNoQuestion(calcNoQuestion(contentId));
    });

    topicContent.appendChild(newDropdown);
}

async function addGroupDropdown(dimensiones, index, noQuestion) {

        let id = document.getElementById('subjectSelect').value;
        let url = `http://localhost:8080/api/get-dimensions?subjectId=${id}&sortBy=GROUP`;
        dimensiones = (await (await fetch(url)).json()).dimensiones;


    if (dimensiones.length <= 0) {
        return;
    }
    const contentId = "group-content";
    const topicContent = document.getElementById(contentId);

    const newDropdown = document.createElement('div');
    newDropdown.classList.add('form-field', 'col', 'x-100');

    newDropdown.innerHTML = `
  <div class="form-field col x-20">
    <select class="form-select input-text not-empty" name="groupSelect">
      ${dimensiones.map(type => `<option value="${type.id}" ${+type.id === +index? "selected" : ""}>${type.name}</option>`).join('')}
    </select>
    <label class="label" for="groupSelect">Group</label>
  </div>
  <div class="form-field col x-20">
    <input class="input-text not-empty js-input noQuestion" name="noGroupQuestion" value="${noQuestion}" onchange="setNoQuestion(calcNoQuestion('${contentId}'))" required>
    <label class="label" for="noGroupQuestion">Number of question</label>
  </div>
 <button class="close-button">Close</button>
`;

    const closeButton = newDropdown.querySelector('.close-button');
    closeButton.addEventListener('click', () => {
        topicContent.removeChild(newDropdown);
        setNoQuestion(calcNoQuestion(contentId));
    });

    topicContent.appendChild(newDropdown);
}
function calcNoQuestion(contextId) {
    return [...document.getElementById(contextId).querySelectorAll(".noQuestion")].map(noQuestion => + noQuestion.value).reduce((sum, v) => sum + v, 0);
}